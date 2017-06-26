<%@page pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
  </head>
  <body>
  
  	<table>
  		<caption>Liste des adhérents</caption>
  		<tbody>
  			<c:forEach var="a" items="${adhesions}">
  				<tr>
  					<td><fmt:formatDate value="${a.dateAdhesion}"/></td>
  					<td><c:out value="${a.email}"/></td>
  				</tr>
  			</c:forEach>
  		</tbody>
  	</table>
  
  	<p><a href="<c:url value="/index.jsp"/>">Retour à l'accueil</a></p>
  
  </body>
</html>