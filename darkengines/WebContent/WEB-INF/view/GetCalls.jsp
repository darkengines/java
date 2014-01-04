<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<div class="Content">
	<div class="Title">
		<h3>Propositions</h3>
	</div>
	<div class="GetCalls" data-load-url="get_call_test?data={userId:<%=request.getAttribute("userId")%>, callId:<%=request.getParameter("callId")%>}">
		<div class="Controls"><a href="update_call" class="Button">Créer une offre</a></div>
		<div class="Calls"></div>
	</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>