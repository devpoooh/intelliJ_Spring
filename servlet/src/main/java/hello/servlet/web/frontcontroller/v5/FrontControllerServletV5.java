package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import org.springframework.web.servlet.ViewResolver;

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

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //여러 종류의 컨트롤러를 위해 object타입으로 만든다
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>(); //많은 핸들러 리스트

    //기본생성자
    public FrontControllerServletV5() {
        //컨트롤러 v3을 위한 매핑리스트
        initHandlerMappingMap(); //<uri, controllerV3>컨트롤러 리스트

        initHandlerAdapters(); //어댑터 리스트 만들기
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter()); //v3컨트롤러를 리스트에 넣어준다.
        handlerAdapters.add(new ControllerV4HandlerAdapter()); //v4컨트롤러를 리스트에 넣어준다.
    }

    //핸들러 등록
    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());    //회원가입
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());       //저장
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());           //조회
        //v4추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());    //회원가입
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());       //저장
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());           //조회
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1. 핸들러 매핑 조회: 핸들러(어떤종류인지 모르는 컨트롤러)를 찾아 와라
        Object handler = getHandler(req); //컨트롤러 리턴받음

        //컨트롤러가 없으면
        if(handler == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            return; //종료
        }

        //2. 핸들러 어댑터 조회: 컨트롤러를 가지고 등록된 어댑터에서 찾아라
        MyHandlerAdapter adapter = getHandlerAdapter(handler); //어댑터 리턴

        //3. handle(handler)
        //3-1 핸들러 어댑터를 통해 컨트롤러를 호출한다.
        ModelView mv = adapter.handle(req, resp, handler); //req,resp,handler(컨트롤러)를 넘겨준다.

        String viewName = mv.getViewName(); //논리패스 꺼내기

        //6.viewResolver 호출
        MyView view = viewResolver(viewName); //논리패스 -> 물리패스
        //7. MyView로 반환

        //8. render(model) 호출출
        view.render(mv.getModel(),req,resp);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        //어댑터리스트에서 맞는 어댑터를 찾는다
        for (MyHandlerAdapter adapter : handlerAdapters) { //현재 v3만 들어있음
            if(adapter.supports(handler)){  //어댑터를 지원하면
                return adapter; //v3의 어댑터를 리턴
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler = "+handler);
    }

    //1. 핸들러 매핑 조회 함수
    private Object getHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI(); //1-1 들어온 요청의 uri를 저장하고
        return handlerMappingMap.get(requestURI); //1-2 uri로 핸들러 매핑정보에서 찾는다(핸들러 조회)
    }

    //6.viewResolver 호출 함수
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp"); //물리패스로 만들어서 반환
    }
}
