package hello.servlet.basic.response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK);

//        //[header]
//        response.setHeader("Content-type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");//cache 무효화 - 캐싱 절대 불가.
        response.setHeader("Pragma", "no-cache");//http1.0사용하는 브라우저 위한 캐시 무효화
        response.setHeader("my-header", "hello");

        //[header편의 메서드]
        content(response);
        cookie(response);
        redirect(response);

        //[body]
        //print() 는 write()를 호출함. 같은 메소드.
        response.getWriter().println("안녕하세요.");
    }

    private void content(HttpServletResponse response) {
        //content-length는 생략시 자동 생성
        response.setContentType(MediaType.TEXT_PLAIN.toString());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    }

    private void cookie(HttpServletResponse response) {
        //set-cookie: myCookie good; Max-Age= 600;
        //다음 요청시 header에 cookie 담겨 옴.
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);
        response.addCookie(cookie);
    }


    private void redirect(HttpServletResponse response) throws IOException {
        //status 300대 이면 브라우저에서 자동 redirect -> url 바뀜.
//        response.setStatus(HttpServletResponse.SC_FOUND);
//        response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }


}
