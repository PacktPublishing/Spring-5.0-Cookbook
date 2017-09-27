<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Department Form Request</title>
</head>
<body>
  <form method="POST" action="${pageContext.request.contextPath}/dept_form.html">
     Department ID: <input type="text" name="deptId"  /><br/>
     Department Name:<input type="text" name="deptName"  /><br/>
     <input type="submit" value="Add Department" />
  </form>
</body>
</html>