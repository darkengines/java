<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<div class="Content">
	<form method="post" action="">
		<div class="Title">
			<h3>Informations personnelles</h3>
		</div>
		<div class="Field">
			<label for="email">Nom</label> <input type="text" id="email"
				name="email" />
				<div class="Result Error">Ce courriel n'est pas valide</div>
		</div>
		<div class="Field">
			<label for="email">Prénom</label> <input type="text" id="email"
				name="email" />
				<div class="Result Error">Ce courriel n'est pas valide</div>
		</div>
		<div class="Field">
			<label for="email">Date de naissance</label> <input type="text" id="email"
				name="email" />
				<div class="Result Error">Ce courriel n'est pas valide</div>
		</div>
		<div class="Field">
			<label for="email">Pays</label> <input type="text" id="email"
				name="email" />
				<div class="Result Error">Ce courriel n'est pas valide</div>
		</div>
		<div class="Field">
			<label for="email">Code postal</label> <input type="text" id="email"
				name="email" />
				<div class="Result Error">Ce courriel n'est pas valide</div>
		</div>
		<div class="Field">
			<label for="email">Ville</label> <input type="text" id="email"
				name="email" />
				<div class="Result Error">Ce courriel n'est pas valide</div>
		</div>
		<div class="Field">
			<label for="email">Adresse</label> <input type="text" id="email"
				name="email" />
				<div class="Result Error">Ce courriel n'est pas valide</div>
		</div>
		<div class="Field">
			<button name="submit">Enregistrer</button>
		</div>
	</form>
</div>
<jsp:include page="Footer.jsp"></jsp:include>