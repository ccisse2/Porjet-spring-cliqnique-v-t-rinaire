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

public class AnimalDaoImpl implements AnimalDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String INSERT_ANIMAL =
            "INSERT INTO ANIMAUX (NomAnimal, Sexe, Couleur, Race, Espece, CodeClient, Tatouage, Antecedents, Archive) " +
                    "VALUES (:nomAnimal, :sexeAnimal, :couleur, :race, :espece, :codeClient, :tatouage, :antecedents, :archive)";

    private static final String SELECT_ANIMAL_BY_CODE =
            "SELECT a.CodeAnimal, a.NomAnimal, a.Sexe, a.Couleur, a.Race, a.Espece, a.Tatouage, a.Antecedents, c.Nom AS " +
                    "NomClient, c.Prenom AS PrenomClient " +
                    "FROM ANIMAUX a " +
                    "INNER JOIN CLIENTS c ON a.CodeClient = c.CodeClient " +
                    "WHERE a.CodeAnimal = :codeAnimal";

    private static final String SELECT_ALL_ANIMALS =
            "SELECT a.CodeAnimal, a.NomAnimal, a.Sexe, a.Couleur, a.Race, a.Espece, a.Tatouage, a.Antecedents, c.Nom AS " +
                    "NomClient, c.Prenom AS PrenomClient " +
                    "FROM ANIMAUX a " +
                    "INNER JOIN CLIENTS c ON a.CodeClient = c.CodeClient";

    private static final String DELETE_ANIMAL = "DELETE FROM Animaux WHERE CodeAnimal = :codeAnimal";

    private static final String UPDATE_ANIMAL =
            "UPDATE ANIMAUX SET NomAnimal = :nomAnimal, Sexe = :sexeAnimal, Couleur = :couleur, Race = :race, " +
                    "Espece = :espece, CodeClient = :codeClient, Tatouage = :tatouage, Antecedents = :antecedents, " +
                    "Archive = :archive " +
                    "WHERE CodeAnimal = :codeAnimal";

    private static final String SELECT_ALL_ANIMALS_BY_CLIENT = "SELECT Animal.CodeAnimal, Animal.Nom, Animal.Prenom, Animal.Race, Animal.Couleur, Animal.DateNaissance " +
            "FROM Clients INNER JOIN Animal ON Clients.CodeClient = Animal.CodeClient WHERE Clients.CodeClient = :codeClient";

    public AnimalDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Animal animal) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nomAnimal", animal.getNomAnimal());
        namedParameters.addValue("sexeAnimal", animal.getSexeAnimal());
        namedParameters.addValue("couleur", animal.getCouleur());
        namedParameters.addValue("race", animal.getRace());
        namedParameters.addValue("espece", animal.getEspece());
        namedParameters.addValue("codeClient", animal.getClient().getCodeClient());
        namedParameters.addValue("tatouage", animal.getTatouage());
        namedParameters.addValue("antecedents", animal.getAntecedents());
        namedParameters.addValue("archive", animal.isArchive());

        jdbcTemplate.update(INSERT_ANIMAL, namedParameters, keyHolder);
        if (keyHolder.getKey() != null) {
            animal.setCodeAnimal(keyHolder.getKey().longValue());
        }
    }

    @Override
    public Animal read(long codeAnimal) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("codeAnimal", codeAnimal);

        return jdbcTemplate.queryForObject(SELECT_ANIMAL_BY_CODE, namedParameters, new AnimalRowMapper());
    }

    @Override
    public List<Animal> findAll() {
        return jdbcTemplate.query(SELECT_ALL_ANIMALS, new AnimalRowMapper());
    }

    @Override
    public void update(Animal animal) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nomAnimal", animal.getNomAnimal());
        namedParameters.addValue("sexeAnimal", animal.getSexeAnimal());
        namedParameters.addValue("couleur", animal.getCouleur());
        namedParameters.addValue("race", animal.getRace());
        namedParameters.addValue("espece", animal.getEspece());
        namedParameters.addValue("codeClient", animal.getClient().getCodeClient());
        namedParameters.addValue("tatouage", animal.getTatouage());
        namedParameters.addValue("antecedents", animal.getAntecedents());
        namedParameters.addValue("archive", animal.isArchive());
        namedParameters.addValue("codeAnimal", animal.getCodeAnimal());

        jdbcTemplate.update(UPDATE_ANIMAL, namedParameters);
    }

    @Override
    public void delete(Animal animal) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("codeAnimal", animal.getCodeAnimal());

        jdbcTemplate.update(DELETE_ANIMAL, namedParameters);
    }

    @Override
    public List<Animal> findAnimalsClient(long codeClient) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("codeClient", codeClient);

        return jdbcTemplate.query(SELECT_ALL_ANIMALS_BY_CLIENT, namedParameters, new AnimalRowMapper());
    }

     class AnimalRowMapper implements RowMapper<Animal> {

        @Override
        public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
            Animal a = new Animal();
            a.setCodeAnimal(rs.getLong("CodeAnimal"));
            a.setNomAnimal(rs.getString("NomAnimal"));
            a.setSexeAnimal(rs.getString("Sexe"));
            a.setCouleur(rs.getString("Couleur"));
            a.setRace(rs.getString("Race"));
            a.setEspece(rs.getString("Espece"));
            Clients c = new Clients();
            c.setNom(rs.getString("NomClient"));
            c.setPrenom(rs.getString("PrenomClient"));
            a.setClient(c);
            a.setTatouage(rs.getString("Tatouage"));
            a.setAntecedents(rs.getString("Antecedents"));
            a.setArchive(rs.getBoolean("Archive"));
            return a;
        }
    }
}
