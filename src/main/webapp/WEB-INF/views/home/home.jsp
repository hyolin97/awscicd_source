<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	This is Home page!!!
	<br/>
	<br/>
	<form method="post" action="/logout">
		<input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />
		<div>
			<input type="submit" value="Logout">
		</div>
	</form>
</body>
</html>