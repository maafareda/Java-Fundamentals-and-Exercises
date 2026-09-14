import java.util.HashSet;

public class H_HashSet_Unicite {
    public static void main(String[] args) {
        HashSet<String> prenoms = new HashSet<>();
        prenoms.add("Reda");
        prenoms.add("Amine");
        prenoms.add("Yassine");
        prenoms.add("Sara");
        prenoms.add("Amine");
        prenoms.add("Nada");

        System.out.println(prenoms);
        System.out.println("Taille : " + prenoms.size());
    }
}
