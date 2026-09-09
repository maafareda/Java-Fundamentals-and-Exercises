package bibliotheque;

public class livre {

	private int numero;
    private String titre;
    private String auteur;
    private boolean disponible;
 
    public livre(int numero, String titre, String auteur)
    {	
        this.numero = numero;
        this.titre = titre;
        this.auteur = auteur;
        this.disponible = true; // au départ, tous les livres sont disponibles
    }
 
    public int getnumero() {
        return numero;
    }
 
    public String gettitre() {
        return titre;
    }
 
    public String getauteur() {
        return auteur;
    }
 
    public boolean isdisponible() {
        return disponible;
    }
 
    public void setdisponible(boolean disponible) {
        this.disponible = disponible;
    }

}
