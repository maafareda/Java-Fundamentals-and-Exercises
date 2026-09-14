import java.util.Arrays;
import java.util.List;

public class N_Filter_Map_Combines {
    public static void main(String[] args) {
        List<Integer> notes = Arrays.asList(8, 12, 15, 5, 18, 9, 20);

        notes.stream()
             .filter(n -> n >= 10)
             .map(n -> n * 5)
             .forEach(n -> System.out.println(n + "%"));
    }
}
