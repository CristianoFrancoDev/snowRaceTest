<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home User</title>
</head>
<body>

<%@include file="css/header.jsp"%>

<div class="navbar">
  <a class="active" href="homeUser.jsp">Home</a>
  <a href="">Biglietti</a>
  <a href="UserServlet?mode=userlist">Impianti</a>
  <a href="UserServlet?mode=userlist">Noleggi</a>
  <a href="UserServlet?mode=userlist">Profilo</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>

<div class="header">
	<h3>Welcome User</h3>
</div>


<%@ include file="css/footer.jsp" %>

</body>
</html>