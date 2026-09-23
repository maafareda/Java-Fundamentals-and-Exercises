/**
 * Correction — Exercice 1 : Conteneur de servlets ou serveur d'application ?
 * Thème : Tomcat vs GlassFish/WildFly, EJB, JMS, JTA
 *
 * Cet exercice est principalement une analyse de cas ; la correction est donc
 * fournie sous forme de commentaires structurés plutôt que de code exécutable.
 */
public class Correction_Exercice1_Analyse {

    /*
     * ============================================================
     * Application A — site vitrine avec formulaire de contact
     * ============================================================
     * Choix : Apache Tomcat (simple conteneur de servlets).
     *
     * Justification : l'application n'a besoin ni de transactions
     * distribuées (JTA), ni de messagerie asynchrone (JMS), ni de
     * composants métier transactionnels (EJB). Un simple conteneur
     * de servlets suffit à exécuter les Servlets/JSP nécessaires au
     * formulaire de contact, avec une empreinte et une complexité
     * d'administration bien moindres qu'un serveur d'application complet.
     *
     * Avantage du choix : légèreté, démarrage rapide, administration simple.
     * Inconvénient : si l'application évolue un jour vers des besoins
     * transactionnels avancés, une migration vers un serveur plus complet
     * (ou l'ajout de bibliothèques tierces) sera nécessaire.
     */

    /*
     * ============================================================
     * Application B — réservation avec débit bancaire + réservation externe
     * ============================================================
     * Choix : serveur d'application complet (ex. WildFly).
     * Spécification indispensable : JTA (Java Transaction API).
     *
     * Justification : l'énoncé décrit explicitement une transaction
     * distribuée entre deux ressources (le compte bancaire et le système
     * de réservation externe), qui doivent réussir ou échouer ENSEMBLE
     * (principe d'atomicité). C'est exactement le rôle de JTA, qui
     * coordonne un "two-phase commit" entre plusieurs ressources
     * transactionnelles. Un simple conteneur de servlets comme Tomcat ne
     * fournit pas nativement ce mécanisme.
     *
     * Avantage du choix : cohérence garantie entre les deux opérations,
     * même en cas de panne partielle.
     * Inconvénient : complexité et empreinte plus importantes que Tomcat,
     * configuration du gestionnaire de transactions à assurer.
     */

    /*
     * ============================================================
     * Application C — notification asynchrone de plusieurs services
     * ============================================================
     * Spécification répondant au besoin : JMS (Java Message Service).
     * Environnement requis : serveur d'application complet (ou un
     * conteneur de servlets associé à un broker JMS externe, ex. ActiveMQ).
     *
     * Justification : le besoin décrit — notifier plusieurs services sans
     * attendre leur réponse immédiate — est la définition même de la
     * messagerie asynchrone, assurée par JMS. Un serveur d'application
     * complet intègre nativement un fournisseur JMS ; à défaut, il faudrait
     * ajouter un broker de messages externe à un simple conteneur Tomcat.
     *
     * Avantage : découplage fort entre l'émetteur et les récepteurs, bonne
     * tolérance aux pannes temporaires d'un des services notifiés.
     * Inconvénient : complexité accrue (gestion des files, des accusés de
     * réception), latence de traitement plus difficile à garantir en temps réel.
     */
}
