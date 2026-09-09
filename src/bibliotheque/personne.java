package bibliotheque;

public abstract class personne {
	
	private int id ;
    private String nom;

    public personne (int id , String nom )
    {
    	this.nom= nom;
    	this.id=id;
    }
    
    public String getnom()
    {
        return nom;
    }
    
    public void setid(int id)
    {
    	this.id = id; 
    	
    }
    
    public int getid() 
    {
        return id;
    }
    
    public void setnom(String nom)
    {
    	this.nom=nom;
    }

    public abstract void afficherRole();
}
