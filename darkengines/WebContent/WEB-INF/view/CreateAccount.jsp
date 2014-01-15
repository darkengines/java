<%@ include file="Header.jsp" %>
<div class="Content">
	<form method="post" class="CreateAccount" action="create_account_test">
		<div class="Title">
			<h3>Création de compte</h3>
		</div>
		<div>
		<div class="Field">
			<input placeholder="Courriel" type="text" id="email"
				name="email" />
				<div class="Validator"></div>
		</div>
		<div class="Field">
			<input placeholder="Mot de passe" type="password"
				id="password" name="password" />
				<div class="Validator"></div>
		</div>
		<div class="Field">
			<input placeholder="Retapez votre mot de passe"
				type="password" id="password_confirmation"
				name="password_confirmation" />
				<div class="Validator"></div>
		</div>
		<div class="Field">
			<button type="submit" class="Button" value="Offerrer" name="type">Je suis développeur</button>
			<button type="submit" value="Caller" name="type">Je cherche un dev</button>
		</div>
		</div>
	</form>
</div>
<%@ include file="Footer.jsp" %>