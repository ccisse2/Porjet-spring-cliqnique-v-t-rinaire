package fr.eni.projet.cliniqueveterinaire.bo;

import java.time.LocalDate;
import java.util.Objects;

public class RendezVous {
    private long codeVeto;
    private LocalDate dateRdv;
    private Animal animal;
    private Personnel veterinaire;

    public RendezVous(long codeVeto, LocalDate dateRdv, Animal animal, Personnel veterinaire) {
        this.codeVeto = codeVeto;
        this.dateRdv = dateRdv;
        this.animal = animal;
        this.veterinaire = veterinaire;
    }

    @Override
    public String toString() {
        return "RendezVous{" +
                "codeVeto=" + codeVeto +
                ", dateRdv=" + dateRdv +
                ", animal=" + animal +
                ", veterinaire=" + veterinaire +
                '}';
    }

    // Getters et Setters
    public long getCodeVeto() {
        return codeVeto;
    }

    public void setCodeVeto(long codeVeto) {
        this.codeVeto = codeVeto;
    }

    public LocalDate getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(LocalDate dateRdv) {
        this.dateRdv = dateRdv;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Personnel getVeterinaire() {
        return veterinaire;
    }

    public void setVeterinaire(Personnel veterinaire) {
        this.veterinaire = veterinaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RendezVous that = (RendezVous) o;
        return codeVeto == that.codeVeto &&
                Objects.equals(dateRdv, that.dateRdv) &&
                Objects.equals(animal, that.animal) &&
                Objects.equals(veterinaire, that.veterinaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeVeto, dateRdv, animal, veterinaire);
    }
}
