public class E_Employe_Manager {

    static abstract class Employe {
        String nom;
        double salaireBase;
        Employe(String nom, double salaireBase) {
            this.nom = nom;
            this.salaireBase = salaireBase;
        }
        abstract double calculerSalaire();
    }

    interface Prime {
        double bonus();
    }

    static class Manager extends Employe implements Prime {
        double bonusMontant;
        Manager(String nom, double salaireBase, double bonusMontant) {
            super(nom, salaireBase);
            this.bonusMontant = bonusMontant;
        }
        public double bonus() { return bonusMontant; }
        double calculerSalaire() { return salaireBase + bonus(); }
    }

    public static void main(String[] args) {
        Manager m = new Manager("Reda", 8000, 1500);
        System.out.println(m.nom + " gagne : " + m.calculerSalaire() + " DH");
    }
}
