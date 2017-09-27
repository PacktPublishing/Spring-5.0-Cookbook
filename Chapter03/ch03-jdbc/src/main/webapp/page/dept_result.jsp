<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="dept_title"  /></title>
</head>
<body>
   <h1><spring:message code="dept_title"  /></h1>
   <table>
      <c:forEach var="dept" items="${ departments }">
            <tr>
                <td>${ dept.deptId }</td>
                <td>${ dept.name }</td>
            </tr>
      </c:forEach>
   </table>
  
</body>
</html>