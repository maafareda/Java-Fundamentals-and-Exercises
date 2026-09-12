package iterface_example;

public class cammion implements Vehicule {

    @Override
    public void demarer() {
        System.out.println("demaration de camion");
    }

    @Override
    public void arrete() {
        System.out.println("arret de camion");
    }
}