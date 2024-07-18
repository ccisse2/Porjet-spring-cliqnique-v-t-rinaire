package fr.eni.projet.cliniqueveterinaire.dal;

import fr.eni.projet.cliniqueveterinaire.bo.RendezVous;
import fr.eni.projet.cliniqueveterinaire.bo.Animal;
import fr.eni.projet.cliniqueveterinaire.bo.Personnel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RendezVousDaoImpl implements RendezVousDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private AnimalDAO animalDAO;
    private PersonnelDAO personnelDAO;

    private static final String INSERT_RENDEZVOUS = "INSERT INTO RendezVous (CodeAnimal, CodeVeto, DateRdv) " +
            "VALUES (:codeAnimal, :codeVeto, :dateRdv)";
    private static final String SELECT_RENDEZVOUS_BY_CODE = "SELECT CodeAnimal, CodeVeto, DateRdv FROM RendezVous WHERE CodeVeto = :codeVeto";
    private static final String SELECT_ALL_RENDEZVOUS = "SELECT CodeAnimal, CodeVeto, DateRdv FROM RendezVous";
    private static final String DELETE_RENDEZVOUS = "DELETE FROM RendezVous WHERE CodeVeto = :codeVeto";
    private static final String UPDATE_RENDEZVOUS = "UPDATE RendezVous SET CodeAnimal = :codeAnimal, DateRdv = :dateRdv WHERE CodeVeto = :codeVeto";

    private static final String SELECT_BY_VETERINAIRE_AND_DATE =
            "SELECT CodeAnimal, CodeVeto, DateRdv FROM RendezVous WHERE CodeVeto = :codeVeto AND DateRdv = :dateRdv";

    private static final String SELECT_BY_DATE =
            "SELECT CodeAnimal, CodeVeto, DateRdv FROM RendezVous WHERE DateRdv = :dateRdv";

    public RendezVousDaoImpl(NamedParameterJdbcTemplate jdbcTemplate, AnimalDAO animalDAO, PersonnelDAO personnelDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.animalDAO = animalDAO;
        this.personnelDAO = personnelDAO;
    }

    @Override
    public void create(RendezVous rendezVous) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("codeAnimal", rendezVous.getAnimal().getCodeAnimal());
        namedParameters.addValue("codeVeto", rendezVous.getVeterinaire().getCodePers());
        namedParameters.addValue("dateRdv", rendezVous.getDateRdv());

        jdbcTemplate.update(INSERT_RENDEZVOUS, namedParameters, keyHolder);
    }

    @Override
    public RendezVous read(long codeVeto) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("codeVeto", codeVeto);

        return jdbcTemplate.queryForObject(SELECT_RENDEZVOUS_BY_CODE, namedParameters, new RendezVousRowMapper(animalDAO, personnelDAO));
    }

    @Override
    public void update(RendezVous rendezVous) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("codeAnimal", rendezVous.getAnimal().getCodeAnimal());
        namedParameters.addValue("dateRdv", rendezVous.getDateRdv());
        namedParameters.addValue("codeVeto", rendezVous.getVeterinaire().getCodePers());

        jdbcTemplate.update(UPDATE_RENDEZVOUS, namedParameters);
    }

    @Override
    public void delete(long codeVeto) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("codeVeto", codeVeto);

        jdbcTemplate.update(DELETE_RENDEZVOUS, namedParameters);
    }

    @Override
    public List<RendezVous> findAll() {
        return jdbcTemplate.query(SELECT_ALL_RENDEZVOUS, new RendezVousRowMapper(animalDAO, personnelDAO));
    }

    @Override
    public List<RendezVous> findByVeterinaireAndDate(long codeVeto, LocalDate date) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("codeVeto", codeVeto);
        params.addValue("dateRdv", date);

        return jdbcTemplate.query(SELECT_BY_VETERINAIRE_AND_DATE, params, new RendezVousRowMapper(animalDAO, personnelDAO));
    }

    @Override
    public List<RendezVous> findByDate(LocalDate date) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dateRdv", date);

        return jdbcTemplate.query(SELECT_BY_DATE, params, new RendezVousRowMapper(animalDAO, personnelDAO));
    }

    class RendezVousRowMapper implements RowMapper<RendezVous> {
        private AnimalDAO animalDAO;
        private PersonnelDAO personnelDAO;

        public RendezVousRowMapper(AnimalDAO animalDAO, PersonnelDAO personnelDAO) {
            this.animalDAO = animalDAO;
            this.personnelDAO = personnelDAO;
        }

        @Override
        public RendezVous mapRow(ResultSet rs, int rowNum) throws SQLException {
            RendezVous rendezVous = new RendezVous();
            rendezVous.setCodeVeto(rs.getLong("CodeVeto"));
            rendezVous.setDateRdv(rs.getDate("DateRdv").toLocalDate());

            long codeAnimal = rs.getLong("CodeAnimal");
            rendezVous.setAnimal(animalDAO.read(codeAnimal));

            long codePers = rs.getLong("CodeVeto");
            rendezVous.setVeterinaire(personnelDAO.read(codePers));

            return rendezVous;
        }
    }
}

