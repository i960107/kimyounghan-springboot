<%@ page contentType = "text/html;charset=utf-8" language ="java"%>
<html>
<body>
<ul>
<%-- <li>id = <%=((Member)request.getAttribute("member"))getId()%></li> --%>
<li>id = ${member.id}</li>
<li>username = ${member.username}</li>
<li>age = ${member.age}</li>
</ul>
<a href= "/index.html">메인</a>
</body>
</html>
