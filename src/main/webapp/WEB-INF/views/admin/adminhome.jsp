<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Home</title>
</head>
<body>
	This is Admin Home page!!!
	<br/>
	<br/>
	<a href="/admin/memberList">멤버조회</a>
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