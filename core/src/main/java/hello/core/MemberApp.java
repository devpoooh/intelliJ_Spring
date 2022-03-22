package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        //AppConfig로 테스트하기
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService(); //appconfig의 memberService가 결정하게 한다
        //test하기에 메인을 짜는 건 귀찮고 좋은 방법도 아니다...
        //MemberService memberService = new MemberServiceImpl();

        //회원가입하기
        Member member = new Member(1L, "meberA", Grade.VIP); //member 객체 생성
        memberService.join(member); //join 수행하기

        Member findMember = memberService.findMember(1L); //id가 1L인 회원 찾기
        System.out.println("member = " + member.getName()); //회원가입 된 멤버변수의 이름 출력
        System.out.println("findMember = " + findMember.getName());//id가 1L로 저장된 회원 이름 출력
    }
}

