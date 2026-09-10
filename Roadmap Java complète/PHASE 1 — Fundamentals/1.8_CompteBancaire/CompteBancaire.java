public class CompteBancaire {
    private double solde;

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        if (solde < 0) {
            System.out.println("Solde invalide, opération refusée.");
            return;
        }
        this.solde = solde;
    }

    public static void main(String[] args) {
        CompteBancaire compte = new CompteBancaire();
        compte.setSolde(1000);
        compte.setSolde(-50);
        System.out.println("Solde: " + compte.getSolde());
    }
}
