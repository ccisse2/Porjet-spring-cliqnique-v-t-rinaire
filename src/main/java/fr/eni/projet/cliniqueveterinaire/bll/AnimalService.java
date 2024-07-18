package fr.eni.projet.cliniqueveterinaire.bll;

import fr.eni.projet.cliniqueveterinaire.bo.Animal;

import java.util.List;

public interface AnimalService {

    void ajouterAnimal(Animal animal);

    void modifierAnimal(Animal animal);

    void supprimerAnimal(long codeAnimal);

    Animal rechercherAnimalParId(long codeAnimal);

    List<Animal> rechercherTousLesAnimaux();

    List<Animal> rechercherAnimauxParClient(long codeClient);
}
