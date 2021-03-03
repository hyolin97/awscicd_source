<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/jstl-tld.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>LOGIN</title>
</head>
<body>

<form method="post" action="/login">
<input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />

	<c:if test="${param.error != null}">
		<div>
			<p>Invalid username and password.</p>
		</div>
	</c:if>
	<c:if test="${param.logout != null}">
		<div>
			<p>You have been logged out successfully.</p>
		</div>
	</c:if>
	
	<div>
		Username : <input type="text" id="username" name="username" placeholder="Enter Username" required>
	</div>
	<div>
		Password : <input type="password" id="password" name="password" placeholder="Enter Password" required>
	</div>
		
	<div>
		<input type="submit" value="Log in">
	</div>
	
</form>

</body>
</html>