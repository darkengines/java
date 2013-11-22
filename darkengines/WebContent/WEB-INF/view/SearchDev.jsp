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
		<div class="Field">
			<label for="programmingLanguageIds">Languages de
				programmation maîtrisés</label> <input type="text"
				id="programmingLanguageIds" name="programmingLanguageIds" />
		</div>
		<div class="Field">
			<label for="frameworkIds">Frameworks maîtrisés</label> <input type="text" id="frameworkIds"
				name="frameworkIds" />
		</div>
		<div class="Field">
			<label for="languageIds">Langages parlés</label>
			<input type="text" id="languageIds" name="languageIds" />
		</div>
		<div class="Field">
			<label for="diploma_ui">Niveau d'études</label> <input type="text"
				id="diploma_ui" name="diploma_ui" />
		</div>
		<div class="Field">
			<label for="seniority">Années d'expérience</label> <input type="text"
				id="seniority" name="seniority" />
			<div class="SeniorityUi"></div>
		</div>
		<div class="Field">
			<button name="submit">Rechercher</button>
		</div>
	</form>
	<div class="SearchResult">
		<h3>Resultats</h3>
		<div class="Collection"></div>
	</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>