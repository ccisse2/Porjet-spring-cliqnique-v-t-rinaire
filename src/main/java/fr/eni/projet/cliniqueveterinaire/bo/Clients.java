package fr.eni.projet.cliniqueveterinaire.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clients {
    private long codeClient;
    private String nom;
    private String prenom;
    private String adresse1;
    private String adresse2;
    private String codePostal;
    private String ville;
    private String numTel;
    private String assurance;
    private String email;
    private String remarque;
    private boolean archive;
    private List<Animal> animauxList;

    public Clients(long codeClient, String nom, String prenom, String adresse1, String adresse2, String codePostal,
                   String ville, String numTel, String assurance, String email, String remarque, boolean archive, List<Animal> animauxList) {
        this.codeClient = codeClient;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.codePostal = codePostal;
        this.ville = ville;
        this.numTel = numTel;
        this.assurance = assurance;
        this.email = email;
        this.remarque = remarque;
        this.archive = archive;
        this.animauxList = animauxList;
    }

    public Clients() {

    }

    @Override
    public String toString() {
        return "Clients{" +
                "codeClient=" + codeClient +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse1='" + adresse1 + '\'' +
                ", adresse2='" + adresse2 + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", numTel='" + numTel + '\'' +
                ", assurance='" + assurance + '\'' +
                ", email='" + email + '\'' +
                ", remarque='" + remarque + '\'' +
                ", archive=" + archive +
                ", animauxList=" + animauxList +
                '}';
    }

    // Getters et Setters
    public long getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(long codeClient) {
        this.codeClient = codeClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse1() {
        return adresse1;
    }

    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAssurance() {
        return assurance;
    }

    public void setAssurance(String assurance) {
        this.assurance = assurance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public List<Animal> getAnimauxList() {
        return animauxList;
    }

    public void setAnimauxList(List<Animal> animauxList) {
        this.animauxList = animauxList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients clients = (Clients) o;
        return codeClient == clients.codeClient &&
                archive == clients.archive &&
                Objects.equals(nom, clients.nom) &&
                Objects.equals(prenom, clients.prenom) &&
                Objects.equals(adresse1, clients.adresse1) &&
                Objects.equals(adresse2, clients.adresse2) &&
                Objects.equals(codePostal, clients.codePostal) &&
                Objects.equals(ville, clients.ville) &&
                Objects.equals(numTel, clients.numTel) &&
                Objects.equals(assurance, clients.assurance) &&
                Objects.equals(email, clients.email) &&
                Objects.equals(remarque, clients.remarque) &&
                Objects.equals(animauxList, clients.animauxList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeClient, nom, prenom, adresse1, adresse2, codePostal, ville, numTel, assurance, email,
                remarque, archive, animauxList);
    }


}
