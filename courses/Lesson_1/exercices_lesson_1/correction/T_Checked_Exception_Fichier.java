import java.io.FileReader;
import java.io.IOException;

public class T_Checked_Exception_Fichier {

    static void lireFichier(String chemin) {
        try {
            FileReader fichier = new FileReader(chemin);
            System.out.println("Fichier ouvert avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur : impossible de lire le fichier \"" + chemin + "\".");
        }
    }

    public static void main(String[] args) {
        lireFichier("test.txt");
    }
}
