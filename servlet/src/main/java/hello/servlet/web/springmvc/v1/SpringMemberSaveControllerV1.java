package hello.servlet.web.springmvc.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//조회하기
@Controller
public class SpringMemberSaveControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest req, HttpServletResponse resp){
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
}
