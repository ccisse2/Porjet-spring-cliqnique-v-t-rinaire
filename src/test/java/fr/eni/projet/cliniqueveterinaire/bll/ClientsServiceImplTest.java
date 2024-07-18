package fr.eni.projet.cliniqueveterinaire.bll;

import fr.eni.projet.cliniqueveterinaire.bo.Animal;
import fr.eni.projet.cliniqueveterinaire.bo.Clients;
import fr.eni.projet.cliniqueveterinaire.dal.AnimalDAO;
import fr.eni.projet.cliniqueveterinaire.dal.ClientsDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ClientsServiceImplTest {

    @Mock
    private ClientsDAO clientsDAO;

    @Mock
    private AnimalDAO animalDAO;

    @InjectMocks
    private ClientsServiceImpl clientsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterClient() {
        Clients client = new Clients();
        clientsService.ajouterClient(client);
        verify(clientsDAO, times(1)).create(client);
    }

    @Test
    void testModifierClient() {
        Clients client = new Clients();
        clientsService.modifierClient(client);
        verify(clientsDAO, times(1)).update(client);
    }

    @Test
    void testSupprimerClient() {
        long codeCli = 1L;
        clientsService.supprimerClient(codeCli);
        verify(clientsDAO, times(1)).delete(codeCli);
    }

    @Test
    void testRechercherClientParId() {
        long codeCli = 1L;
        when(clientsDAO.read(codeCli)).thenReturn(new Clients());
        Clients client = clientsService.rechercherClientParId(codeCli);
        verify(clientsDAO, times(1)).read(codeCli);
        assertNotNull(client);
    }

    @Test
    void testRechercherClientsParNom() {
        String nom = "Doe";
        when(clientsDAO.findByNom(nom)).thenReturn(List.of(new Clients()));
        List<Clients> clients = clientsService.rechercherClientsParNom(nom);
        verify(clientsDAO, times(1)).findByNom(nom);
        assertFalse(clients.isEmpty());
    }

    @Test
    void testModifierAnimal() {
        long codeClient = 1L;
        Animal animal = new Animal();
        animal.setCodeAnimal(1L);
        when(animalDAO.read(animal.getCodeAnimal())).thenReturn(animal);
        animal.setClient(new Clients());
        animal.getClient().setCodeClient(codeClient);
        clientsService.modifierAnimal(animal, codeClient);
        verify(animalDAO, times(1)).update(animal);
    }

    @Test
    void testSupprimerAnimal() {
        long codeAnimal = 1L;
        Animal animal = new Animal();
        animal.setCodeAnimal(codeAnimal);
        when(animalDAO.read(codeAnimal)).thenReturn(animal);
        clientsService.supprimerAnimal(codeAnimal);
        verify(animalDAO, times(1)).update(animal);
        assertTrue(animal.isArchive());
    }
}
