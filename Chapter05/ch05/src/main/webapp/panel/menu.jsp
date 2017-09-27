<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
                 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="menu_title" /></title>
</head>
<body>
   <table border="1">
      <c:forEach var="emp" items="${ emps }">
            <tr>
           	    <td>${ emp.id }</td>
                <td>${ emp.empId }</td>
                <td>${ emp.firstName }</td>
                <td>${ emp.lastName }</td>
                <td>${ emp.age }</td>
                <td>${ emp.email }</td>
                <td>${ emp.birthday }</td>
                <td>${ emp.deptId }</td>
                <c:url var="delUrl" value="/deldept.html/${emp.empId}" />
                <td><a href="<c:out value='${ delUrl }'/>">DELETE</a></td>
            </tr>
      </c:forEach>
   </table>
</body>
</html>