package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//v4 코드흐름
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    //1-1 url매핑정보를 저장하기 위한 해시맵이다
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    //1-2 매핑정보를 담는다.
    public FrontControllerServletV4() { //기본생성자로 클래스가 호출되면서 미리 만들어둔다
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());    //회원가입
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());       //저장
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());           //조회
    }

    //1-3 service함수 실행
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //2. 요청받은 매핑정보찾기
        String requestURI = req.getRequestURI(); //2-1 요청받은 uri(로컬서버를 뗀 url)을 requestURI에 저장한다.
        
        //2-2 매핑정보가 있는 곳에서 요청받는 uri(key)에 대한 value(컨트롤러)를 가져와 클래스를 생성한다.
        // ex) COntrollerV3 controller = new MemberFormControllerV3(); --> uri가 new-form이었다면
        ControllerV4 controller = controllerMap.get(requestURI);

        //에러처리
        if(controller==null){    //정의되지 않은 url호출되면
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND); //404호출
            return;
        }

        //요청된 파라미터 정보들을 map으로 저장한다.
        Map<String, String> paramMap = createParamMap(req); //요청 파라미터(uesrname, age) 저장ㄷ

        //3. 컨트롤러 호출: 파라미터와 모델을 넘겨준다.
        Map<String,Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);//4. 경로로 반환 3-1 컨트롤러실행

        //5. viewResolver 호출: 논리패스를 물리패스로 변환해준다.
        MyView view = viewResolver(viewName);// "/WEB-INF/views/new-form.jsp가 된다.
        view.render(model,req,resp); //디스패처 렌더링 처리 모델과 http요청을 같이 보내줘야 한다.
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        //getParameterNames(): 요청의 모든 파라미터(username,age)를 가져온다. -> paramMap에 다 넣는다.
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
