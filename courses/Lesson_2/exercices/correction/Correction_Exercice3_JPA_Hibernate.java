import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Correction — Exercice 3 : ORM avec JPA et Hibernate.
 *
 * Relation many-to-many entre Etudiant et Cours.
 */

// ---------------------------------------------------------------------
// Entité Etudiant
// ---------------------------------------------------------------------
@Entity
@Table(name = "etudiant")
class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    // Côté PROPRIÉTAIRE de la relation : c'est ici que la table de jointure
    // est déclarée avec @JoinTable.
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "etudiant_cours",
        joinColumns = @JoinColumn(name = "etudiant_id"),
        inverseJoinColumns = @JoinColumn(name = "cours_id")
    )
    private Set<Cours> cours = new HashSet<>();

    public Etudiant() {
    }

    public Etudiant(String nom) {
        this.nom = nom;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Set<Cours> getCours() { return cours; }
}

// ---------------------------------------------------------------------
// Entité Cours
// ---------------------------------------------------------------------
@Entity
@Table(name = "cours")
class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String intitule;

    // Côté INVERSE de la relation : mappedBy pointe vers le champ "cours"
    // déjà défini dans Etudiant. Aucune table de jointure n'est redéclarée ici.
    @ManyToMany(mappedBy = "cours", fetch = FetchType.LAZY)
    private Set<Etudiant> etudiants = new HashSet<>();

    public Cours() {
    }

    public Cours(String intitule) {
        this.intitule = intitule;
    }

    public Long getId() { return id; }
    public String getIntitule() { return intitule; }
    public void setIntitule(String intitule) { this.intitule = intitule; }
    public Set<Etudiant> getEtudiants() { return etudiants; }
}

// ---------------------------------------------------------------------
// Exemple d'utilisation : requête JPQL
// ---------------------------------------------------------------------
public class Correction_Exercice3_JPA_Hibernate {

    /**
     * Retourne la liste des noms d'étudiants inscrits à un cours donné,
     * identifié par son intitulé.
     *
     * Requête JPQL demandée : on manipule les ENTITÉS (Etudiant, Cours) et
     * leurs attributs, pas directement les tables/colonnes SQL.
     */
    public static List<String> nomsEtudiantsParCours(EntityManager em, String intituleCours) {
        String jpql = "SELECT e.nom FROM Etudiant e JOIN e.cours c WHERE c.intitule = :intitule";

        TypedQuery<String> query = em.createQuery(jpql, String.class);
        query.setParameter("intitule", intituleCours);

        return query.getResultList();
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecolePU");
        EntityManager em = emf.createEntityManager();

        try {
            List<String> noms = nomsEtudiantsParCours(em, "Bases de données");
            noms.forEach(System.out::println);
        } finally {
            em.close();
            emf.close();
        }
    }
}

/*
 * Lazy vs Eager :
 *
 * - FetchType.LAZY (choisi ici) : la collection "cours" (ou "etudiants") n'est
 *   chargée depuis la base qu'au moment où elle est réellement accédée
 *   (etudiant.getCours().size() par exemple). C'est le choix recommandé pour
 *   une relation many-to-many, car le nombre de cours ou d'étudiants liés peut
 *   être important : charger systématiquement toute la collection dès qu'on
 *   récupère un Etudiant ou un Cours serait coûteux et souvent inutile.
 *
 * - FetchType.EAGER : la collection associée est chargée immédiatement, en
 *   même temps que l'entité principale. Cela peut être pertinent pour une
 *   relation avec très peu d'éléments, systématiquement utilisée dès qu'on
 *   charge l'entité — ce qui n'est pas le cas ici.
 *
 * Choix retenu : LAZY, pour éviter de charger inutilement des collections
 * potentiellement volumineuses à chaque lecture d'un Etudiant ou d'un Cours.
 */
