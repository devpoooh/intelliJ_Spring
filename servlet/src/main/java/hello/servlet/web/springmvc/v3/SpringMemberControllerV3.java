package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
//modelandView안만들고 리턴 가능
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //이 url로 오면 메서드를 실행한다.
    //@GetMapping('/new-form)
    @RequestMapping(value = "/new-form", method = RequestMethod.GET) //get일 경우에만
    public String newForm(){
        return "new-form"; //string으로 넘겨주면 알아서 modelandView인 줄 알고 매핑해준다.
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(//HttpServletRequest req, HttpServletResponse resp //http가 아닌 파라미터도 받을 수 있다.
                             @RequestParam("username") String username,
                             @RequestParam("age") int age,
                             Model model){


        //객체 생성 후 디비 넘겨주기
        Member member = new Member(username,age);
        memberRepository.save(member);

        //모델뷰 넘기기
//        ModelAndView mv = new ModelAndView("save-result");
//        mv.addObject("member",member);
        model.addAttribute("member",member);

        return "save-result";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String members(Model model){

        //전체 멤버 가져오기
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members",members);

        return "members";
    }
}
