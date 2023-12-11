package hello.springmvc.basic.response;

import java.io.IOException;
import java.io.Writer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    @GetMapping("/response-view-v1")
    public ModelAndView responseViewV1(Writer writer) throws IOException {
        writer.write("writer test");
        ModelAndView mav = new ModelAndView("hello")
                .addObject("data", "hello!");
        return mav;
    }

    @GetMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

    //권장되지 않음. return void시 request url을 논리적 뷰 이름으로 지정.
    @GetMapping("/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}
