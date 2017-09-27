<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
             <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="login_title" /></title>
</head>
<body>
   <form action="<c:url value='/login_emps.html' />" method="POST">
     <spring:message code="user"  /><input type="text" name="username"  /><br/>
     <spring:message code="password"  /><input type="text" name="password"  /><br/>
     <input type="submit" value="Login" />
  </form>

</body>
</html>