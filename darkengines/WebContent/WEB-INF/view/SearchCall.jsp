<%@ include file="Header.jsp" %>
<div class="Content">
	<form method="post" action="search_call_test" class="SearchCall" data-load-url="get_search_call_query_test?data={token:<%=request.getAttribute("token")%>}">
		<input type="hidden" name="token" value="<%=request.getAttribute("token")%>" />
		<div class="Title">
			<h3>
				Rechercher un dev <span class="Notification"></span>
			</h3>
		</div>
		<div>
		<div class="Field">
			<label for="programmingLanguageIds">Langages de
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
			<label for="diploma">Niveau d'études</label> <input type="hidden"
				id="diploma" name="diploma" />
				<div class="DiplomaDisplay">Aucun</div>
			<div class="DiplomaEditor"></div>
		</div>
		<div class="Field">
			<label for="seniority">Années d'expérience</label> <input type="hidden"
				id="seniority" name="seniority" />
			<div class="SeniorityDisplay">Aucune</div>
			<div class="SeniorityEditor"></div>
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
<%@ include file="Footer.jsp" %>