package fr.eni.projet.cliniqueveterinaire.dal;

import fr.eni.projet.cliniqueveterinaire.bo.Personnel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonnelDaoImpl implements PersonnelDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String INSERT_PERSONNEL = "INSERT INTO Personnels (Nom, MotPasse, Role, Archive) " +
            "VALUES (:nom, :motPasse, :role, :archive)";
    private static final String SELECT_PERSONNEL_BY_CODE = "SELECT CodePers, Nom, MotPasse, Role, Archive FROM Personnels WHERE CodePers = :codePers";
    private static final String SELECT_ALL_PERSONNELS = "SELECT CodePers, Nom, MotPasse, Role, Archive FROM Personnels";
    private static final String DELETE_PERSONNEL = "DELETE FROM Personnels WHERE CodePers = :codePers";
    private static final String UPDATE_PERSONNEL = "UPDATE Personnels SET Nom = :nom, MotPasse = :motPasse, Role = :role, Archive = :archive WHERE CodePers = :codePers";

    public PersonnelDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Personnel personnel) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom", personnel.getNom());
        namedParameters.addValue("motPasse", personnel.getMotPasse());
        namedParameters.addValue("role", personnel.getRole());
        namedParameters.addValue("archive", personnel.isArchive());

        jdbcTemplate.update(INSERT_PERSONNEL, namedParameters, keyHolder);

        if (keyHolder.getKey() != null) {
            personnel.setCodePers(keyHolder.getKey().longValue());
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
        namedParameters.addValue("motPasse", personnel.getMotPasse());
        namedParameters.addValue("role", personnel.getRole());
        namedParameters.addValue("archive", personnel.isArchive());
        namedParameters.addValue("codePers", personnel.getCodePers());

        jdbcTemplate.update(UPDATE_PERSONNEL, namedParameters);
    }

    @Override
    public void delete(long codePers) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("codePers", codePers);

        jdbcTemplate.update(DELETE_PERSONNEL, namedParameters);
    }

    @Override
    public List<Personnel> findAll() {
        return jdbcTemplate.query(SELECT_ALL_PERSONNELS, new PersonnelRowMapper());
    }

    class PersonnelRowMapper implements RowMapper<Personnel> {
        @Override
        public Personnel mapRow(ResultSet rs, int rowNum) throws SQLException {
            Personnel p = new Personnel();
            p.setCodePers(rs.getLong("CodePers"));
            p.setNom(rs.getString("Nom"));
            p.setMotPasse(rs.getString("MotPasse"));
            p.setRole(rs.getString("Role"));
            p.setArchive(rs.getBoolean("Archive"));
            return p;
        }
    }
}

