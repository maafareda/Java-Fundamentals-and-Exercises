package e3_classe_abstract;

public class Main {
    public static void main(String[] args) {
    	//en fait 2 employe
    	
        Employe[] employes = new Employe[2];
        employes[0] = new Developpeur("Amine", 3);
        employes[1] = new Manager("Sara", 5);

        for (Employe e : employes) {
            e.travailler();
            e.afficherInfos();

            if (e instanceof Payable p) {
                System.out.println("Salaire: " + p.calculerSalaire() + " DH");
            }
            System.out.println("---");
        }
    }
}