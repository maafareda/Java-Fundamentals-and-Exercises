package org.example;

public class Livre {

    private int numero;
    private String titre;
    private String auteur;
    private boolean disponible;

    public Livre(int numero, String titre, String auteur) {
        this.numero = numero;
        this.titre = titre;
        this.auteur = auteur;
        this.disponible = true;
    }

    public int getNumero() {
        return numero;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return numero + " - " + titre + " - " + auteur +
                " - " + (disponible ? "Disponible" : "Emprunté");
    }
}