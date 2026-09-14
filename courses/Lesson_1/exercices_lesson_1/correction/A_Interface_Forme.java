public class A_Interface_Forme {

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
        Forme c = new Cercle(5);
        Forme r = new Rectangle(4, 6);
        System.out.println("Surface cercle : " + c.surface());
        System.out.println("Surface rectangle : " + r.surface());
    }
}
