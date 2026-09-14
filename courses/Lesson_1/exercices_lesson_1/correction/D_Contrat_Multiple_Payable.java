public class D_Contrat_Multiple_Payable {

    interface Payable {
        void payer(double montant);
    }

    static class Client implements Payable {
        public void payer(double montant) {
            System.out.println("Paiement reçu de " + montant + " DH");
        }
    }

    static class Fournisseur implements Payable {
        public void payer(double montant) {
            System.out.println("Paiement envoyé de " + montant + " DH");
        }
    }

    public static void main(String[] args) {
        Payable p1 = new Client();
        Payable p2 = new Fournisseur();
        p1.payer(500);
        p2.payer(1200);
    }
}
