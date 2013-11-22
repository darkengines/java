<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<div class="Content">
	<form method="post" action="update_profile_test" class="UpdateProfile">
		<input name="diplomaId" type="hidden" />
		<div class="Title">
			<h3>
				Profil <span class="Notification"></span>
			</h3>
		</div>
		<div class="Field">
			<label for="photo">Photo</label> <input type="file"
				id="photo" name="photo" />
			<img class="Photo" alt="Photo" />
		</div>
		<div class="Field">
			<label for="programmingLanguageIds">Languages de
				programmation ma�tris�s</label> <input type="text"
				id="programmingLanguageIds" name="programmingLanguageIds" />
		</div>
		<div class="Field">
			<label for="frameworkIds">Frameworks ma�tris�s</label> <input type="text" id="frameworkIds"
				name="frameworkIds" />
		</div>
		<div class="Field">
			<label for="languageIds">Langages parl�s</label>
			<input type="text" id="languageIds" name="languageIds" />
		</div>
		<div class="Field">
			<label for="diploma_ui">Niveau d'�tudes</label> <input type="text"
				id="diploma_ui" name="diploma_ui" />
		</div>
		<div class="Field">
			<label for="seniority">Ann�es d'exp�rience</label> <input type="text"
				id="seniority" name="seniority" />
			<div class="SeniorityUi"></div>
		</div>
		<div class="Field">
			<button name="submit">Enregistrer</button>
		</div>
	</form>
</div>
<jsp:include page="Footer.jsp"></jsp:include>