package hello.servlet.basic.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1.파라미터 전송 기능 http://localhost:8080/request-param?name=kim&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[파라미터 조회] - start");
        request.getParameterNames().asIterator()
                .forEachRemaining(parameterName ->
                        System.out.println(parameterName + " : " + request.getParameter(parameterName)));
        System.out.println("[파라미터 조회] - end");
        //만약 같은 이름의 파라미터가 존재한다면? 
        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("name = " + name);
        }
    }
}
