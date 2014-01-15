<%@ include file="Header.jsp" %>
<div class="Content">
	<div class="Title">
		<h3>Propositions</h3>
	</div>
	<div class="GetCalls" data-load-url="get_call_test?data={userId:<%=request.getAttribute("userId")%>, callId:<%=request.getParameter("callId")%>}">
		<div class="Controls"><a href="update_call" class="Button">Créer une offre</a></div>
		<div class="Calls"></div>
	</div>
</div>
<%@ include file="Footer.jsp" %>