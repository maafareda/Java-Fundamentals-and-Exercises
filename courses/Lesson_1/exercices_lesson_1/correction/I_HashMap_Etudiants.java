import java.util.HashMap;

public class I_HashMap_Etudiants {
    public static void main(String[] args) {
        HashMap<Integer, String> etudiants = new HashMap<>();
        etudiants.put(1, "Reda");
        etudiants.put(2, "Amine");
        etudiants.put(3, "Sara");
        etudiants.put(4, "Yassine");

        int idRecherche = 3;
        System.out.println("Étudiant avec l'ID " + idRecherche + " : " + etudiants.get(idRecherche));
    }
}
