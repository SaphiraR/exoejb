<%@page pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Adhésion</title>
	<style type="text/css">
.error {
	color: red;
}
	</style>
</head>
<body>
	<section>
		<form method="POST">
			<p>
				<label>Email</label>
				<input name="email" type="text" value='<c:out value="${adhesion.email}"/>'>
				<c:if test="${raisons['adhesion.email.vide']}">
					<p class="error">Vous devez saisir votre email&nbsp;!</p>
				</c:if>
				<c:if test="${raisons['adhesion.email.invalide']}">
					<p class="error">L'email saisi ne semble pas correct&nbsp;!</p>
				</c:if>
			</p>

			<p>
				<label>Mot de passe</label>
				<input name="motDePasse" type="password" value="">
				<c:if test="${raisons['adhesion.motDePasse.tropCourt']}">
					<p class="error">Le mot de passe doit contenir au moins 8 caractères&nbsp;!</p>
				</c:if>
			</p>

			<p>
				<label>Confirmer votre mot de passe</label>
				<input name="confirmationMotDePasse" type="password" value="">
				<c:if test="${raisons['adhesion.motDePasse.confirmationIncorrecte']}">
					<p class="error">Les deux mots de passe saisis ne sont pas identiques&nbsp;!</p>
				</c:if>
			</p>

			<p>
				<input name="conditionsAcceptees" type="checkbox" <c:if test="${adhesion.conditionsAcceptees}">checked="checked"</c:if>>
				<label>J'ai lu et approuvé les conditions générales de ce site</label>
				<c:if test="${raisons['adhesion.conditionsNonAcceptees']}">
					<p class="error">Vous devez accepter les conditions générales&nbsp;!</p>
				</c:if>
			</p>

			<p><button type=submit>Valider</button></p>
		</form>

		<p><a href="<c:url value="/index.jsp"/>">Retour à l'accueil</a></p>
	</section>
</body>
</html>
