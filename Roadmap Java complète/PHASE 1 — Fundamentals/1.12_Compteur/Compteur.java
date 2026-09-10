public class Compteur {
    static int compteurTotal = 0;
    static final int MAX_VALEUR = 100;

    public Compteur() {
        compteurTotal++;
    }

    public static void main(String[] args) {
        new Compteur();
        new Compteur();
        new Compteur();
        System.out.println("Instances créées: " + compteurTotal);
        System.out.println("Valeur max autorisée: " + MAX_VALEUR);
    }
}
