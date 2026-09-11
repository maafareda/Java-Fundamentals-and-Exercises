import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/commande")
public class CommandeServlet extends HttpServlet {

    @Inject
    private CommandeService commandeService;    // injection CDI

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Commande commande = new Commande();
        commande.setClient(request.getParameter("client"));
        commande.setMontant(Double.parseDouble(request.getParameter("montant")));

        commandeService.passerCommande(commande);

        request.setAttribute("commandes", commandeService.listerCommandes());
        request.getRequestDispatcher("confirmation.jsp").forward(request, response);
    }
}
