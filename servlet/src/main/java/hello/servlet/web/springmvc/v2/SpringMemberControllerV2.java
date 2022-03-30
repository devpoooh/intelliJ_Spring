package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form") //이 url로 오면 메서드를 실행한다.
    public ModelAndView newForm(){
        System.out.println("SpringMemberFormControllerV1.process");
        return new ModelAndView("new-form"); //경로 지정하기
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest req, HttpServletResponse resp){
        //파라미터가져오기
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        //객체 생성 후 디비 넘겨주기
        Member member = new Member(username,age);
        memberRepository.save(member);

        //모델뷰 넘기기
        ModelAndView mv = new ModelAndView("save-result");
        //mv.getModel().put("member",member);
        mv.addObject("member",member);

        return mv;
    }

    @RequestMapping
    public ModelAndView members(){

        //전체 멤버 가져오기
        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members",members);

        return mv;
    }
}
