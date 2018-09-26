<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form action="do-login" method="post">
		<p>
			<label for="usuario">Usuario</label> 
			<input type="text" name="u">
		</p>
		<p>
			<label for="password">Password</label> 
			<input type="password" name="p">
		</p>
		<p>
			<input type="submit" value="Acceder">
		</p>
	</form>
</body>
</html>