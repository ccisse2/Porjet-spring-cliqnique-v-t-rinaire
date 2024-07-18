package fr.eni.projet.cliniqueveterinaire.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class CliniqueSecurityConfig {
    private static final String SELECT_USER = "SELECT Nom, MotPasse, 1 FROM Personnels WHERE Nom = ?";
    private static final String SELECT_ROLES = "SELECT p.Nom, r.Description AS Role " +
            "FROM Personnels p " +
            "LEFT JOIN PersonnelRoles pr ON p.CodePers = pr.CodePers " +
            "LEFT JOIN Roles r ON pr.CodeRole = r.CodeRole " +
            "WHERE p.Nom = ?";

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(SELECT_USER);
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(SELECT_ROLES);
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                /*.requestMatchers("/").permitAll()
                                .requestMatchers("/css/**").permitAll()
                                .requestMatchers("/images/**").permitAll()
                                .requestMatchers("/personnels/**").hasRole("DIR")
                                .requestMatchers("/rendezvous/**").hasAnyRole("DIR", "VET", "SEC")
                                .requestMatchers("/consultations/**").hasAnyRole("DIR", "VET", "AST")
                                .requestMatchers("/clients/**").hasAnyRole("DIR", "SEC")
                                .anyRequest().authenticated()*/
                        .anyRequest().permitAll()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/", true)
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login?logout")
                                .permitAll()
                );

        return http.build();
    }
}
