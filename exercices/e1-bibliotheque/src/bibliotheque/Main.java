package bibliotheque;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Bibliotheque bibliotheque = new Bibliotheque();

        // Ajouter les livres
        bibliotheque.ajouterLivre(
                new livre(1, "Le Petit Prince", "Antoine de Saint-Exupéry"));

        bibliotheque.ajouterLivre(
                new livre(2, "1984", "George Orwell"));

        bibliotheque.ajouterLivre(
                new livre(3, "L'Étranger", "Albert Camus"));

        bibliotheque.ajouterLivre(
                new livre(4, "Le Meilleur des Mondes", "Aldous Huxley"));

        bibliotheque.ajouterLivre(
                new livre(5, "La Peste", "Albert Camus"));

        // Créer un client
        Client client = new Client(
                1,
                "Jean Dupont",
                "jean@email.com"
        );

        bibliotheque.ajouterClient(client);

        // Créer un employé
        Employe employe = new Employe(
                1,
                "Marie Martin",
                "marie@bibliotheque.fr",
                "Bibliothécaire"
        );

        bibliotheque.ajouterEmploye(employe);

        // Afficher les rôles
        System.out.println("=== Acteurs ===");

        client.afficherRole();
        employe.afficherRole();

        Scanner scanner = new Scanner(System.in);

        boolean continuer = true;

        while (continuer) {

            bibliotheque.afficherLivresDisponibles();

            System.out.print(
                    "\nChoisissez le numéro du livre à emprunter (0 pour quitter) : "
            );

            int choix;

            try {
                choix = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
                continue;
            }

            if (choix == 0) {
                System.out.println("Au revoir !");
                continuer = false;
                continue;
            }

            livre livreChoisi = bibliotheque.getLivreParNumero(choix);

            if (livreChoisi == null) {
                System.out.println(
                        "Numéro invalide ou livre non disponible."
                );
                continue;
            }

            System.out.print(
                    "Confirmez-vous l'emprunt de \""
                    + livreChoisi.getTitre()
                    + "\" ? (o/n) : "
            );

            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("o")
                    || confirmation.equalsIgnoreCase("oui")) {

                client.emprunter(livreChoisi);

                System.out.println(
                        "Emprunt confirmé !"
                );

            } else {

                System.out.println("Emprunt annulé.");
            }

            System.out.println("\n=== État de la bibliothèque ===");

            livre[] tousLesLivres = bibliotheque.getLivres();

            for (int i = 0; i < bibliotheque.getNbLivres(); i++) {
                System.out.println(tousLesLivres[i]);
            }

            System.out.println(
                    "\n"
                    + client.getNom()
                    + " a emprunté "
                    + client.getNbEmprunts()
                    + " livre(s)."
            );

            System.out.println("------------------------------");
        }

        scanner.close();
    }
}