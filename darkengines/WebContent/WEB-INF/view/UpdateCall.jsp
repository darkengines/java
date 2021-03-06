<%@ include file="Header.jsp" %>
<div class="Content">
	<div class="Title">
		<h3>Proposition</h3>
	</div>
	<div class="UpdateCall"
		data-load-url="get_call_test?data={userId:<%=request.getAttribute("userId")%>, callId:<%=request.getParameter("callId")%>}">
		<div class="Field Type">
			<label for="callType">Type</label> <input type="text" id="callType"
				name="callType" />
			<div class="Validator"></div>
		</div>
		<form method="post" action="update_call_test" class="PermanentContract" style="display:none;">
			<input type="hidden" name="token"
				value="<%=request.getAttribute("token")%>" /> <input
				type="hidden" name="type" value="PermanentContract" />
			<input type=hidden value="<%=request.getParameter("callId")%>" name="callId"/>
			<div class="Fields">
				<div class="Field">
					<label for="title">Titre</label> <input type="text" id="title"
						name="title" />
					<div class="Validator"></div>
				</div>
				<div class="Field CDI CDD">
					<label for="salary">Salaire</label> <input type="text" id="salary"
						name="salary" />
					<div class="Validator"></div>
				</div>
				<div class="Field">
					<label for="description">Description</label> 
					<textarea id="description" name="description"></textarea>
				</div>
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
					<div class="DiplomaEditor"></div>
				</div>
				<div class="Field">
					<label for="seniority">Ann�es d'exp�rience</label> <input type="hidden"
						id="seniority" name="seniority" />
					<div class="SeniorityDisplay">Aucune</div>
					<div class="SeniorityEditor"></div>
				</div>
				<div class="Field">
					<button type="submit" value="Enregistrer">Enregistrer</button>
				</div>
			</div>
		</form>
		<form method="post" action="update_call_test" class="FixedTermContract" style="display:none;">
			<input type="hidden" name="token"
				value="<%=request.getAttribute("token")%>" /> <input
				type="hidden" name="type" value="FixedTermContract" />
				<input type=hidden value="<%=request.getParameter("callId")%>" name="callId"/>
			<div class="Fields">
				<div class="Field">
					<label for="title">Titre</label> <input type="text" id="title"
						name="title" />
					<div class="Validator"></div>
				</div>
				<div class="Field CDI CDD">
					<label for="salary">Salaire</label> <input type="text" id="salary"
						name="salary" />
					<div class="Validator"></div>
				</div>
				<div class="Field CDD">
					<label for="length">Dur�e</label> <input type="text" id="length"
						name="length" />
					<div class="Validator"></div>
				</div>
				<div class="Field">
					<label for="description">Description</label> 
					<textarea id="description" name="description"></textarea>
				</div>
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
					<div class="DiplomaEditor"></div>
				</div>
				<div class="Field">
					<label for="seniority">Ann�es d'exp�rience</label> <input type="hidden"
						id="seniority" name="seniority" />
					<div class="SeniorityDisplay">Aucune</div>
					<div class="SeniorityEditor"></div>
				</div>
				<div class="Field">
					<button type="submit" value="Enregistrer">Enregistrer</button>
				</div>
			</div>
		</form>
		<form method="post" action="update_call_test" class="Freelance" style="display:none;">
			<input type="hidden" name="token"
				value="<%=request.getAttribute("token")%>" /> <input
				type="hidden" name="type" value="Freelance" />
				<input type=hidden value="<%=request.getParameter("callId")%>" name="callId"/>
			<div class="Fields">
				<div class="Field">
					<label for="title">Titre</label> <input type="text" id="title"
						name="title" />
					<div class="Validator"></div>
				</div>
				<div class="Field Freelance">
					<label for="budget">Budget</label> <input type="text" id="budget"
						name="budget" />
					<div class="Validator"></div>
				</div>
				<div class="Field">
					<label for="description">Description</label> 
					<textarea id="description" name="description"></textarea>
				</div>
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
					<div class="DiplomaEditor"></div>
				</div>
				<div class="Field">
					<label for="seniority">Ann�es d'exp�rience</label> <input type="hidden"
						id="seniority" name="seniority" />
					<div class="SeniorityDisplay">Aucune</div>
					<div class="SeniorityEditor"></div>
				</div>
				<div class="Field">
					<button type="submit" value="Enregistrer">Enregistrer</button>
				</div>
			</div>
		</form>
	</div>
</div>
<%@ include file="Footer.jsp" %>