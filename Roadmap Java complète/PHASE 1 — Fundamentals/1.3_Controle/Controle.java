public class Controle {
    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) System.out.println(i);
        }

        int jour = 3;
        String nom = switch (jour) {
            case 1 -> "Lundi";
            case 2 -> "Mardi";
            case 3 -> "Mercredi";
            case 4 -> "Jeudi";
            case 5 -> "Vendredi";
            case 6 -> "Samedi";
            case 7 -> "Dimanche";
            default -> "Invalide";
        };
        System.out.println(nom);
    }
}
