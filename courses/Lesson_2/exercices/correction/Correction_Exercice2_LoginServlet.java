import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Correction — Exercice 2 : Servlets, JSP et gestion de session.
 *
 * Cycle de vie rappelé en commentaires :
 *   - init()    : appelée UNE SEULE FOIS par le conteneur, au chargement de la servlet.
 *   - service() : appelée à CHAQUE requête HTTP ; par défaut HttpServlet la redirige
 *                 vers doGet()/doPost() selon la méthode HTTP utilisée.
 *   - destroy() : appelée UNE SEULE FOIS, avant le retrait de la servlet par le conteneur.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // Identifiants codés en dur pour les besoins de l'exercice uniquement.
    private static final String LOGIN_VALIDE = "admin";
    private static final String MDP_VALIDE = "admin123";

    @Override
    public void init() throws ServletException {
        // Appelée une seule fois : initialisation de ressources éventuelles
        // (ex. connexion à un pool, chargement de configuration).
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Appelée à chaque soumission POST du formulaire de connexion.
        String login = request.getParameter("login");
        String motDePasse = request.getParameter("motDePasse");

        if (LOGIN_VALIDE.equals(login) && MDP_VALIDE.equals(motDePasse)) {
            // Authentification réussie : on crée (ou récupère) la session.
            HttpSession session = request.getSession(true);
            session.setAttribute("utilisateurConnecte", login);
            session.setMaxInactiveInterval(30 * 60); // 30 minutes

            response.sendRedirect(request.getContextPath() + "/profil");
        } else {
            // Échec : retour vers la page de login avec un message d'erreur.
            request.setAttribute("erreur", "Identifiants invalides.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Simple affichage du formulaire de connexion (Vue : login.jsp).
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        // Appelée une seule fois avant le retrait de la servlet : libération de
        // ressources éventuellement ouvertes dans init().
        super.destroy();
    }
}

/**
 * Servlet protégée : exige une session valide avant d'afficher le profil.
 * Illustration du contrôleur dans l'architecture MVC.
 */
@WebServlet("/profil")
class ProfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // false : ne pas en créer une nouvelle

        if (session == null || session.getAttribute("utilisateurConnecte") == null) {
            // Aucune session valide : redirection vers la page de connexion.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String utilisateur = (String) session.getAttribute("utilisateurConnecte");
        request.setAttribute("nomUtilisateur", utilisateur);
        // Vue : profil.jsp affiche request.getAttribute("nomUtilisateur")
        request.getRequestDispatcher("/profil.jsp").forward(request, response);
    }
}

/*
 * Architecture MVC dans cette correction :
 *   - Modèle     : l'utilisateur authentifié, représenté ici simplement par une
 *                  chaîne stockée en session (dans un cas réel : une entité Utilisateur).
 *   - Vue        : login.jsp et profil.jsp (non fournis ici, responsables du rendu HTML).
 *   - Contrôleur : LoginServlet et ProfilServlet, qui reçoivent les requêtes,
 *                  appliquent la logique métier (vérification, session) et choisissent
 *                  la vue à afficher.
 */
