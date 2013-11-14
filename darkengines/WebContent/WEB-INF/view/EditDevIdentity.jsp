<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<div class="Content">
	<form method="post" action="update_identity_test" class="UpdateIdentity LabelInline">
		<input type="hidden" name="birthDate"/>
		<input type="hidden" name="cityId"/>
		<div class="Title">
			<h3>Informations personnelles <span class="Notification"></span></h3>
		</div>
		<div class="Field">
			<label for="lastName">Nom</label> <input type="text" id="lastName"
				name="lastName" />
				<div class="Result"></div>
		</div>
		<div class="Field">
			<label for="firstName">Prénom</label> <input type="text" id="firstName"
				name="firstName" />
				<div class="Result"></div>
		</div>
		<div class="Field">
			<label for="birthDate">Date de naissance</label> <input type="text" id="birthDate"
				name="birthDate_ui" />
				<div class="Result"></div>
		</div>
		<div class="Field">
			<label for="city">Ville</label> <input type="text" id="city"
				name="city_ui" />
				<div class="Result"></div>
		</div>
		<div class="Field">
			<label for="address">Adresse</label> <input type="text" id="address"
				name="address" />
				<div class="Result"></div>
		</div>
		<div class="Field">
			<button name="submit">Enregistrer</button>
		</div>
	</form>
</div>
<jsp:include page="Footer.jsp"></jsp:include>