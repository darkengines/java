<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<div class="Content">
	<form method="post" class="Login" action="login_test">
		<div class="Title">
			<h3>Connexion</h3>
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
			<button type="submit" value="Dev">Ok</button>
		</div>
		</div>
	</form>
</div>
<jsp:include page="Footer.jsp"></jsp:include>