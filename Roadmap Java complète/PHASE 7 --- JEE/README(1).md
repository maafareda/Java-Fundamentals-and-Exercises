# Mini application "Gestion de Commandes" — récapitulatif Java EE

Ces fichiers reprennent, à travers un seul exemple cohérent, toutes les
technologies vues dans le guide PDF : Servlet, JSP, EJB, JPA, CDI, JAX-RS,
Sécurité, Transactions (JTA), JMS et Bean Validation.

**Scénario :** un client passe une commande → elle est validée → enregistrée
en base de données → une notification asynchrone est envoyée.

## Fichiers

| Fichier | Rôle | Technologie |
|---|---|---|
| `Commande.java` | Entité représentant une commande | JPA + Bean Validation |
| `CommandeService.java` | Logique métier (enregistrement, transaction) | EJB + CDI + JTA |
| `NotificationService.java` | Envoie un message dans la file JMS | JMS (producteur) |
| `NotificationConsumer.java` | Traite le message reçu en tâche de fond | JMS (consommateur) |
| `CommandeServlet.java` | Reçoit le formulaire web | Servlet |
| `confirmation.jsp` | Affiche le résultat au client | JSP |
| `CommandeResource.java` | Expose une API REST protégée | JAX-RS + Sécurité |

## Comment tout s'enchaîne

1. Le **Servlet** (`CommandeServlet`) ou l'**API REST** (`CommandeResource`)
   reçoit la demande du client.
2. Pour l'API REST, `@RolesAllowed("VENDEUR")` vérifie les **droits d'accès**.
3. `@Valid` déclenche la **validation** des données (`@NotNull`, `@Min`...).
4. **CDI** (`@Inject`) fournit automatiquement le service métier.
5. L'**EJB** exécute `passerCommande()` dans une **transaction** sécurisée.
6. **JPA** (`em.persist()`) enregistre la commande en base de données.
7. **JMS** envoie une notification en tâche de fond, sans bloquer la réponse.
8. La **JSP** (ou une réponse JSON) renvoie le résultat au client.

> En résumé : le point d'entrée (Servlet/REST) délègue au service métier via
> CDI, l'EJB exécute la logique dans une transaction, JPA persiste les
> données, JMS notifie en asynchrone, et la vue (JSP/JSON) répond au client.
