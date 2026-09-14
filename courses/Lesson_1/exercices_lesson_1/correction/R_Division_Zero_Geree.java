import java.util.Scanner;

public class R_Division_Zero_Geree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Entrez le numérateur : ");
        int a = sc.nextInt();
        System.out.print("Entrez le dénominateur : ");
        int b = sc.nextInt();

        try {
            System.out.println("Résultat : " + (a / b));
        } catch (ArithmeticException e) {
            System.out.println("Erreur : division par zéro impossible.");
        }
    }
}
