package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Bibliotheque b = new Bibliotheque();

        // Livres
        b.ajouterLivre(new livre(1, "Le Petit Prince", "Saint-Exupéry"));
        b.ajouterLivre(new livre(2, "1984", "George Orwell"));
        b.ajouterLivre(new livre(3, "L'Étranger", "Albert Camus"));

        // Client
        Client client = new Client(1, "Jean Dupont", "jean@email.com");
        b.ajouterClient(client);

        // Employé
        Employe employe = new Employe(
                1, "Marie Martin", "marie@bibliotheque.fr", "Bibliothécaire"
        );
        b.ajouterEmploye(employe);

        client.afficherRole();
        employe.afficherRole();

        Scanner sc = new Scanner(System.in);

        while (true) {

            b.afficherLivresDisponibles();

            System.out.print("Numéro du livre (0 pour quitter) : ");
            int choix = sc.nextInt();

            if (choix == 0)
                break;

            livre l = b.getLivreParNumero(choix);

            if (l == null) {
                System.out.println("Livre introuvable.");
                continue;
            }

            client.emprunter(l);

            System.out.println("Livre emprunté : " + l.getTitre());
            System.out.println("Nombre de livres empruntés : "
                    + client.getNbEmprunts());
        }

        sc.close();
        System.out.println("Au revoir !");
    }
}

