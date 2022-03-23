package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //AppConfig로 테스트하기
        // AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService(); //appconfig의 memberService가 결정하게 한다

        //스프링 컨테이너 생성 -> bean 관리
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //memberService 꺼내오기 --> (이름은 클래스명이고, 반환타입은 클래스야)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        //회원가입하기
        Member member = new Member(1L, "meberA", Grade.VIP); //member 객체 생성
        memberService.join(member); //join 수행하기

        Member findMember = memberService.findMember(1L); //id가 1L인 회원 찾기
        System.out.println("member = " + member.getName()); //회원가입 된 멤버변수의 이름 출력
        System.out.println("findMember = " + findMember.getName());//id가 1L로 저장된 회원 이름 출력
    }
}

