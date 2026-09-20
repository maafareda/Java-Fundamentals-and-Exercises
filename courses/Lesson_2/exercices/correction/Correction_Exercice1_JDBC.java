import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Correction — Exercice 1 : JDBC (connexion et requêtes sécurisées)
 *
 * Thème : DriverManager, DataSource, Statement vs PreparedStatement.
 *
 * Table attendue :
 *   CREATE TABLE etudiants (
 *       id    INTEGER PRIMARY KEY AUTO_INCREMENT,
 *       nom   VARCHAR(100),
 *       email VARCHAR(150)
 *   );
 */
public class Correction_Exercice1_JDBC {

    private static final String URL = "jdbc:mysql://localhost:3306/ecole";
    private static final String USER = "root";
    private static final String PASSWORD = "motdepasse";

    /**
     * 1) Obtention d'une connexion via DriverManager.
     * En entreprise, on préférerait un DataSource (pool de connexions, ex. HikariCP),
     * mais DriverManager est suffisant pour illustrer le principe de base.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * 2) Insertion sécurisée avec PreparedStatement.
     * Les valeurs ne sont JAMAIS concaténées dans la chaîne SQL : elles sont liées
     * via setString(), ce qui empêche toute injection SQL.
     */
    public static void ajouterEtudiant(String nom, String email) throws SQLException {
        String sql = "INSERT INTO etudiants (nom, email) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nom);
            ps.setString(2, email);
            ps.executeUpdate();
        }
        // try-with-resources ferme automatiquement PreparedStatement puis Connection,
        // même en cas d'exception.
    }

    /**
     * 3) Recherche sécurisée par nom, toujours avec PreparedStatement.
     */
    public static String rechercherParNom(String nom) throws SQLException {
        String sql = "SELECT id, nom, email FROM etudiants WHERE nom = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nom);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return String.format("Etudiant{id=%d, nom='%s', email='%s'}",
                            rs.getInt("id"), rs.getString("nom"), rs.getString("email"));
                }
            }
        }
        return null; // aucun étudiant trouvé
    }

    /*
     * 4) Exemple d'injection SQL évitée.
     *
     * Avec un Statement construit par concaténation :
     *
     *     String sql = "SELECT * FROM etudiants WHERE nom = '" + nom + "'";
     *
     * Si l'utilisateur saisit :   nom = "x' OR '1'='1"
     * la requête finale devient :
     *
     *     SELECT * FROM etudiants WHERE nom = 'x' OR '1'='1'
     *
     * La condition '1'='1' est toujours vraie : la requête retourne alors TOUS les
     * étudiants de la table, au lieu d'un seul. Une valeur encore plus malveillante
     * pourrait injecter un ";DROP TABLE etudiants;--" et endommager la base.
     *
     * Avec un PreparedStatement, la chaîne "x' OR '1'='1" est transmise au moteur SQL
     * comme une SIMPLE VALEUR du paramètre "nom", jamais comme du code SQL : la
     * requête recherche alors un étudiant dont le nom est littéralement
     * "x' OR '1'='1", ce qui ne retournera aucun résultat. C'est cette séparation
     * stricte entre le code SQL (précompilé) et les données (liées séparément) qui
     * neutralise l'injection.
     */

    public static void main(String[] args) throws SQLException {
        ajouterEtudiant("Amine Benali", "amine.benali@example.com");
        String resultat = rechercherParNom("Amine Benali");
        System.out.println(resultat);
    }
}
