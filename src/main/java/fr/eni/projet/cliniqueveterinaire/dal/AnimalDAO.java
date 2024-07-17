package fr.eni.projet.cliniqueveterinaire.dal;

import fr.eni.projet.cliniqueveterinaire.bo.Animal;

import java.util.List;

public interface AnimalDAO {
    void create(Animal animal);

    Animal read(long codeAnimal);

    List<Animal> findAll();

    void update(Animal animal);

    void delete(Animal animal);
}
