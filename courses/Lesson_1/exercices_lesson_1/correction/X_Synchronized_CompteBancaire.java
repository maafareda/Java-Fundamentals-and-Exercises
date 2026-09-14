public class X_Synchronized_CompteBancaire {

    static class CompteBancaire {
        private int solde = 1000;

        synchronized void retirer(int montant) {
            if (solde >= montant) {
                solde -= montant;
                System.out.println("Retrait de " + montant + " | Solde : " + solde);
            } else {
                System.out.println("Solde insuffisant pour retirer " + montant);
            }
        }

        int getSolde() { return solde; }
    }

    public static void main(String[] args) throws InterruptedException {
        CompteBancaire compte = new CompteBancaire();

        Runnable retrait = () -> compte.retirer(100);

        Thread t1 = new Thread(retrait);
        Thread t2 = new Thread(retrait);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Solde final : " + compte.getSolde());
    }
}
