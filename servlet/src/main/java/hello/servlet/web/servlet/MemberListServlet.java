package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        List<Member> members = memberRepository.findAll();
        PrintWriter writer = response.getWriter();
        writer.write("<!DOCTYPE html>\n"
                + "<html>\n"
                + "<body>\n"
                + "<table>\n"
                + "<thead>\n"
                + "<th>id</th>"
                + "<th>name</th>"
                + "<th>age</th>"
                + "</thead>");
        for (Member member : members) {
            writer.write("<tr>\n");
            writer.write("<td>" + member.getId() + "</td>\n"
                    + "<td>" + member.getUsername() + "</td>\n"
                    + "<td>" + member.getAge() + "</td>\n"
            );
            writer.write("</tr>\n");
        }

        writer.write("</table>\n"
                + "</body>\n"
                + "</html>\n");
    }
}
