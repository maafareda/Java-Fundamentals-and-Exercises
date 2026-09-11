import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
public class NotificationService {

    @Inject
    private JMSContext contexte;

    @Resource(lookup = "jms/FileCommandes")
    private Queue fileCommandes;

    public void notifierNouvelleCommande(Commande commande) {
        contexte.createProducer()
                .send(fileCommandes, "Nouvelle commande de " + commande.getClient());
    }
}
