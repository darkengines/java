<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
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
					<label for="length">Durée</label> <input type="text" id="length"
						name="length" />
					<div class="Validator"></div>
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
					<button type="submit" value="Enregistrer">Enregistrer</button>
				</div>
			</div>
		</form>
	</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>