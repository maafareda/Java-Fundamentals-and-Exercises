package org.example;

public class Client extends personne {

    private String email;
    private livre[] emprunts;
    private int nbEmprunts;

    public Client(int id, String nom, String email) {
        super(id, nom);
        this.email = email;
        this.emprunts = new livre[10];
        this.nbEmprunts = 0;
    }

    public String getEmail() {
        return email;
    }

    public int getNbEmprunts() {
        return nbEmprunts;
    }

    public void emprunter(livre livre) {

        if (livre.isDisponible()) {

            if (nbEmprunts < emprunts.length) {
                emprunts[nbEmprunts] = livre;
                nbEmprunts++;

                livre.setDisponible(false);
            }
        }
    }

    @Override
    public void afficherRole() {
        System.out.println("Client : " + getNom());
    }
}