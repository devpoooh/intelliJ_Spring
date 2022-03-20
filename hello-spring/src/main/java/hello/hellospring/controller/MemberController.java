package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//컨트롤러 어노테이션을 붙이는 순간 스프링 컨테이너가 알아서 MemberController라고 하는 객체를 생성한다.
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") //요청페이지가 /members/new면 매핑해줘라
    public String createForm(){
        return "members/createMemberForm"; //members/createMemberForm.html을 보여주자
    }

    //post방식으로 넘어오면
    @PostMapping("/members/new")
    public String create(MemberForm form){ //MemberForm에 name을 넣어줌
        Member member = new Member(); //Member객체 생성
        member.setName(form.getName()); //form에서 꺼낸 name을 Member객체에 넣기

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers(); //모든 멤버 리스트를 담는다.
        model.addAttribute("members",members);

        return "members/memberList";
    }
}
