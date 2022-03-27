package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//front-controller 사용하기
//해당 url에 대해서 jsp접근하는 게 아닌 무조건 front-controller로 와서 요청한 url에 매핑하도록 한다.
//이유) 반복되는 코드(포워드 중복, 경로 중복), 공통 처리 문제 해결
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*") //v1/* 하위는 여기로 오게된다.
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    //url  매핑정보
    //map형태로 key:url value:controllerV1(인터페이스)형태로 클래스 생성
    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());    //회원가입
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());       //저장
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());           //조회
        //경로 끝에 /넣고 안넣고가 차이가 있네...
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //uri는 localhost:8080/다음의 경로를 의미한다.
        //즉, 매핑했던 key의 url과 같다.
        //1. 요청된 uri를 저장한다.
        String requestURI = req.getRequestURI();
        
        //2.controllerMap에서 해당 uri를 가지는 클래스를 반환하여 저장한다.
        ControllerV2 controller = controllerMap.get(requestURI);

        if(controller==null){    //정의되지 않은 url호출되면
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND); //404호출
            return;
        }

        //매핑된 url이 호출이 정상적으로 됐다면
        //3.contoller의 process(interface) --> 선택된 (key)uri의 객체가 가지고 있는 process가 호출된다.
        MyView view = controller.process(req, resp);
        view.render(req,resp);
    }
}
