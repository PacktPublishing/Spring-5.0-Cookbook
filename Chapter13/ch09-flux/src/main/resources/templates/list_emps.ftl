<!DOCTYPE html>
 
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>
<body>
       
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>First Name</th>
          <th>Last Name </th>
         
        </tr>
      </thead>
      <tbody>
        <#list empList as e>
        <tr>
           <td>${e.id?html}</td>
           <td>${e.empid?html}</td>
           <td>${e.firstname?html}</td>
           <td>${e.lastname?html}</td>
        </tr>
        </#list>
      </tbody>
     </table>
    
</body>
</html>