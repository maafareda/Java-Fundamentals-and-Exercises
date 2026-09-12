package e3_classe_abstract;

public abstract class Employe {

	protected String nom;
    protected int anneesExperience;

    public Employe(String nom, int anneesExperience) {
        this.nom = nom;
        this.anneesExperience = anneesExperience;
    }

    public abstract void travailler();

    public void afficherInfos() {
        System.out.println("Nom: " + nom + " | Expérience: " + anneesExperience + " ans");
    }
}