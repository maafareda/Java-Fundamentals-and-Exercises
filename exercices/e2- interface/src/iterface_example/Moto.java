package iterface_example;

public class Moto implements Vehicule {

    @Override
    public void demarer() {
        System.out.println("demaration de moto");
    }

    @Override
    public void arrete() {
        System.out.println("arret de moto");
    }
}