import java.util.concurrent.ConcurrentHashMap;

public class Z_ConcurrentHashMap_MultiThread {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Integer, String> utilisateurs = new ConcurrentHashMap<>();

        Runnable tache1 = () -> {
            for (int i = 0; i < 5; i++) {
                utilisateurs.put(i, "Utilisateur-A" + i);
            }
        };
        Runnable tache2 = () -> {
            for (int i = 5; i < 10; i++) {
                utilisateurs.put(i, "Utilisateur-B" + i);
            }
        };
        Runnable tache3 = () -> {
            for (int i = 10; i < 15; i++) {
                utilisateurs.put(i, "Utilisateur-C" + i);
            }
        };

        Thread t1 = new Thread(tache1);
        Thread t2 = new Thread(tache2);
        Thread t3 = new Thread(tache3);

        t1.start(); t2.start(); t3.start();
        t1.join(); t2.join(); t3.join();

        System.out.println("Nombre total d'entrées : " + utilisateurs.size());
        System.out.println(utilisateurs);
    }
}
