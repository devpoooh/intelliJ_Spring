package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //조회할 데이터 가져오기
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        //객체에 저장하고
        Member member = new Member(username,age);
        //리포지토리에 저장하기
        memberRepository.save(member);

        //Model에 데이터 보관
        req.setAttribute("member",member); //req 내부 저장소에 저장해준다.

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
