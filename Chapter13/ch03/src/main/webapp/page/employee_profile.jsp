<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="employee_profile" /></title>
</head>
<body>
  <h1><spring:message code="employee_profile" /></h1>
  <table>
      <tr>
         <th>First Name</th>
         <th>Last Name</th>
         <th>Position</th>
         <th>Age</th>
         <th>Date of Birth</th>
         <th>Email</th>
      </tr>
       <tr>
        <td><c:out value='${ employeeForm.firstName }'/></td>
        <td><c:out value='${ employeeForm.lastName }' /></td>
        <td><c:out value='${ employeeForm.position }' /></td>
        <td><c:out value='${ employeeForm.age }' /></td>
        <td><fmt:formatDate value="${employeeForm.birthday}" type="date" /></td>
        <td><c:out value='${ employeeForm.email }' /></td>
      </tr>
      
  </table>
</body>
</html>