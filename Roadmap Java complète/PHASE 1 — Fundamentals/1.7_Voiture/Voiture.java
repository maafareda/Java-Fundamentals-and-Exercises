public class Voiture {
    String marque;
    int vitesse;

    public Voiture(String marque) {
        this.marque = marque;
        this.vitesse = 0;
    }

    public void accelerer(int increment) {
        this.vitesse += increment;
    }

    public static void main(String[] args) {
        Voiture v = new Voiture("Toyota");
        v.accelerer(50);
        System.out.println(v.marque + " roule à " + v.vitesse + " km/h");
    }
}
