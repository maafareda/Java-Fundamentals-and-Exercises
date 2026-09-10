package org.example;

import javax.swing.*;
import java.awt.*;

public class Interface extends JFrame {

    private Bibliotheque b;
    private Client client;
    private JTextArea zone;

    public Interface() {

        // Bibliothèque
        b = new Bibliotheque();

        b.ajouterLivre(new livre(1, "Le Petit Prince", "Saint-Exupéry"));
        b.ajouterLivre(new livre(2, "1984", "George Orwell"));
        b.ajouterLivre(new livre(3, "L'Étranger", "Albert Camus"));

        // Client
        client = new Client(1, "Jean Dupont", "jean@email.com");
        b.ajouterClient(client);

        // Fenêtre
        setTitle("Bibliothèque");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Bouton afficher
        JButton afficher = new JButton("Afficher les livres");

        // Champ numéro
        JTextField numero = new JTextField(10);

        // Bouton emprunter
        JButton emprunter = new JButton("Emprunter");

        // Zone de texte
        zone = new JTextArea(15, 45);
        zone.setEditable(false);

        add(afficher);
        add(new JLabel("Numéro :"));
        add(numero);
        add(emprunter);
        add(new JScrollPane(zone));

        // Afficher les livres
        afficher.addActionListener(e -> {

            zone.setText("");

            for (int i = 0; i < b.getNbLivres(); i++) {

                if (b.getLivres()[i].isDisponible()) {
                    zone.append(b.getLivres()[i] + "\n");
                }
            }
        });

        // Emprunter un livre
        emprunter.addActionListener(e -> {

            int num = Integer.parseInt(numero.getText());

            livre l = b.getLivreParNumero(num);

            if (l == null) {

                zone.setText("Livre introuvable ou déjà emprunté.");

            } else {

                client.emprunter(l);

                zone.setText("Livre emprunté : " + l.getTitre()
                        + "\nNombre de livres empruntés : "
                        + client.getNbEmprunts());
            }

            numero.setText("");
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Interface();
    }
}