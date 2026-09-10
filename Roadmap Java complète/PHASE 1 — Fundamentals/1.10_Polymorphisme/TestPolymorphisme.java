import java.util.ArrayList;
import java.util.List;

class Chat extends Animal {
    @Override
    public void crier() {
        System.out.println("Le chat miaule");
    }
}

public class TestPolymorphisme {
    public static void main(String[] args) {
        List<Animal> animaux = new ArrayList<>();
        animaux.add(new Chien());
        animaux.add(new Chat());

        for (Animal a : animaux) {
            a.crier(); // liaison dynamique
        }
    }
}
