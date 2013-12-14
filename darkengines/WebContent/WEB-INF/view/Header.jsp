<%@page import="application.Util"%>
<%@page import="caller_offerrer.UserType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Je cherche un dev</title>
  <link type="text/css" rel="stylesheet" href="css/darkengines.css">
  <link type="text/css" rel="stylesheet" href="css/jquery_ui.css">
  <link type="text/css" rel="stylesheet" href="css/jquery_magicsuggest.css">
  <link type="text/css" rel="stylesheet" href="css/suggest.css">
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript" src="js/jquery_ui.js"></script>
  <script type="text/javascript" src="js/jquery_cookie.js"></script>
  <script type="text/javascript" src="js/jquery_ui_datepicker_fr.js"></script>
  <script type="text/javascript" src="js/jquery_purl.js"></script>
  <script type="text/javascript" src="js/jquery_magicsuggest.js"></script>
  <script type="text/javascript" src="js/modernizr.js"></script>
  <script type="text/javascript" src="js/modernize.js"></script>
  <script type="text/javascript" src="js/form.js"></script>
  <script type="text/javascript" src="js/validator.js"></script>
  <script type="text/javascript" src="js/darkengines.js"></script>
  <script type="text/javascript" src="js/suggest.js"></script>
</head>
<body>
<div class="Container">
	<div class="Header">Je cherche un dev</div>
	<div class="Menu">
		<div class="Left">
		<% if (request.getAttribute("userId") != null) {%>
			<a href="update_contact">Contact</a>
		<%} %>
		<% if (request.getAttribute("userType") == UserType.Offerrer) {%>
			<a href="edit_dev_profile">Mon profil</a>
		<%} %>
		<% if (request.getAttribute("userType") == UserType.Caller) {%>
			<a href="update_calls">Mes offres</a>
		<%} %>
			<a href="search_dev">Recherche</a>
		</div>
		<div class="Right">
		<% if (request.getAttribute("userId") != null) {%>
			<a href="/" class="Disconnect">Déconnexion</a>
		<%} else { %>
			<a href="login">Connexion</a>
			<a href="register">Ouvrir un compte</a>
		<%} %>
		</div>
	</div>