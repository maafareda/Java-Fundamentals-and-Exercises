import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Q_Consumer_Personnalise {
    public static void main(String[] args) {
        Consumer<Integer> parite = n -> {
            if (n % 2 == 0) {
                System.out.println(n + " -> Nombre pair");
            } else {
                System.out.println(n + " -> Nombre impair");
            }
        };

        List<Integer> nombres = Arrays.asList(1, 2, 3, 4, 5, 6);
        nombres.forEach(parite);
    }
}
