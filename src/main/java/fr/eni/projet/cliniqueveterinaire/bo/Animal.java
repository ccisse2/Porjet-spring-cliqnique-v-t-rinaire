package fr.eni.projet.cliniqueveterinaire.bo;

import java.util.Objects;

public class Animal {
    private long codeAnimal;
    private String nomAnimal;
    private String sexeAnimal;
    private String couleur;
    private String race;
    private String espece;
    private Clients client; // Référence au propriétaire
    private String tatouage;
    private String antecedents;
    private boolean archive;

    public Animal(long codeAnimal, String nomAnimal, String sexeAnimal, String couleur, String race, String espece,
                   Clients client, String tatouage, String antecedents, boolean archive) {
        this.codeAnimal = codeAnimal;
        this.nomAnimal = nomAnimal;
        this.sexeAnimal = sexeAnimal;
        this.couleur = couleur;
        this.race = race;
        this.espece = espece;
        this.client = client;
        this.tatouage = tatouage;
        this.antecedents = antecedents;
        this.archive = archive;
    }

    public Animal() {
    }

    @Override
    public String toString() {
        return "Animaux{" +
                "codeAnimal=" + codeAnimal +
                ", nomAnimal='" + nomAnimal + '\'' +
                ", sexeAnimal='" + sexeAnimal + '\'' +
                ", couleur='" + couleur + '\'' +
                ", race='" + race + '\'' +
                ", espece='" + espece + '\'' +
                ", client=" + client +
                ", tatouage='" + tatouage + '\'' +
                ", antecedents='" + antecedents + '\'' +
                ", archive=" + archive +
                '}';
    }

    // Getters et Setters
    public long getCodeAnimal() {
        return codeAnimal;
    }

    public void setCodeAnimal(long codeAnimal) {
        this.codeAnimal = codeAnimal;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }

    public String getSexeAnimal() {
        return sexeAnimal;
    }

    public void setSexeAnimal(String sexeAnimal) {
        this.sexeAnimal = sexeAnimal;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public String getTatouage() {
        return tatouage;
    }

    public void setTatouage(String tatouage) {
        this.tatouage = tatouage;
    }

    public String getAntecedents() {
        return antecedents;
    }

    public void setAntecedents(String antecedents) {
        this.antecedents = antecedents;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animaux = (Animal) o;
        return codeAnimal == animaux.codeAnimal &&
                archive == animaux.archive &&
                Objects.equals(nomAnimal, animaux.nomAnimal) &&
                Objects.equals(sexeAnimal, animaux.sexeAnimal) &&
                Objects.equals(couleur, animaux.couleur) &&
                Objects.equals(race, animaux.race) &&
                Objects.equals(espece, animaux.espece) &&
                Objects.equals(client, animaux.client) &&
                Objects.equals(tatouage, animaux.tatouage) &&
                Objects.equals(antecedents, animaux.antecedents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeAnimal, nomAnimal, sexeAnimal, couleur, race, espece, client, tatouage, antecedents, archive);
    }
}