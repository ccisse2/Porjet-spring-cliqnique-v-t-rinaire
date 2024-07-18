package fr.eni.projet.cliniqueveterinaire.dal;

import fr.eni.projet.cliniqueveterinaire.bo.Personnel;
import fr.eni.projet.cliniqueveterinaire.bo.Role;

import java.util.List;

public interface PersonnelDAO {
    //Méthode crud
    void create(Personnel personnel);

    Personnel read(long codePers);

    void update(Personnel personnel);

    void delete(long codeClient);

    //Méthode pour récupérer tous les personnels
    List<Personnel> findAll();

    List<Role> findAllRoles();
}
