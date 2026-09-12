package iterface_example;

public class voiture implements Vehicule {

    @Override
    public void demarer() {
        System.out.println("demaration de voiture");
    }

    @Override
    public void arrete() {
        System.out.println("arret de voiture");
    }
}