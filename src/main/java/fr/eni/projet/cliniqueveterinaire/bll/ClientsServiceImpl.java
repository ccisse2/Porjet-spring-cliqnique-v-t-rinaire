package fr.eni.projet.cliniqueveterinaire.bll;

import fr.eni.projet.cliniqueveterinaire.bo.Animal;
import fr.eni.projet.cliniqueveterinaire.bo.Clients;
import fr.eni.projet.cliniqueveterinaire.dal.AnimalDAO;
import fr.eni.projet.cliniqueveterinaire.dal.ClientsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientsServiceImpl implements ClientsService {
    private ClientsDAO clientsDAO;
    private AnimalDAO animalDAO;

    @Autowired
    public ClientsServiceImpl(ClientsDAO clientsDAO, AnimalDAO animalDAO) {
        this.clientsDAO = clientsDAO;
        this.animalDAO = animalDAO;
    }

    @Override
    public void ajouterClient(Clients client) {
        clientsDAO.create(client);
    }

    @Override
    public void modifierClient(Clients client) {
        clientsDAO.update(client);
    }

    @Override
    public void supprimerClient(long codeCli) {
        clientsDAO.delete(codeCli);
    }

    @Override
    public Clients rechercherClientParId(long codeCli) {
        return clientsDAO.read(codeCli);
    }

    @Override
    public List<Clients> rechercherClientsParNom(String nom) {
        return clientsDAO.findByNom(nom);
    }


    @Override
    public void modifierAnimal(Animal animal, long codeClient) {
        // Vérifier si l'animal appartient bien au client
        Animal existingAnimal = animalDAO.read(animal.getCodeAnimal());
        if (existingAnimal != null && existingAnimal.getClient().getCodeClient() == codeClient) {
            // Mettre à jour l'animal
            animal.setClient(new Clients());
            animal.getClient().setCodeClient(codeClient);
            animalDAO.update(animal);
        } else {
            throw new IllegalArgumentException("Animal non trouvé ou n'appartenant pas au client spécifié");
        }
    }

    @Override
    public void supprimerAnimal(long codeAnimal) {
        // Suppression (archivage) de l'animal
        Animal animal = animalDAO.read(codeAnimal);
        if (animal != null) {
            animal.setArchive(true);
            animalDAO.update(animal);
        }
    }
}

