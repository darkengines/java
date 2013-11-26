<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<div class="Content">
	<form method="post" action="search_dev_test" class="SearchDev">
		<input name="diplomaId" type="hidden" />
		<div class="Title">
			<h3>
				Rechercher un dev <span class="Notification"></span>
			</h3>
		</div>
		<div>
		<div class="Field">
			<label for="programmingLanguageIds">Langages de
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
			<label for="diploma">Niveau d'�tudes</label> <input type="hidden"
				id="diploma" name="diploma" />
				<div class="DiplomaDisplay">Aucun</div>
			<div class="DiplomaUi"></div>
		</div>
		<div class="Field">
			<label for="seniority">Ann�es d'exp�rience</label> <input type="hidden"
				id="seniority" name="seniority" />
			<div class="SeniorityDisplay">Aucune</div>
			<div class="SeniorityUi"></div>
		</div>
		<div class="Field">
			<button name="submit">Rechercher</button>
		</div>
		</div>
	</form>
	<div class="SearchResult">
		<h3>Resultats</h3>
		<div class="Collection"></div>
	</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>