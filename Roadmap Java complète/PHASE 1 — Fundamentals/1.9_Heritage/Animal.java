public class Animal {
    public void crier() {
        System.out.println("L'animal fait un bruit");
    }
}

class Chien extends Animal {
    @Override
    public void crier() {
        System.out.println("Le chien aboie");
    }

    public static void main(String[] args) {
        Animal a = new Chien();
        a.crier();
    }
}
