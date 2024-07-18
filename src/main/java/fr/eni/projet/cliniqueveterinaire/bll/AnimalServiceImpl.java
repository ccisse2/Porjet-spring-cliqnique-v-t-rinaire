package fr.eni.projet.cliniqueveterinaire.bll;

import fr.eni.projet.cliniqueveterinaire.bo.Animal;
import fr.eni.projet.cliniqueveterinaire.dal.AnimalDAO;

import java.util.List;

public class AnimalServiceImpl implements AnimalService{
    private AnimalDAO animalDAO;

    @Override
    public void ajouterAnimal(Animal animal) {
        animalDAO.create(animal);
    }

    @Override
    public void modifierAnimal(Animal animal) {
        animalDAO.update(animal);
    }

    @Override
    public void supprimerAnimal(long codeAnimal) {
        animalDAO.delete(rechercherAnimalParId(codeAnimal));
    }

    @Override
    public Animal rechercherAnimalParId(long codeAnimal) {
        return animalDAO.read(codeAnimal);
    }

    @Override
    public List<Animal> rechercherTousLesAnimaux() {
        return animalDAO.findAll();
    }

    @Override
    public List<Animal> rechercherAnimauxParClient(long codeClient) {
        return animalDAO.findAnimalsClient(codeClient);
    }
}
