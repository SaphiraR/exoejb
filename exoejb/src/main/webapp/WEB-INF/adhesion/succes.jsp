<%@page pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Adhésion</title>
</head>
<body>
	<p>Votre inscription a bien été prise en compte le <fmt:formatDate value="${adhesion.dateAdhesion}" pattern="dd MMMM YYYY 'à' HH:mm:ss"/> pour l'adresse mail <c:out value="${adhesion.email}"/>.</p>
	<p><a href="<c:url value="/index.jsp"/>">Retour à l'accueil</a></p>
  	<p><a href="<c:url value="/adhesions"/>">Voir la liste des adhésions</a></p>
</body>
</html>
