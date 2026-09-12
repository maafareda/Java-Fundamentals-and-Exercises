package e3_classe_abstract;

class Manager extends Employe implements Payable {

    public Manager(String nom, int anneesExperience) {
        super(nom, anneesExperience);
    }

    @Override
    public void travailler() {
        System.out.println(nom + " gère l'équipe.");
    }

    @Override
    public double calculerSalaire() {
        return 7000 + anneesExperience * 300;
    }
}
