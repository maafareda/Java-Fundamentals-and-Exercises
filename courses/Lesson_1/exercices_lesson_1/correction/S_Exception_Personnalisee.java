public class S_Exception_Personnalisee {

    static class AgeInvalideException extends RuntimeException {
        AgeInvalideException(String message) {
            super(message);
        }
    }

    static void verifierAge(int age) {
        if (age < 0 || age > 120) {
            throw new AgeInvalideException("Âge invalide : " + age);
        }
        System.out.println("Âge valide : " + age);
    }

    public static void main(String[] args) {
        verifierAge(25);
        verifierAge(-5); // lève l'exception
    }
}
