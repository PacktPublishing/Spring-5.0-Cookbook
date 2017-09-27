<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<!-- ... -->
<title><spring:message code="dept_title"  /></title>
</head>
<body>
  <c:if test="${ not empty username }">
      Username is <em><c:out value='${ username }'/></em><br/>
      Password is <em><c:out value='${ password }'/></em><br/>
    
  </c:if>
  <br/>
  <form:form modelAttribute="departmentForm" method="POST">
     <spring:message code="dept_id"  /><form:input path="deptId"  /><br/>
     <spring:message code="dept_name"  /><form:input path="name"  /><br/>
     <input type="submit" value="Add Department" />
    
  </form:form>
  
  <br/>
  <em><a href="<c:url value="/deptbanned.html" />">Process to Banned Departments</a></em>
</body>
</html>