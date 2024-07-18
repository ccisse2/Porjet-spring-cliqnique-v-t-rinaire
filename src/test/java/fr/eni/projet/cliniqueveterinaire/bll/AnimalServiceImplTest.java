package fr.eni.projet.cliniqueveterinaire.bll;

import fr.eni.projet.cliniqueveterinaire.bo.Animal;
import fr.eni.projet.cliniqueveterinaire.dal.AnimalDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnimalServiceImplTest {

    @Mock
    private AnimalDAO animalDAO;

    @InjectMocks
    private AnimalServiceImpl animalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterAnimal() {
        Animal animal = new Animal();
        animalService.ajouterAnimal(animal);
        verify(animalDAO, times(1)).create(animal);
    }

    @Test
    void testModifierAnimal() {
        Animal animal = new Animal();
        animalService.modifierAnimal(animal);
        verify(animalDAO, times(1)).update(animal);
    }

    @Test
    void testSupprimerAnimal() {
        long codeAnimal = 1L;
        animalService.supprimerAnimal(codeAnimal);
        verify(animalDAO, times(1)).delete(animalService.rechercherAnimalParId(codeAnimal));
    }

    @Test
    void testRechercherAnimalParId() {
        long codeAnimal = 1L;
        when(animalDAO.read(codeAnimal)).thenReturn(new Animal());
        Animal animal = animalService.rechercherAnimalParId(codeAnimal);
        verify(animalDAO, times(1)).read(codeAnimal);
        assertNotNull(animal);
    }

    @Test
    void testRechercherTousLesAnimaux() {
        when(animalDAO.findAll()).thenReturn(List.of(new Animal()));
        List<Animal> animaux = animalService.rechercherTousLesAnimaux();
        verify(animalDAO, times(1)).findAll();
        assertFalse(animaux.isEmpty());
    }
}
