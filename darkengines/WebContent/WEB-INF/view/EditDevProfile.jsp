<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<div class="Content">
	<form method="post" action="update_profile_test" class="UpdateProfile" data-load-url="get_profile_test?data={offerrerId:<%= request.getAttribute("userId") %>}">
	<input type="hidden" name="token" value="<%= request.getAttribute("token") %>" />
		<div class="Title">
			<h3>
				Profil <span class="Notification"></span>
			</h3>
		</div>
		<div>
			<div class="Field">
				<label for="photo">Photo</label> <input type="file" id="photo"
					name="photo" /> <img class="PhotoDisplay" alt="Photo" />
			</div>
			<div class="Field">
				<label for="programmingLanguageIds">Langages de
					programmation maîtrisés</label> <input type="text"
					id="programmingLanguageIds" name="programmingLanguageIds" />
			</div>
			<div class="Field">
				<label for="frameworkIds">Frameworks maîtrisés</label> <input
					type="text" id="frameworkIds" name="frameworkIds" />
			</div>
			<div class="Field">
				<label for="languageIds">Langages parlés</label> <input type="text"
					id="languageIds" name="languageIds" />
			</div>
			<div class="Field">
				<label for="diploma">Niveau d'études</label> <input type="hidden"
					id="diploma" name="diploma" />
				<div class="DiplomaDisplay">Aucun</div>
				<div class="DiplomaEditor"></div>
			</div>
			<div class="Field">
				<label for="seniority">Années d'expérience</label> <input
					type="hidden" id="seniority" name="seniority" />
				<div class="SeniorityDisplay">Aucune</div>
				<div class="SeniorityEditor"></div>
			</div>
			<div class="Field">
				<button name="submit">Enregistrer</button>
			</div>
		</div>
	</form>
</div>
<jsp:include page="Footer.jsp"></jsp:include>