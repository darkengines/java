<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<div class="Content">
	<form method="post" action="">
		<div class="Title">
			<h3>Your identity</h3>
		</div>
		<div class="Field">
			<label for="lastname">Lastname</label> <input type="text"
				id="lastname" name="lastname" />
		</div>
		<div class="Field">
			<label for="firstname">Firstname</label> <input type="text"
				id="firstname" name="firstname" />
		</div>
		<div class="Field">
			<label for="company">Company</label> <input type="text" id="company"
				name="company" />
		</div>
		<div class="Field">
			<label for="phone">Phone</label> <input type="text" id="phone"
				name="phone" />
		</div>
		<div class="Field">
			<label for="email">Email</label> <input type="text" id="email"
				name="email" />
		</div>
		<div class="Field">
			<label for="password">Password</label> <input type="password"
				id="password" name="password" />
		</div>
		<div class="Field">
			<label for="password_confirmation">Retype password</label> <input
				type="password" id="password_confirmation"
				name="password_confirmation" />
		</div>
		<div class="Title">
			<h3>Project</h3>
		</div>
		<div class="Field">
			<label for="project_description">Project description</label>
			<textarea id="project_description" name="project_description"></textarea>
		</div>
		<div class="Field">
			<div class="File">
				<div class="Browse">Browse...</div>
				<div class="Filename">File</div>
				<input style="display: none;" type="file" id="project_file"
					name="project_file">
			</div>
		</div>
		<div class="Field">
			<input type="submit" value="Submit" />
		</div>
	</form>
</div>
<jsp:include page="Footer.jsp"></jsp:include>