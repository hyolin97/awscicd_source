<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/jstl-tld.jsp" %>

<%
String url = request.getRequestURL().toString();
String strCurrentUrl = request.getScheme()+"://"+request.getServerName() + ":" + request.getServerPort();
%>

<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="/css/common.css" />	
 
 </head>
 
<body class="error_bg">
	<div>
		<form id="fromerror" name="fromerror">
			
			<input type="hidden" name="ERROR_CODE" id="ERROR_CODE" value="${requestScope['javax.servlet.error.status_code']}"/>
			<input type="hidden" name="ERROR_TYPE" id="ERROR_TYPE" value="${requestScope['javax.servlet.error.exception_type']}"/>
			<input type="hidden" name="ERROR_MSG" id="ERROR_MSG" value=""/>
			<input type="hidden" name="ERROR_EXCEPTION" id="ERROR_EXCEPTION" value="${requestScope['javax.servlet.error.exception']}"/>
			<input type="hidden" name="ERROR_URL" id="ERROR_URL" value="${requestScope['javax.servlet.error.request_url']}"/>
			<input type="hidden" name="ERROR_SERVLET" id="ERROR_SERVLET" value="${requestScope['javax.servlet.error.servlet_name']}"/>							
		</form>
	</div>
	
	<div class="error_10">
		<img alt="요청하신 페이지를 찾을 수 없습니다." src="/img/errors/error_img.png">
		<ul>
			<li><strong>요청하신 페이지의 주소가 잘못 입력되었거나,<br>
						페이지 주소의 변경/삭제로 인하여 요청하신 <br>
						페이지를 찾을 수 없습니다.</strong></li>
			<li style="padding-top:20px;"><button type="button" class="kbtn k-primary" onclick="#">이전페이지로 이동</button> 
			<%-- <button type="button" class="kbtn k-primary" onclick="location.href='<%=strCurrentUrl%>/login.go'">홈으로 이동</button></li> --%>
			<button type="button" class="kbtn k-primary" onclick="location.href='<%=strCurrentUrl%>/'">홈으로 이동</button></li>
		
		</ul>
	
	</div>
	
	
	<div class="error_10" style="display:none">
		
		<ul>
		
			<li>&bull; 에러코드:${requestScope['javax.servlet.error.status_code']}</li>
			<li>&bull; 에러타입:${requestScope['javax.servlet.error.exception_type']}</li>
			<li id ="errormessage">&bull; 에러메시지:${requestScope['javax.servlet.error.message']}</li>
			<li>&bull; 에러객체:${requestScope['javax.servlet.error.exception']}</li>
			<li>&bull; 에러REFFERE_URL:${requestScope['javax.servlet.error.request_url']}</li>
			<li>&bull; 에러서블릿:${requestScope['javax.servlet.error.servlet_name']}</li>
		</ul>
	</div>
		
</body>

</html>