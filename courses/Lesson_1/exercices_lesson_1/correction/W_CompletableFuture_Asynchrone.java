import java.util.concurrent.CompletableFuture;

public class W_CompletableFuture_Asynchrone {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Traitement asynchrone terminé.");
        });

        System.out.println("Le programme principal continue pendant ce temps...");

        Thread.sleep(1500); // pour laisser le temps à la tâche async de finir
    }
}
