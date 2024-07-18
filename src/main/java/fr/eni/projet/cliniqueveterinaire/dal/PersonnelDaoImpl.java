package fr.eni.projet.cliniqueveterinaire.dal;

import fr.eni.projet.cliniqueveterinaire.bo.Personnel;
import fr.eni.projet.cliniqueveterinaire.bo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonnelDaoImpl implements PersonnelDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private PasswordEncoder passwordEncoder;

    private static final String INSERT_PERSONNEL = "INSERT INTO Personnels (Nom, MotPasse, Archive) " +
            "VALUES (:nom, :motPasse, :archive)";
    private static final String INSERT_PERSONNEL_ROLE = "INSERT INTO PersonnelRoles (CodePers, CodeRole) " +
            "VALUES (:codePers, :codeRole)";
    private static final String SELECT_PERSONNEL_BY_CODE = "SELECT p.CodePers, p.Nom, p.MotPasse, r.Description AS Role, p.Archive " +
            "FROM Personnels p " +
            "LEFT JOIN PersonnelRoles pr ON p.CodePers = pr.CodePers " +
            "LEFT JOIN Roles r ON pr.CodeRole = r.CodeRole " +
            "WHERE p.CodePers = :codePers";
    private static final String SELECT_ALL_PERSONNELS = "SELECT p.CodePers, p.Nom, p.MotPasse, r.Description AS Role, p.Archive " +
            "FROM Personnels p " +
            "LEFT JOIN PersonnelRoles pr ON p.CodePers = pr.CodePers " +
            "LEFT JOIN Roles r ON pr.CodeRole = r.CodeRole";
    private static final String DELETE_PERSONNEL = "DELETE FROM Personnels WHERE CodePers = :codePers";
    private static final String DELETE_PERSONNEL_ROLES = "DELETE FROM PersonnelRoles WHERE CodePers = :codePers";
    private static final String UPDATE_PERSONNEL = "UPDATE Personnels SET Nom = :nom, MotPasse = :motPasse, Archive = :archive WHERE CodePers = :codePers";
    private static final String UPDATE_PERSONNEL_ROLE = "UPDATE PersonnelRoles SET CodeRole = :codeRole WHERE CodePers = :codePers";
    private static final String SELECT_ALL_ROLES = "SELECT CodeRole, Description FROM Roles";
    @Autowired
    public PersonnelDaoImpl(NamedParameterJdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(Personnel personnel) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String encodedPassword = passwordEncoder.encode(personnel.getMotPasse());

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom", personnel.getNom());
        namedParameters.addValue("motPasse", encodedPassword);
        namedParameters.addValue("archive", personnel.isArchive());

        jdbcTemplate.update(INSERT_PERSONNEL, namedParameters, keyHolder);

        if (keyHolder.getKey() != null) {
            long codePers = keyHolder.getKey().longValue();
            personnel.setCodePers(codePers);

            for (String role : personnel.getRoles()) {
                MapSqlParameterSource roleParameters = new MapSqlParameterSource();
                roleParameters.addValue("codePers", codePers);
                roleParameters.addValue("codeRole", role);
                jdbcTemplate.update(INSERT_PERSONNEL_ROLE, roleParameters);
            }
        }
    }

    @Override
    public Personnel read(long codePers) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("codePers", codePers);

        return jdbcTemplate.queryForObject(SELECT_PERSONNEL_BY_CODE, namedParameters, new PersonnelRowMapper());
    }

    @Override
    public void update(Personnel personnel) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom", personnel.getNom());
        namedParameters.addValue("motPasse", passwordEncoder.encode(personnel.getMotPasse()));
        namedParameters.addValue("archive", personnel.isArchive());
        namedParameters.addValue("codePers", personnel.getCodePers());

        jdbcTemplate.update(UPDATE_PERSONNEL, namedParameters);

        for (String role : personnel.getRoles()) {
            MapSqlParameterSource roleParameters = new MapSqlParameterSource();
            roleParameters.addValue("codePers", personnel.getCodePers());
            roleParameters.addValue("codeRole", role);
            jdbcTemplate.update(UPDATE_PERSONNEL_ROLE, roleParameters);
        }
    }

    @Override
    public void delete(long codePers) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("codePers", codePers);

        jdbcTemplate.update(DELETE_PERSONNEL_ROLES, namedParameters);
        jdbcTemplate.update(DELETE_PERSONNEL, namedParameters);
    }

    @Override
    public List<Personnel> findAll() {
        return jdbcTemplate.query(SELECT_ALL_PERSONNELS, new PersonnelRowMapper());
    }

    @Override
    public List<Role> findAllRoles() {
        return jdbcTemplate.query(SELECT_ALL_ROLES, new RoleRowMapper());
    }

    class PersonnelRowMapper implements RowMapper<Personnel> {
        @Override
        public Personnel mapRow(ResultSet rs, int rowNum) throws SQLException {
            Personnel p = new Personnel();
            p.setCodePers(rs.getLong("CodePers"));
            p.setNom(rs.getString("Nom"));
            p.setMotPasse(rs.getString("MotPasse"));
            p.setRoles(List.of(rs.getString("Role")));
            p.setArchive(rs.getBoolean("Archive"));
            return p;
        }
    }

    class RoleRowMapper implements RowMapper<Role> {
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role = new Role();
            role.setCodeRole(rs.getString("CodeRole"));
            role.setDescription(rs.getString("Description"));
            return role;
        }
    }
}
