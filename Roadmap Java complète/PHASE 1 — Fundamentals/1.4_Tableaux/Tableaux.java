import java.util.Arrays;

public class Tableaux {
    public static void main(String[] args) {
        int[] tab = {12, 45, 3, 67, 21, 8};

        int somme = 0;
        for (int valeur : tab) {
            somme += valeur;
        }
        double moyenne = (double) somme / tab.length;

        System.out.println("Somme: " + somme);
        System.out.println("Moyenne: " + moyenne);

        Arrays.sort(tab);
        System.out.println("Trié: " + Arrays.toString(tab));
    }
}
