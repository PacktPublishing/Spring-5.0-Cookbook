<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <c:set var="msg" value="${ param.message }" />
    <c:if test="${ not empty msg }">
        <h1><c:out value='${ msg }' /></h1>
    </c:if>
      <c:if test="${ empty msg }">
        <h1>Thank you! Come back again!</h1>
    </c:if>
    
    <em><a href="<c:url value='/login_form.html'/>">Try to login again.</a></em>
</body>
</html>