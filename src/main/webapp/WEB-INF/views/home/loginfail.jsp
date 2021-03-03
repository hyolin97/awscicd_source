<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl-tld.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Failure</title>
</head>
<body>

<input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />

	<div>
		username : ${username} 에 대한 ${message}
		<br/>
		로그인 실패하였습니다.
		<br/>
	</div>
	<div>
		<a href="/login">확인</a>
	</div>

</body>
</html>