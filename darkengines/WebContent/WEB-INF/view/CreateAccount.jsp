<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<div class="Content">
	<form method="post" class="CreateAccount" action="create_account_test">
		<div class="Title">
			<h3>Création de compte</h3>
		</div>
		<div>
		<div class="Field">
			<input placeholder="Courriel" type="text" id="email"
				name="email" />
				<div class="Result"></div>
		</div>
		<div class="Field">
			<input placeholder="Mot de passe" type="password"
				id="password" name="password" />
				<div class="Result"></div>
		</div>
		<div class="Field">
			<input placeholder="Retapez votre mot de passe"
				type="password" id="password_confirmation"
				name="password_confirmation" />
				<div class="Result"></div>
		</div>
		<div class="Field">
			<button type="submit" class="Button" value="Dev" name="type">Je suis développeur</button>
			<button type="submit" value="Offerer" name="type">Je cherche un dev</button>
		</div>
		</div>
	</form>
</div>
<jsp:include page="Footer.jsp"></jsp:include>