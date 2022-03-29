package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    //요청된 어댑터가 컨트롤러를 처리할 수 있는지 파악해준다.
    boolean supports(Object handler);

    //어댑터가 실제 컨트롤러를 호출하고 결과를 modelview로 반환한다.
    //전에는 프론트 컨트롤러가 실제 컨트롤러를 호출했지만 이제는 어댑터를 통해서 호출해야한다.
    ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException;
}
