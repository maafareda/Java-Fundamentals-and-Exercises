import java.util.ArrayList;
import java.util.List;

public class C_Polymorphisme_Simple {

    interface Forme {
        double surface();
    }

    static class Cercle implements Forme {
        double rayon;
        Cercle(double rayon) { this.rayon = rayon; }
        public double surface() { return Math.PI * rayon * rayon; }
    }

    static class Rectangle implements Forme {
        double largeur, hauteur;
        Rectangle(double largeur, double hauteur) { this.largeur = largeur; this.hauteur = hauteur; }
        public double surface() { return largeur * hauteur; }
    }

    public static void main(String[] args) {
        List<Forme> formes = new ArrayList<>();
        formes.add(new Cercle(3));
        formes.add(new Rectangle(4, 5));
        formes.add(new Cercle(2));

        for (Forme f : formes) {
            System.out.println("Surface : " + f.surface());
        }
    }
}
