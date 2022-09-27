<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/vittoriostyle.css" rel="stylesheet">

<meta charset="ISO-8859-1">
<title>Register page</title>
</head>
<body>

	<form class="reg" action="RegisterServlet" method="post">
				
		<label for="user">Username</label>
		<input type="text" id="user" name="username" placeholder="Insert username">
				
		<label for="indirizzo">Indirizzo</label>
		<input type="text" id="indirizzo" name="indirizzo" placeholder="Insert indirizzo">
				
		<label for="luogo">Luogo</label>
		<input type="text" id="luogo" name="luogo" placeholder="Insert luogo">
		
		<label for="pass">Password</label>
		<input type="password" id="pass" name="password" placeholder="Insert password">
		
		<button type="submit" value="Registrati" name="pulsante">Registrati</button>
	</form>

</body>
</html>