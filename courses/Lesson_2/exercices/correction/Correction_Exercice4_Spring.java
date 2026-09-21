import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Correction — Exercice 4 : Écosystème Spring
 * (IoC/DI, Spring Data JPA, contrôleur REST, Spring Security).
 *
 * S'appuie sur l'entité Cours définie à l'exercice 3.
 */

// ---------------------------------------------------------------------
// 1) Repository Spring Data JPA — aucune implémentation à écrire.
//    Spring génère automatiquement l'implémentation à partir du nom
//    de la méthode ("findByIntitule" -> WHERE intitule = ?).
// ---------------------------------------------------------------------
interface CoursRepository extends JpaRepository<Cours, Long> {
    Cours findByIntitule(String intitule);
}

// ---------------------------------------------------------------------
// 2) Service métier — injection de dépendances PAR CONSTRUCTEUR.
//    Le champ est privé et final : il ne peut être modifié après
//    construction, ce qui garantit un objet toujours valide.
// ---------------------------------------------------------------------
@org.springframework.stereotype.Service
class CoursService {

    private final CoursRepository coursRepository;

    // Depuis Spring 4.3, @Autowired est facultatif s'il n'y a qu'un seul
    // constructeur : Spring l'utilise automatiquement pour l'injection.
    @Autowired
    public CoursService(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    public List<Cours> listerTousLesCours() {
        return coursRepository.findAll();
    }
}

// ---------------------------------------------------------------------
// 3) Contrôleur REST — expose GET /api/cours
// ---------------------------------------------------------------------
@RestController
class CoursController {

    private final CoursService coursService;

    @Autowired
    public CoursController(CoursService coursService) {
        this.coursService = coursService;
    }

    @GetMapping("/api/cours")
    public List<Cours> getCours() {
        return coursService.listerTousLesCours();
    }
}

// ---------------------------------------------------------------------
// 4) Configuration Spring Security — endpoint protégé (RBAC, rôle USER).
// ---------------------------------------------------------------------
@Configuration
@EnableWebSecurity
class SecurityConfig {

    @org.springframework.context.annotation.Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // /api/cours n'est accessible qu'aux utilisateurs authentifiés
                // ayant au moins le rôle USER (principe du RBAC).
                .requestMatchers("/api/cours").hasRole("USER")
                .anyRequest().authenticated()
            )
            .httpBasic(); // authentification basique pour l'exercice ;
                          // une API réelle utiliserait plutôt un filtre JWT.

        return http.build();
    }
}

/*
 * Pourquoi l'injection par constructeur facilite les tests unitaires :
 *
 * Avec une injection par constructeur, CoursService déclare explicitement sa
 * dépendance (CoursRepository) comme paramètre obligatoire de construction.
 * Dans un test unitaire, il suffit donc d'écrire :
 *
 *     CoursRepository faux = Mockito.mock(CoursRepository.class);
 *     CoursService service = new CoursService(faux);
 *
 * sans avoir besoin de démarrer le conteneur Spring ni de recourir à la
 * réflexion pour injecter un attribut privé (comme le ferait une injection
 * par attribut avec @Autowired sur le champ). Le code est donc testable en
 * Java "pur", de façon rapide et isolée, et la dépendance ne peut jamais être
 * un objet null non initialisé puisqu'elle est exigée dès la construction.
 */
