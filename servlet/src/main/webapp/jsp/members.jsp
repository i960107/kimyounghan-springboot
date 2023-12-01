<%@ page contentType = "text/html;charset=utf-8" language ="java"%>
<%@ page import = "hello.servlet.domain.member.Member" %>
<%@ page import = "hello.servlet.domain.member.MemberRepository" %>
<%@ page import = "java.util.List" %>
<%
MemberRepository memberRepository = MemberRepository.getInstance();

List<Member> members = memberRepository.findAll();
%>

<html>
<body>
<table>
<thead>
<th>id</th>
<th>name</th>
<th>age</th>
</thead>
<%
for(Member member: members){
    %>
    <tr>
    <td><%=member.getId()%></td>
    <td><%=member.getUsername()%></td>
    <td><%=member.getAge()%></td>
    </tr>
    <%
}
%>
</table>
</body>
</html>
