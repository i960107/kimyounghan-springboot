package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap) throws ServletException, IOException;
}
