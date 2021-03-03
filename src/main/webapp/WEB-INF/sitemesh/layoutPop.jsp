<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" type="image/x-icon" href="/img/favicon/favicon.png">
	
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="Megazone Cloud">
	
	<title><sitemesh:write property='title'/></title>

    <sitemesh:write property='head'/>
</head>

<body>
	<!-- Wrap -->
	<div id="wrapperpop">
    	<sitemesh:write property='body'/>
	</div>

</body>
</html>
