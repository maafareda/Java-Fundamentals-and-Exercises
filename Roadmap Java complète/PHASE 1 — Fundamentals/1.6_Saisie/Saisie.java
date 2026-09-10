import java.util.Scanner;

public class Saisie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Votre nom: ");
        String nom = sc.nextLine();

        System.out.print("Votre âge: ");
        int age = sc.nextInt();

        System.out.printf("Bonjour %s, vous avez %d ans.%n", nom, age);
        sc.close();
    }
}
