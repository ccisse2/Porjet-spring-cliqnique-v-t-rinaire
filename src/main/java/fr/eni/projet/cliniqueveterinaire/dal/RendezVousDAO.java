package fr.eni.projet.cliniqueveterinaire.dal;

import fr.eni.projet.cliniqueveterinaire.bo.RendezVous;

import java.time.LocalDate;
import java.util.List;

public interface RendezVousDAO {
    //Méthode crud
    void create(RendezVous rendezVous);

    RendezVous read(long codeVeto);

    void update(RendezVous rendezVous);

    void delete(long codeVeto);

    List<RendezVous> findAll();

    List<RendezVous> findByVeterinaireAndDate(long codeVeto, LocalDate date);

    List<RendezVous> findByDate(LocalDate date);
}
