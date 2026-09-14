import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class P_Function_Personnalisee {
    public static void main(String[] args) {
        Function<String, Integer> longueur = String::length;

        List<String> mots = Arrays.asList("Java", "Programmation", "POO", "Stream");

        mots.stream()
            .map(longueur)
            .forEach(System.out::println);
    }
}
