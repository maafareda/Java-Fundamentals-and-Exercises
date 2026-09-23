/**
 * Correction — Exercice 4 : Choisir entre WAR et JAR exécutable.
 * Thème : artefacts de déploiement, conteneur embarqué, intégration avec Docker.
 *
 * Exercice d'analyse ; correction fournie sous forme de commentaires structurés.
 */
public class Correction_Exercice4_Analyse {

    /*
     * ============================================================
     * 1) Nouvelle application ajoutée à un serveur WildFly existant,
     *    partagé avec deux autres applications déjà en production.
     * ============================================================
     * Choix : format WAR.
     *
     * Justification : le serveur WildFly est déjà en place et partagé par
     * plusieurs applications. Le modèle WAR est précisément conçu pour ce
     * cas : plusieurs archives WAR peuvent être déployées côte à côte sur
     * un même serveur d'application, qui gère leur cycle de vie commun.
     * Empaqueter un JAR exécutable autonome ici ajouterait un second
     * serveur (le conteneur embarqué du JAR) en plus de WildFly, ce qui
     * n'a pas de sens dans une infrastructure déjà mutualisée.
     */

    /*
     * ============================================================
     * 2) Nouveau microservice packagé en image Docker, déployé sur Kubernetes.
     * ============================================================
     * Choix : JAR exécutable autonome (conteneur embarqué).
     *
     * Justification : dans une architecture conteneurisée (voir Leçon 3),
     * chaque image Docker doit être autonome et ne dépendre d'aucune
     * installation externe préalable. Un JAR exécutable, qui embarque son
     * propre conteneur de servlets (ex. Tomcat embarqué via Spring Boot),
     * correspond exactement à ce principe : l'image Docker n'a besoin que
     * d'un JRE et du JAR pour démarrer l'application avec
     * "java -jar application.jar", sans installer ni configurer de
     * serveur d'application séparément à l'intérieur de l'image.
     */

    /*
     * ============================================================
     * 3) Pourquoi un WAR classique n'est-il pas adapté à une image Docker
     *    "from scratch" (ou basée sur un JRE minimal, sans serveur) ?
     * ============================================================
     * Un fichier WAR n'est pas exécutable par lui-même : il nécessite un
     * conteneur de servlets ou un serveur d'application déjà installé et
     * configuré pour être déployé et exécuté (ex. copier le WAR dans le
     * dossier webapps/ de Tomcat). Une image Docker "from scratch" ou
     * basée sur un simple JRE ne contient, par définition, aucun de ces
     * composants : il faudrait alors installer et configurer manuellement
     * un serveur d'application DANS l'image, ce qui alourdit
     * considérablement l'image et va à l'encontre du principe d'image
     * légère et autonome recherché en environnement conteneurisé.
     */

    /*
     * ============================================================
     * 4) Avantage du modèle WAR perdu avec des JAR exécutables systématiques.
     * ============================================================
     * Le modèle WAR permet de MUTUALISER un même serveur d'application (et
     * donc ses ressources : mémoire allouée à la JVM, pool de threads,
     * pool de connexions partagé) entre plusieurs applications déployées
     * côte à côte. Avec des JAR exécutables autonomes, chaque application
     * embarque son propre serveur et sa propre JVM : on perd cette
     * mutualisation, chaque instance consommant sa propre mémoire de base
     * (empreinte JVM + conteneur embarqué), même pour des applications à
     * très faible charge. C'est un compromis assumé dans les architectures
     * conteneurisées, où l'isolation et l'autonomie de chaque service
     * priment sur la mutualisation des ressources d'un serveur partagé.
     */
}
