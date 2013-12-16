<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<div class="Content">
	<form method="post" action="update_contact_test" class="UpdateContact" data-load-url="get_contact_test?data={userId:<%= request.getAttribute("userId") %>, callId:<%= request.getParameter("callId") %>}">
		<input type="hidden" name="token" value="<%= request.getAttribute("token") %>" />
	</form>
<jsp:include page="Footer.jsp"></jsp:include>