package fr.eni.projet.cliniqueveterinaire.bll;

import fr.eni.projet.cliniqueveterinaire.bo.Clients;
import fr.eni.projet.cliniqueveterinaire.bo.Animal;

import java.util.List;

public interface ClientsService {
    void ajouterClient(Clients client);

    void modifierClient(Clients client);

    void supprimerClient(long codeCli);

    Clients rechercherClientParId(long codeCli);

    List<Clients> rechercherClientsParNom(String nom);

    void modifierAnimal(Animal animal, long codeClient);

    void supprimerAnimal(long codeAnimal);
}
