<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<div class="Content">
	<div class="Profile">
		<div class="Title">
			<h3>
				Profil <span class="Notification"></span>
			</h3>
		</div>
		<div class="Field">
			<img class="Photo" alt="Photo" />
			<div class="Email"></div>
		</div>
		<dl>
			<dt>Langages maîtrisés</dt>
			<dd class="Field ms-sel-ctn ProgrammingLanguages"></dd>
		</dl>
		<dl>
			<dt>Frameworks maîtrisés</dt>
			<dd class="Field ms-sel-ctn Frameworks"></dd>
		</dl>
		<dl>
			<dt>Langages parlés</dt>
			<dd class="Field ms-sel-ctn Languages"></dd>
		</dl>
		<dl>
			<dt>Niveau d'étude</dt>
			<dd class="Field Diploma"></dd>
		</dl>
		<dl>
			<dt>Années d'expérience</dt>
			<dd class="Field Seniority"></dd>
		</dl>
	</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>