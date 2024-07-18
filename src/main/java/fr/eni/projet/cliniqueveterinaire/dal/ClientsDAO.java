package fr.eni.projet.cliniqueveterinaire.dal;

import fr.eni.projet.cliniqueveterinaire.bo.Clients;

import java.util.List;

public interface ClientsDAO {
    // CRUD operations on clients table
    void create(Clients client);

    Clients read(long codeClient);

    void update(Clients client);

    void delete(long codeClient);

    List<Clients> findByNom(String nom);

    List<Clients> findAll();
}
