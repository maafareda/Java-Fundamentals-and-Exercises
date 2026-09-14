import java.util.Arrays;
import java.util.List;

public class M_Map_Simple {
    public static void main(String[] args) {
        List<String> prenoms = Arrays.asList("reda", "amine", "sara");

        prenoms.stream()
               .map(String::toUpperCase)
               .forEach(System.out::println);
    }
}
