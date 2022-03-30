package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//bean 등록
@Component("/springmvc/old-controller") //해당 패스로 빈을 등록했으므로 핸들러가 호출된다.
//스프링은 이미 필요한 핸들러 매핑과 핸들러 어댑터를 대부분 구현해 뒀으므로 개발자가 직접 핸들러 매핑과 어댑터를 만드는 일은 거의 없다.
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form"); //논리패스 --> 뷰 리졸버에서 물리패스로 바꾼다
    }
}
