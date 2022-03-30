package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

 //컨트롤러야! 빈 등록해줘 -> 자동으로 빈 등록, 컴포넌트 스캔의 대상이 된다.
//mvc에서 애노테이션 기반 컨트롤러로 인식한다.
//@component @RequestMapping //클래스에도 RequestMapping을 해줄 수 있다.
//or
@Controller //내부에 @Component를 포함한다
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form") //이 url로 오면 메서드를 실행한다.
    public ModelAndView process(){
        System.out.println("SpringMemberFormControllerV1.process");
        return new ModelAndView("new-form"); //경로 지정하기
    }
}
