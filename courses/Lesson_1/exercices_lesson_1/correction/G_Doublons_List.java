import java.util.ArrayList;

public class G_Doublons_List {
    public static void main(String[] args) {
        ArrayList<Integer> nombres = new ArrayList<>();
        System.out.println("Taille avant : " + nombres.size());

        nombres.add(10);
        nombres.add(10);
        nombres.add(20);
        nombres.add(10);

        System.out.println("Taille après : " + nombres.size());
        System.out.println(nombres);
    }
}
