<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<div class="Content">
	<div class="Profile">
		<div class="Title">
			<h3>
				Profil <span class="Notification"></span>
			</h3>
		</div>
		<div class="Field Identity">
			<img class="Photo" alt="Photo" />
			<div>
				<dl>
					<dt>Nom</dt>
					<dd class="Lastname">Tollin de Rivarol</dd>
					<dt>Prénom</dt>
					<dd class="Firstname">Florent</dd>
					<dt>Florent</dt>
					<dt>Date de naissance</dt>
					<dd class="BirthDate">13/11/1989</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt>Courriel</dt>
					<dd class="Email"></dd>
					<dt>Téléphone</dt>
					<dd class="Phone">06 69 78 83 11</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt>Code Postal</dt>
					<dd class="PostalCode">92400</dd>
					<dt>Ville</dt>
					<dd class="City">Courbevoie</dd>
					<dt>Adresse</dt>
					<dd class="Address">40 rue Baudin</dd>
				</dl>
			</div>
		</div>
		</div>
		<dl>
			<dt>Langages maîtrisés</dt>
			<dd class="Field ms-sel-ctn ProgrammingLanguages"></dd>
			<dt>Frameworks maîtrisés</dt>
			<dd class="Field ms-sel-ctn Frameworks"></dd>
			<dt>Langages parlés</dt>
			<dd class="Field ms-sel-ctn Languages"></dd>
			<dt>Niveau d'étude</dt>
			<dd class="Field Diploma"></dd>
			<dt>Années d'expérience</dt>
			<dd class="Field Seniority"></dd>
		</dl>
	</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>