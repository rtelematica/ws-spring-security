<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<h1>This is secured!</h1>
	<p>
		Hello <b><c:out value="${pageContext.request.remoteUser}" /></b>
	</p>

	<p>The time on the server is ${serverTime}.</p>

	<br />

	<c:url var="logoutUrl" value="/logout" />
	<form action="${logoutUrl}" method="POST">
		<input type="submit" value="Log out" /> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>

</body>
</html>
