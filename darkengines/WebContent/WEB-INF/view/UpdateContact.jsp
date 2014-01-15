<%@ include file="Header.jsp" %>
<div class="Content">
	<form method="post" action="update_contact_test" class="UpdateContact" data-load-url="get_contact_test?data={userId:<%= request.getAttribute("userId") %>}">
	<input type="hidden" name="token" value="<%= request.getAttribute("token") %>" />
		<div class="Title">
			<h3>
				Contact <span class="Notification"></span>
			</h3>
		</div>
		<div>
			<div class="Field">
				<label for="email">Courriel</label> <input type="text" id="email"
					name="email" />
			</div>
			<div class="Field">
				<label for="phone">Téléphone</label> <input type="text"
					id="phone" name="phone" />
			</div>
			<div class="Field">
				<button name="submit">Enregistrer</button>
			</div>
		</div>
	</form>
</div>
<%@ include file="Footer.jsp" %>