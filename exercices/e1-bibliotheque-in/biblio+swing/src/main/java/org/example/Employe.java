package org.example;

public class Employe extends personne {

    private String email;
    private String poste;

    public Employe(int id, String nom, String email, String poste) {
        super(id, nom);
        this.email = email;
        this.poste = poste;
    }

    public String getEmail() {
        return email;
    }

    public String getPoste() {
        return poste;
    }

    @Override
    public void afficherRole() {
        System.out.println("Employé : " + getNom() + " - " + poste);
    }
}