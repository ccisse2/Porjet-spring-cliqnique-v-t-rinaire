package fr.eni.projet.cliniqueveterinaire.bll;

import fr.eni.projet.cliniqueveterinaire.bo.RendezVous;
import fr.eni.projet.cliniqueveterinaire.dal.RendezVousDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AgendaServiceImpl implements AgendaService {

    private final RendezVousDAO rendezVousDAO;

    @Autowired
    public AgendaServiceImpl(RendezVousDAO rendezVousDAO) {
        this.rendezVousDAO = rendezVousDAO;
    }

    @Override
    public List<RendezVous> rechercherRdvByVetEtDate(long codeVeto, LocalDate date) {
        return rendezVousDAO.findByVeterinaireAndDate(codeVeto, date);
    }

    @Override
    public List<RendezVous> rechercherRendezVousParDate(LocalDate date) {
        return rendezVousDAO.findByDate(date);
    }

    @Override
    public void creerRendezVous(RendezVous rendezVous) {
        rendezVousDAO.create(rendezVous);
    }

    @Override
    public void modifierRendezVous(RendezVous rendezVous) {
        rendezVousDAO.update(rendezVous);
    }

    @Override
    public void supprimerRendezVous(long id) {
        rendezVousDAO.delete(id);
    }

    @Override
    public RendezVous rechercherRendezVousParId(long id) {
        return rendezVousDAO.read(id);
    }
}
