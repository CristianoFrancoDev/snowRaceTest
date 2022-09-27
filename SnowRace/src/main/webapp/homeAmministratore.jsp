<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Amministatore</title>
<link href="css/vittoriostyle.css" rel="stylesheet">
</head>
<body>

<%@include file="css/header.jsp"%>

<div class="navbar">
  <a class="active" href="homeAmministratore.jsp">Home</a>
  <a href="UserServlet?mode=userlist">Impianti</a>
  <a href="UserServlet?mode=userlist">Utenti</a>
  <a href="UserServlet?mode=userlist">Piste</a>
  <a href="profiloUtente.jsp">Profilo</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>

<div class="header">
	<h3>Welcome Amministatore</h3>
</div>


<%@ include file="css/footer.jsp" %>

</body>
</html>