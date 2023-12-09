package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class ReqeustParameterController {
    // request에서 직접 parameter꺼내기 -> query string, form-data 모두 가능.
    @RequestMapping("/request-param-v1")
    public void requestParamV1(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {} age = {} ", username, age);
        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge

    ) throws IOException {
        log.info("username = {} age = {} ", memberName, memberAge);
        // controller에서는 ok란 뷰를 찾게 됨. response body에 받으려면 response body 어노테이션 추가 필요.
        return "ok";
    }

    //parameter name과 같으면 이름 생략가능.
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) throws IOException {
        log.info("username = {} age = {} ", username, age);
        return "ok";
    }

    //parameter name과 같으면 어노테이션 자체 생략가능.
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age
    ) throws IOException {
        log.info("username = {} age = {} ", username, age);
        return "ok";
    }

    //required = false인 parameter의 경우 Null값이 들어갈 수 있도록 reference type 변수로 선언.
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age
    ) throws IOException {
        log.info("username = {} age = {} ", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    ) throws IOException {
        log.info("username = {} age = {} ", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap
    ) throws IOException {
        log.info("username = {} age = {} ", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @GetMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) throws IOException {
        log.info("helloData :" + helloData);
        return "ok";
    }

    @ResponseBody
    @GetMapping("/model-attribute-v2")
    public String modelAttributeV2(@ModelAttribute HelloData helloData) throws IOException {
        log.info("helloData :" + helloData);
        return "ok";
    }
}
