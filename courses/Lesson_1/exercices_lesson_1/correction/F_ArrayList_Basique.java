import java.util.ArrayList;

public class F_ArrayList_Basique {
    public static void main(String[] args) {
        ArrayList<String> villes = new ArrayList<>();
        villes.add("Oujda");
        villes.add("Rabat");
        villes.add("Casablanca");
        villes.add("Marrakech");
        villes.add("Fès");

        System.out.println(villes);
        System.out.println("Ville à l'index 2 : " + villes.get(2));
    }
}
