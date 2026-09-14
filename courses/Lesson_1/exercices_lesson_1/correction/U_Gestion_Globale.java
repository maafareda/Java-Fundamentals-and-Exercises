public class U_Gestion_Globale {

    static void executerAction(Runnable action) {
        try {
            action.run();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        executerAction(() -> System.out.println("Action exécutée sans problème."));

        executerAction(() -> {
            throw new RuntimeException("quelque chose a mal tourné");
        });
    }
}
