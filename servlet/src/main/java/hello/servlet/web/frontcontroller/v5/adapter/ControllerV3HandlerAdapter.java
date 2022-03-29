package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//v3컨트롤러를 위한 클래스
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    //요청된 핸들러를 지원할 수 있니
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3); //v3의 컨트롤러니? 해당 컨트롤러가 v3의 인스턴스니
    }

    //실제 컨트롤러를 돌리는 역할
    //3. handle(handler)
    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException {

        ControllerV3 controller = (ControllerV3) handler; //오브젝틍형식인 컨트롤러를 v3으로 캐스팅

        Map<String, String> paramMap = createParamMap(req); //파라미터를 map형태로 담기
        //4. 파라미터정보와 함께 핸들러(컨트롤러) 호출
        ModelView mv = controller.process(paramMap); // modelview(논리패스와 모델이 들어있음) 리턴받는다

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        //getParameterNames(): 요청의 모든 파라미터(username,age)를 가져온다. -> paramMap에 다 넣는다.
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
