import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class K_Mini_Annuaire {
    public static void main(String[] args) {
        HashMap<String, List<String>> annuaire = new HashMap<>();

        annuaire.put("Reda", new ArrayList<>(List.of("0600000001", "0500000001")));
        annuaire.put("Sara", new ArrayList<>(List.of("0600000002", "0500000002")));
        annuaire.put("Amine", new ArrayList<>(List.of("0600000003", "0500000003")));

        for (Map.Entry<String, List<String>> entree : annuaire.entrySet()) {
            System.out.println(entree.getKey() + " : " + entree.getValue());
        }
    }
}
