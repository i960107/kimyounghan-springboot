package hello.servlet.web.frontcontroller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class CompactDispatcherServlet extends FrameworkServlet {
    private List<HandlerMapping> handlerMappings;
    private List<HandlerAdapter> handlerAdapters;
    private Locale locale;
    private List<ViewResolver> viewResolvers;

    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doDispatch(request, response);
    }

    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpServletRequest processedRequest = request;
        HandlerExecutionChain mappedHandler = null;
        ModelAndView mv = null;
        Exception dispatchException = null;
        //1. 핸들러 조회
        mappedHandler = getHandler(processedRequest);
        if (mappedHandler == null) {
            noHandlerFound(processedRequest, response);
            return;
        }

        //2. 핸들러 어댑터 조회 - 핸들러를 처리할 수 있는 어댑터
        HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());

        //3. 핸들러 어댑터를 통해 핸들러 실행 -> ModelAndView반환.
        mv = ha.handle(request, response, mappedHandler.getHandler());

        processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
    }

    private HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        if (this.handlerMappings != null) {
            for (HandlerMapping mapping : handlerMappings) {
                HandlerExecutionChain handler = mapping.getHandler(request);
                if (handler != null) {
                    return handler;
                }
            }
        }
        return null;
    }

    private void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    private HandlerAdapter getHandlerAdapter(Object handler) throws ServletException {
        if (this.handlerAdapters != null) {
            for (HandlerAdapter adapter : handlerAdapters) {
                if (adapter.supports(handler)) {
                    return adapter;
                }
            }
        }
        throw new ServletException("no adapter for handler " + handler);
    }

    private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,
                                       HandlerExecutionChain mappedHandler, ModelAndView mv,
                                       Exception dispatchException) throws Exception {
        //4. render호출
        render(mv, request, response);
    }

    private void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = mv.getViewName();
        //5. ViewResolver를 통해서 뷰 찾기
        View view = resolveViewName(viewName, mv.getModel(), locale, request);
        //6. 뷰 렌더링
        view.render(mv.getModel(), request, response);
    }

    private View resolveViewName(String viewName, Map<String, Object> model, Locale locale, HttpServletRequest request)
            throws Exception {
        if (viewResolvers != null) {
            for (ViewResolver viewResolver : viewResolvers) {
                View view = viewResolver.resolveViewName(viewName, locale);
                if (view != null) {
                    return view;
                }
            }
        }
        return null;
    }
}
