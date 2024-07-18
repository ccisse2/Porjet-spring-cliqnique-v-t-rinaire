package fr.eni.projet.cliniqueveterinaire.dal;

import fr.eni.projet.cliniqueveterinaire.bo.Animal;
import fr.eni.projet.cliniqueveterinaire.bo.Clients;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientsDaoImpl implements ClientsDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String INSERT_CLIENT = "INSERT INTO Clients (Nom, Prenom, Adresse1, Adresse2, Ville, CodePostal, " +
            "NumTel, Assurance, Email, Remarque, Archive) VALUES (:nom, :prenom, :adresse1, :adresse2, :ville, :codePostal, :numTel," +
            " :assurance, :email, :remarque, :archive)";

    private static final String SELECT_CLIENT_BY_CODE = "SELECT CodeClient, Nom, Prenom, Adresse1, Adresse2, Ville, " +
            "CodePostal, NumTel, Email, Assurance, Remarque, Archive " +
            "FROM Clients WHERE CodeClient = :codeClient";

    private static final String SELECT_ALL_CLIENTS = "SELECT CodeClient, Nom, Prenom, Adresse1, Adresse2, Ville, " +
            "CodePostal, NumTel, Assurance, Email, Remarque, Archive " +
            "FROM Clients";

    private static final String DELETE_CLIENT = "DELETE FROM Clients WHERE CodeClient = :codeClient";

    private static final String UPDATE_CLIENT = "UPDATE Clients SET Nom = :nom, Prenom = :prenom, Adresse1 = :adresse1, " +
            "Adresse2 = :adresse2, Ville = :ville," +
            "CodePostal = :codePostal, NumTel = :numTel, Assurance = :assurance, Email = :email, Remarque = :remarque, " +
            "Archive = :archive WHERE CodeClient = :codeClient";

    private static final String SELECT_CLIENTS_BY_NOM = "SELECT  CodeClient, Nom, Prenom, Adresse1, Adresse2, Ville," +
            "CodePostal, NumTel, Assurance, Email, Remarque, Archive FROM Clients WHERE Nom = :nom";

    public ClientsDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Clients client) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom", client.getNom());
        namedParameters.addValue("prenom", client.getPrenom());
        namedParameters.addValue("adresse1", client.getAdresse1());
        namedParameters.addValue("adresse2", client.getAdresse2());
        namedParameters.addValue("ville", client.getVille());
        namedParameters.addValue("codePostal", client.getCodePostal());
        namedParameters.addValue("numTel", client.getNumTel());
        namedParameters.addValue("assurance", client.getAssurance());
        namedParameters.addValue("email", client.getEmail());
        namedParameters.addValue("remarque", client.getRemarque());
        namedParameters.addValue("archive", client.isArchive());

        jdbcTemplate.update(INSERT_CLIENT, namedParameters, keyHolder);

        if (keyHolder.getKey() != null) {
            client.setCodeClient(keyHolder.getKey().longValue());
        }
    }

    @Override
    public Clients read(long codeClient) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("codeClient", codeClient);

        return jdbcTemplate.queryForObject(SELECT_CLIENT_BY_CODE, namedParameters, new ClientsRowMapper());
    }

    @Override
    public void update(Clients client) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom", client.getNom());
        namedParameters.addValue("prenom", client.getPrenom());
        namedParameters.addValue("adresse1", client.getAdresse1());
        namedParameters.addValue("adresse2", client.getAdresse2());
        namedParameters.addValue("ville", client.getVille());
        namedParameters.addValue("codePostal", client.getCodePostal());
        namedParameters.addValue("numTel", client.getNumTel());
        namedParameters.addValue("assurance", client.getAssurance());
        namedParameters.addValue("email", client.getEmail());
        namedParameters.addValue("remarque", client.getRemarque());
        namedParameters.addValue("archive", client.isArchive());
        namedParameters.addValue("codeClient", client.getCodeClient());

        jdbcTemplate.update(UPDATE_CLIENT, namedParameters);
    }

    @Override
    public void delete(long codeClient) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("codeClient", codeClient);

        jdbcTemplate.update(DELETE_CLIENT, namedParameters);
    }

    @Override
    public List<Clients> findByNom(String nom) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom", nom);

        return jdbcTemplate.query(SELECT_CLIENTS_BY_NOM, namedParameters, new ClientsRowMapper());
    }

    @Override
    public List<Clients> findAll() {
        return jdbcTemplate.query(SELECT_ALL_CLIENTS, new ClientsRowMapper());
    }

    class ClientsRowMapper implements RowMapper<Clients> {
        @Override
        public Clients mapRow(ResultSet rs, int rowNum) throws SQLException {
            Clients c = new Clients();
            c.setCodeClient(rs.getLong("CodeClient"));
            c.setNom(rs.getString("Nom"));
            c.setPrenom(rs.getString("Prenom"));
            c.setAdresse1(rs.getString("Adresse1"));
            c.setAdresse2(rs.getString("Adresse2"));
            c.setVille(rs.getString("Ville"));
            c.setCodePostal(rs.getString("CodePostal"));
            c.setNumTel(rs.getString("NumTel"));
            c.setEmail(rs.getString("Email"));
            c.setAssurance(rs.getString("Assurance"));
            c.setRemarque(rs.getString("Remarque"));
            c.setArchive(rs.getBoolean("Archive"));
            return c;
        }
    }
}
