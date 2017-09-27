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
 <form:form modelAttribute="employeeForm" method="POST">
     <spring:message code="id"  /><form:input path="empId"  /><br/>
     <spring:message code="first"  /><form:input path="firstName"  /><br/>
     <spring:message code="last"  /><form:input path="lastName"  /><br/>
     <spring:message code="age"  /><form:input path="age"  /><br/>
     <spring:message code="email"  /><form:input path="email"  /><br/>
     <spring:message code="dept"  /><form:select path="deptId" items="${ deptIds }" /><br/>
     <spring:message code="bday"  /><form:input path="birthday"  /><br/>
     <input type="submit" value="Add Employee" />
  </form:form>
</body>
</html>