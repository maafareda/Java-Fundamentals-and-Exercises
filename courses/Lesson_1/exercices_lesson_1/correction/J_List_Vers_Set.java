import java.util.ArrayList;
import java.util.HashSet;

public class J_List_Vers_Set {
    public static void main(String[] args) {
        ArrayList<String> liste = new ArrayList<>();
        liste.add("Reda");
        liste.add("Amine");
        liste.add("Reda");
        liste.add("Sara");
        liste.add("Amine");

        HashSet<String> ensemble = new HashSet<>(liste);

        System.out.println("Liste initiale : " + liste);
        System.out.println("Set sans doublons : " + ensemble);
    }
}
