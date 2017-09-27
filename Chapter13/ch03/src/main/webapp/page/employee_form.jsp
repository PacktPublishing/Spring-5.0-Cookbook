<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="employee_form" /></title>
</head>
<body>
   <h1><spring:message code="employee_form" /></h1>
   <form:form modelAttribute="employeeForm" method="post">
      <spring:message code="fnameLbl" /> 
            <form:input path="firstName"/>&nbsp;&nbsp;<form:errors path="firstName"/><br/>
      <spring:message code="lnameLbl" />
      		<form:input path="lastName"/>&nbsp;&nbsp;<form:errors path="lastName"/><br/>
      <spring:message code="posLbl" />
      		<form:input path="position"/>&nbsp;&nbsp;<form:errors path="position"/><br/>
     
      <hr/>
      <em>Added Information</em><br/>
      <spring:message code="ageLbl" /> 
            <form:input path="age"/>&nbsp;&nbsp;<form:errors path="age"/><br/>
      <spring:message code="bdayLbl" />
      		<form:input path="birthday"/>&nbsp;&nbsp;<form:errors path="birthday"/><br/>
      <spring:message code="emailLbl" />
      		<form:input path="email"/>&nbsp;&nbsp;<form:errors path="email"/><br/>
      <input type="submit" value="Add Employee"/><br>
      
   </form:form>
</body>
</html>