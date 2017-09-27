<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="simple_patterns_facade" /></title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/simple/form_upload_get.html">GET Transaction</a>
	<br />
	<form action="${pageContext.request.contextPath}/simple/form_upload_post.html" method="post">
		<input type="submit" value="POST Transaction" />
	</form>
</body>
</html>