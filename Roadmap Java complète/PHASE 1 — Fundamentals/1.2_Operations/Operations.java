public class Operations {
    public static void main(String[] args) {
        int a = 17, b = 5;
        System.out.println("Somme: " + (a + b));
        System.out.println("Différence: " + (a - b));
        System.out.println("Produit: " + (a * b));
        System.out.println("Quotient entier: " + (a / b));
        System.out.println("Reste: " + (a % b));

        double quotientDecimal = (double) a / b;
        System.out.println("Quotient décimal: " + quotientDecimal);
    }
}
