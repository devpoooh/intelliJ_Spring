package practice.mvc.web.frontcontroller;

import practice.mvc.web.ModelView;
import practice.mvc.web.MyView;
import practice.mvc.web.frontcontroller.adapter.ControllerV3HandlerAdapter;
import practice.mvc.web.frontcontroller.v3.controller.MemberFormControllerV3;
import practice.mvc.web.frontcontroller.v3.controller.MemberListControllerV3;
import practice.mvc.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServlet" , urlPatterns = "/front-controller/v5/*")
public class FrontControllerServlet extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapter = new ArrayList<>();

    public FrontControllerServlet() {
        initHandlerMappingMap(); //컨트롤러 리스트 만들기

        initHandlerAdapters(); //어댑터 리스트 만들기
    }

    private void initHandlerAdapters() {
        handlerAdapter.add(new ControllerV3HandlerAdapter()); //어댑터 등록
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1. 핸들러 매핑 조회
        Object handler = getHandler(req);

        if(handler ==null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //2. 핸들러 어댑터 조회
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        //3. 핸들러 어댑터를 통해 컨트롤러 호출
        ModelView mv = adapter.handle(req, resp, handler);

        //논리패스 저장
        String viewName = mv.getViewName();

        //6. 뷰리졸버 호출
        MyView view = ViewResolver(viewName);
        //7. MyView로 반환

        //8. render(model) 호출
        view.render(mv.getModel(),req,resp);

    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapter) {
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("hendler adapter를 찾을 수 없습니다. handler = "+handler);
    }

    private Object getHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        return handlerMappingMap.get(requestURI);
    }

    private MyView ViewResolver(String viewName){
        return new MyView("/WEB-INF/views/"+viewName+".jsp");
    }
}
