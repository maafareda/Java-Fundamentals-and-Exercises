package e3_classe_abstract;

public class Developpeur extends Employe implements Payable {

	public Developpeur(String nom, int anneesExperience) {
	    super(nom, anneesExperience);
	}

	@Override
	public void travailler() {
	    System.out.println(nom + " écrit du code.");
	}

	@Override
	public double calculerSalaire() {
	    return 5000 + anneesExperience * 200;
	}
}