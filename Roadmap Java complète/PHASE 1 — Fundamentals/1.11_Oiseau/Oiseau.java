interface Volant {
    void voler();
}

public class Oiseau implements Volant {
    @Override
    public void voler() {
        System.out.println("L'oiseau vole dans le ciel");
    }

    public static void main(String[] args) {
        Oiseau o = new Oiseau();
        o.voler();
    }
}
