package fr.eni.projet.cliniqueveterinaire.dal;

import fr.eni.projet.cliniqueveterinaire.bo.RendezVous;

public interface RendezVousDAO {
    //Méthode crud
    void create(RendezVous rendezVous);

    RendezVous read(long codeVeto);

    void update(RendezVous rendezVous);

    void delete(long codeVeto);
}
