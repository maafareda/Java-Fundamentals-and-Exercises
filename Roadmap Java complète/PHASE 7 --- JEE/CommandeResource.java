import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/commandes")
public class CommandeResource {

    @Inject
    private CommandeService commandeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Commande> getCommandes() {
        return commandeService.listerCommandes();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("VENDEUR")                       // sécurité : rôle requis
    public Response creerCommande(@Valid Commande commande) {   // Bean Validation
        commandeService.passerCommande(commande);
        return Response.status(201).build();
    }
}
