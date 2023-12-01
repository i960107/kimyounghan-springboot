<%@ page contentType = "text/html;charset=utf-8" language ="java"%>
<%@ page import = "hello.servlet.domain.member.Member" %>
<%@ page import = "hello.servlet.domain.member.MemberRepository" %>
<%
MemberRepository memberRepository = MemberRepository.getInstance();

//request, response는 그냥 쓸 수 있음.
// jsp -> servlet으로 변환되어서 사용됨.
String username = request.getParameter("username");
int age = Integer.parseInt(request.getParameter("age"));

Member member = new Member(username, age);

memberRepository.save(member);
%>

<html>
<body>
<ul>
<li>id = <%=member.getId()%></li>
<li>username = <%=member.getUsername()%></li>
<li>age = <%=member.getAge()%></li>
</ul>
<a href= "/index.html">메인</a>
</body>
</html>
