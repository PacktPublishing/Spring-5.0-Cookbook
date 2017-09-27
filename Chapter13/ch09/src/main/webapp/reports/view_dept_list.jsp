<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="dept_title"  /></title>
</head>
<body>
   <h1><spring:message code="dept_list"  /></h1>
   <table border="1">
      <c:forEach var="dept" items="${ departments }">
            <tr>
                <td>${ dept.deptId }</td>
                <td>${ dept.name }</td>
                <c:url var="delUrl" value="/react/deldept.html/${dept.deptId}" />
                <td><a href="<c:out value='${ delUrl }'/>">DELETE</a></td>
                <c:url var="updateUrl" value="/react/updatedept.html/${dept.id}" />
                <td><a href="<c:out value='${ updateUrl }'/>">UPDATE</a></td>
            </tr>
      </c:forEach>
   </table>
  <br>

 <br/>
 <a href="/ch09/react/menu.html">Menu</a><br/>
</body>
</html>