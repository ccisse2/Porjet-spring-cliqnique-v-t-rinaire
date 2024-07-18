package fr.eni.projet.cliniqueveterinaire.bll;

import fr.eni.projet.cliniqueveterinaire.bo.Personnel;
import fr.eni.projet.cliniqueveterinaire.dal.PersonnelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelServiceImpl implements PersonnelService {
    private PersonnelDAO personnelDAO;

    @Autowired
    public void setPersonnelDAO(PersonnelDAO personnelDAO) {
        this.personnelDAO = personnelDAO;
    }
    @Override
    public void ajouterPersonnel(Personnel personnel) {
        personnelDAO.create(personnel);
    }

    @Override
    public void modifierPersonnel(Personnel personnel) {
        personnelDAO.update(personnel);
    }

    @Override
    public void supprimerPersonnel(long codePers) {
        personnelDAO.delete(codePers);
    }

    @Override
    public Personnel rechercherPersonnelParId(long codePers) {
        return personnelDAO.read(codePers);
    }

    @Override
    public void reinitialiserMotPasse(long codePers, String nouveauMotPasse) {
        Personnel personnel = rechercherPersonnelParId(codePers);
        personnel.setMotPasse(nouveauMotPasse);
        modifierPersonnel(personnel);
    }

    @Override
    public List<Personnel> afficherToutPersonnel() {
        return personnelDAO.findAll();
    }
}
