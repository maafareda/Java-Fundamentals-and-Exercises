public class B_Classe_Abstraite_Vehicule {

    static abstract class Vehicule {
        int vitesseMax;
        Vehicule(int vitesseMax) { this.vitesseMax = vitesseMax; }
        abstract void demarrer();
    }

    static class Voiture extends Vehicule {
        Voiture(int vitesseMax) { super(vitesseMax); }
        void demarrer() { System.out.println("La voiture démarre, vitesse max : " + vitesseMax + " km/h"); }
    }

    static class Moto extends Vehicule {
        Moto(int vitesseMax) { super(vitesseMax); }
        void demarrer() { System.out.println("La moto démarre en trombe, vitesse max : " + vitesseMax + " km/h"); }
    }

    public static void main(String[] args) {
        Vehicule v1 = new Voiture(180);
        Vehicule v2 = new Moto(220);
        v1.demarrer();
        v2.demarrer();
    }
}
