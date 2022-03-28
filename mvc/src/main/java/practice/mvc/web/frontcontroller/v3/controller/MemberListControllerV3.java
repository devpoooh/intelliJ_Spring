package practice.mvc.web.frontcontroller.v3.controller;


import practice.mvc.domain.member.Member;
import practice.mvc.domain.member.MemberRepository;
import practice.mvc.web.ModelView;
import practice.mvc.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        List<Member> members = memberRepository.findAll();//로직수행
        ModelView mv = new ModelView("members"); //members라는 이름(패스)로 객체 생성
        mv.getModel().put("members",members);   //key,value로 값 추가하기(전체 회원)

        return mv;
    }
}
