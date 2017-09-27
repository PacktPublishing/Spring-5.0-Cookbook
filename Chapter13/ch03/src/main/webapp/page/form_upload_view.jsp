<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="put_title" /></title>
</head>
<body>
<form:form  modelAttribute="uploadFileForm"  action="${pageContext.request.contextPath}/simple/upload.html"  enctype="multipart/form-data" method="PUT">

   
    <input type="file"name="file"/>
    <input type="submit"  value="Submit"/>
   
</form:form>
</body>
</html>