package fr.eni.projet.cliniqueveterinaire.bll;

import fr.eni.projet.cliniqueveterinaire.bo.RendezVous;

import java.time.LocalDate;
import java.util.List;

public interface AgendaService {

    List<RendezVous> rechercherRdvByVetEtDate(long codeVeto, LocalDate date);

    List<RendezVous> rechercherRendezVousParDate(LocalDate date);

    void creerRendezVous(RendezVous rendezVous);

    void modifierRendezVous(RendezVous rendezVous);

    void supprimerRendezVous(long id);

    RendezVous rechercherRendezVousParId(long id);
}
