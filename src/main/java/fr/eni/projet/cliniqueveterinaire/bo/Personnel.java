package fr.eni.projet.cliniqueveterinaire.bo;

import java.util.List;
import java.util.Objects;

public class Personnel {
    private long codePers;
    private String nom;
    private String motPasse;
    private List<String> roles;  // Changement de role à roles
    private boolean archive;
    private List<RendezVous> rendezVousList;

    public Personnel(long codePers, String nom, String motPasse, List<String> roles, boolean archive, List<RendezVous> rendezVousList) {
        this.codePers = codePers;
        this.nom = nom;
        this.motPasse = motPasse;
        this.roles = roles;  // Changement de role à roles
        this.archive = archive;
        this.rendezVousList = rendezVousList;
    }

    public Personnel() {

    }

    @Override
    public String toString() {
        return "Personnel{" +
                "codePers=" + codePers +
                ", nom='" + nom + '\'' +
                ", motPasse='" + motPasse + '\'' +
                ", roles=" + roles +  // Changement de role à roles
                ", archive=" + archive +
                ", rendezVousList=" + rendezVousList +
                '}';
    }

    // Getters et Setters
    public long getCodePers() {
        return codePers;
    }

    public void setCodePers(long codePers) {
        this.codePers = codePers;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public List<String> getRoles() {  // Changement de role à roles
        return roles;
    }

    public void setRoles(List<String> roles) {  // Changement de role à roles
        this.roles = roles;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public List<RendezVous> getRendezVousList() {
        return rendezVousList;
    }

    public void setRendezVousList(List<RendezVous> rendezVousList) {
        this.rendezVousList = rendezVousList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personnel personnel = (Personnel) o;
        return codePers == personnel.codePers &&
                archive == personnel.archive &&
                Objects.equals(nom, personnel.nom) &&
                Objects.equals(motPasse, personnel.motPasse) &&
                Objects.equals(roles, personnel.roles) &&  // Changement de role à roles
                Objects.equals(rendezVousList, personnel.rendezVousList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codePers, nom, motPasse, roles, archive, rendezVousList);  // Changement de role à roles
    }
}
