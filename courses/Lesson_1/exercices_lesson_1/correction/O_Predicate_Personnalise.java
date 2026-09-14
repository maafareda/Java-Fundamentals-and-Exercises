import java.util.function.Predicate;

public class O_Predicate_Personnalise {
    public static void main(String[] args) {
        Predicate<String> plusDe5Caracteres = mot -> mot.length() > 5;

        System.out.println("Java : " + plusDe5Caracteres.test("Java"));
        System.out.println("Programmation : " + plusDe5Caracteres.test("Programmation"));
        System.out.println("POO : " + plusDe5Caracteres.test("POO"));
    }
}
