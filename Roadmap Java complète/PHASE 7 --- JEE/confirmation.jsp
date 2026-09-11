<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Confirmation de commande</title>
</head>
<body>
  <h2>Commande enregistrée !</h2>
  <ul>
    <c:forEach var="commande" items="${commandes}">
      <li>${commande.client} — ${commande.montant} MAD (${commande.statut})</li>
    </c:forEach>
  </ul>
</body>
</html>
