import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Correction — Exercice 3 : Pool de threads pour le traitement de requêtes.
 * Thème : ExecutorService, threads réutilisables, file d'attente sous charge.
 */
public class Correction_Exercice3_ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {

        // 1) Pool fixe de 4 threads réutilisables.
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // 2) Soumission de 10 tâches simulant 10 requêtes HTTP.
        for (int i = 1; i <= 10; i++) {
            int requeteId = i;
            executor.submit(() -> {
                System.out.println("Requête " + requeteId + " traitée par "
                        + Thread.currentThread().getName());
                try {
                    Thread.sleep(500); // simule un traitement de 500 ms
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 4) Fermeture propre du pool : on n'accepte plus de nouvelles
        // tâches, mais on laisse les tâches déjà soumises se terminer.
        executor.shutdown();
        boolean termine = executor.awaitTermination(10, TimeUnit.SECONDS);
        if (!termine) {
            System.out.println("Le pool n'a pas terminé dans le délai imparti.");
        } else {
            System.out.println("Toutes les tâches sont terminées, pool fermé proprement.");
        }
    }
}

/*
 * 3) Pourquoi seulement 4 noms de threads distincts apparaissent, alors que
 * 10 tâches ont été soumises ?
 *
 * Le pool a été créé avec Executors.newFixedThreadPool(4) : il ne crée et
 * ne maintient que 4 threads ouvriers ("pool-1-thread-1" à
 * "pool-1-thread-4"), quel que soit le nombre de tâches soumises. Les 10
 * tâches sont placées dans une file d'attente interne ; dès qu'un des 4
 * threads termine sa tâche courante, il en récupère une nouvelle dans la
 * file. C'est exactement le principe de RÉUTILISATION des threads : au
 * lieu de créer 10 threads (un par tâche), ce qui serait coûteux et
 * potentiellement dangereux sous forte charge, on réutilise un nombre
 * borné de threads pour traiter successivement toutes les tâches.
 *
 * 5) Pourquoi un serveur web utilise un mécanisme équivalent pour les
 * requêtes HTTP plutôt que de créer un thread par requête ?
 *
 * Sous forte charge (des milliers de requêtes simultanées), créer un
 * thread par requête épuiserait rapidement la mémoire et le temps CPU
 * consacré à la seule gestion des threads (changements de contexte),
 * pouvant faire s'effondrer le serveur entier. Un pool de threads borné
 * garantit un nombre maximal de threads concurrents, connu et maîtrisé à
 * l'avance, tout en mettant en file d'attente les requêtes excédentaires
 * plutôt que de les traiter immédiatement — un compromis contrôlé entre
 * débit et stabilité du serveur.
 */
