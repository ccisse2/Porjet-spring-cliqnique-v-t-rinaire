package fr.eni.projet.cliniqueveterinaire.bll;

import fr.eni.projet.cliniqueveterinaire.bo.Personnel;
import fr.eni.projet.cliniqueveterinaire.bo.Role;

import java.util.List;

public interface PersonnelService {
    void ajouterPersonnel(Personnel personnel);

    void modifierPersonnel(Personnel personnel);

    void supprimerPersonnel(long codePers);

    Personnel rechercherPersonnelParId(long codePers);

    void reinitialiserMotPasse(long codePers, String nouveauMotPasse);

    List<Personnel> afficherToutPersonnel();

    List<Role> consulterTousRoles();
}
