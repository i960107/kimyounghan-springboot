<%@ page contentType = "text/html;charset=utf-8" language ="java"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<table>
<thead>
<th>id</th>
<th>name</th>
<th>age</th>
</thead>
<tbody>
<c:forEach var="item" items="${members}">
<tr>
<td> ${item.id} </td>
<td> ${item.username} </td>
<td> ${item.age} </td>
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>
