package org.example;

public class Bibliotheque {

    private livre[] livres;
    private Client[] clients;
    private Employe[] employes;

    private int nbLivres;
    private int nbClients;
    private int nbEmployes;

    public Bibliotheque() {
        livres = new livre[100];
        clients = new Client[100];
        employes = new Employe[100];

        nbLivres = 0;
        nbClients = 0;
        nbEmployes = 0;
    }

    public void ajouterLivre(Livre livre) {
        livres[nbLivres] = livre;
        nbLivres++;
    }

    public void ajouterClient(Client client) {
        clients[nbClients] = client;
        nbClients++;
    }

    public void ajouterEmploye(Employe employe) {
        employes[nbEmployes] = employe;
        nbEmployes++;
    }

    public void afficherLivresDisponibles() {

        System.out.println("\n=== Livres disponibles ===");

        for (int i = 0; i < nbLivres; i++) {

            if (livres[i].isDisponible()) {
                System.out.println(livres[i]);
            }
        }
    }

    public livre getLivreParNumero(int numero) {

        for (int i = 0; i < nbLivres; i++) {

            if (livres[i].getNumero() == numero
                    && livres[i].isDisponible()) {

                return livres[i];
            }
        }

        return null;
    }

    public livre[] getLivres() {
        return livres;
    }

    public int getNbLivres() {
        return nbLivres;
    }
}