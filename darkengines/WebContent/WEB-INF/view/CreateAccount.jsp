<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<div class="Content">
	<form method="post" action="">
		<div class="Title">
			<h3>Création de compte</h3>
		</div>
		<div class="Field">
			<label for="email">Courriel</label> <input type="text" id="email"
				name="email" />
				<div class="Result Error">Ce courriel n'est pas valide</div>
		</div>
		<div class="Field">
			<label for="password">Mot de passe</label> <input type="password"
				id="password" name="password" />
				<div class="Result Ok">Ok</div>
		</div>
		<div class="Field">
			<label for="password_confirmation">Retapez votre mot de passe</label> <input
				type="password" id="password_confirmation"
				name="password_confirmation" />
				<div class="Result Error">Les mots de passe de correspondent pas</div>
		</div>
		<div class="Field">
			<button name="dev">Je suis développeur</button>
			<button name="offerer">Je cherche un dev</button>
		</div>
	</form>
</div>
<jsp:include page="Footer.jsp"></jsp:include>