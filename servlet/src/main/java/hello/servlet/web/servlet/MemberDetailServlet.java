package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "memberDetailServlet", urlPatterns = "/servlet/members/*")
public class MemberDetailServlet extends HttpServlet {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long memberId = Long.parseLong(request.getRequestURI().split("/")[3]);
        Member member = memberRepository.findById(memberId);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        writer.write("<!DOCTYPE html>\n"
                + "<html>\n"
                + "<body>\n"
                + "<ul>\n"
                + "<li>id = " + member.getId() + "</li>\n"
                + "<li>username = " + member.getUsername() + "</li>\n"
                + "<li>age = " + member.getAge() + "</li>\n"
                + "</ul>\n"
                + "<a href= \"/index.html\">메인</a>\n"
                + "</body>\n"
                + "</html>\n");
    }
}
