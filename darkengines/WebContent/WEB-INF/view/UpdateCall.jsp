<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<div class="Content">
	<form method="post" action="update_call_test" class="UpdateCall" data-load-url="get_call_test?data={userId:<%= request.getAttribute("userId") %>, callId:<%= request.getParameter("callId") %>}">
		<input type="hidden" name="token" value="<%= request.getAttribute("token") %>" />
		<div class="Title">
			<h3>Proposition</h3>
		</div>
		<div>
		<div class="Field">
			<label for="callType">Type</label> 
			<input type="text" id="callType"
				name="callType" />
				<div class="Validator"></div>
		</div>
		<div class="Field">
			<label for="salary">Salary</label> 
			<input type="text" id="salary"
				name="salary" />
				<div class="Validator"></div>
		</div>
		<div class="Field">
			<label for="length">Length</label> 
			<input type="text" id="length"
				name="length" />
				<div class="Validator"></div>
		</div>
		<div class="Field">
			<label for="budget">Budget</label> 
			<input type="text" id="budget"
				name="budget" />
				<div class="Validator"></div>
		</div>
	</form>
<jsp:include page="Footer.jsp"></jsp:include>