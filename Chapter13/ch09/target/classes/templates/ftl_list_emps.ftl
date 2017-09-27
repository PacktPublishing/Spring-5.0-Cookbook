<!DOCTYPE html>
<head>
    <title>Ch08 FreeMarker Reactive View</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
   </head>
   <body>
    <table>
      <thead>
        <tr>
          <th>Employee ID</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Age</th>
        </tr>
      </thead>
      <tbody>
        <#list employees as e>
        <tr>
           <td>${e.empId?html}</td>
           <td>${e.firstName?html}</td>
           <td>${e.lastName?html}</td>
           <td>${e.age?html}</td>
        </tr>
        </#list>
      </tbody>
     </table>
  </body>
</html>