package hello.servlet.basic.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request); //기타 정보
    }

    private void printStartLine(HttpServletRequest request) {
        //http://localhost:8080/request-header?username=hi
        System.out.println("---REQUEST_LINE - start ---");
        System.out.println("request.getMethod()" + request.getMethod()); //get
        System.out.println("request.getProtocol()" + request.getProtocol()); //HTTP/1.1
        System.out.println("request.getScheme()" + request.getScheme()); //http
        System.out.println("request.getRequestURL()" + request.getRequestURL());
        //http://localhost:8080/request-header. location
        System.out.println("request.getRequestURI()" + request.getRequestURI());
        //request-test. identifier
        System.out.println("request.getQueryString()" + request.getQueryString()); //username=hi
        System.out.println("request.isSecure()" + request.isSecure()); // false. https 사용 여부
        System.out.println("---REQUEST_LINE - end ---");
        System.out.println();
    }

    private void printHeaders(HttpServletRequest request) {
        System.out.println("---Headers - start ---");
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println(headerName + " : " + request.getHeader(headerName));
//        }

        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName ->
                        System.out.println(headerName + " : " + request.getHeader(headerName)));
        System.out.println("---Headers - end ---");
    }

    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[HOST 편의 조회]");
        System.out.println("request.getServerName()" + request.getServerName());
        System.out.println("request.getServerPort()" + request.getServerPort());
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getServerName()" + request.getLocale()); //가장 우선순위 높은 locale
        System.out.println();

        System.out.println("[cookie 편의 조회]"); //cookie도 header에 담김.
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content 편의 조회]"); //cookie도 header에 담김.
        System.out.println("request.getContentType() : " + request.getContentType());
        System.out.println("request.getContentLength() : " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() : " + request.getCharacterEncoding());
        System.out.println("--- Header 편의 조회 end ---");
    }

    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보] "); // 요청이 온 곳. http message 아닌 내부에서 network connection 맺어진 정보를 알려줌.
        System.out.println("request.getRemoteHost() : " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() : " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() : " + request.getRemotePort());
        System.out.println();

        System.out.println("[Local 정보] "); //나의 서버에 대한 정보
        System.out.println("request.getLocalName() : " + request.getLocalName());
        System.out.println("request.getLocalAddr() : " + request.getLocalAddr());
        System.out.println("request.getLocalPort() : " + request.getLocalPort());
        System.out.println("--- 기타 조회 end ---");
    }


}
