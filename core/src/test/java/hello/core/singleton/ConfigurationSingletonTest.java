package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    //@Bean memberService -> MemoryMemberRepository()
    //@Bean orderService -> MemoryMemberRepository(), RateDiscountPolicy()
    @Test
    @DisplayName("2번 호출된 MemoryMemberRepository는 다른 객체인가 --> no")
    void configurationTest(){
        //bean 호출
        ApplicationContext ac =new AnnotationConfigApplicationContext(AppConfig.class);

        //getMemberRepository 사용을 위해 선언
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        //멤버리포지토리 호출하기
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        //출력 --> 동일한 객체 인스턴스 임을 확인할 수 있다(싱글톤패턴 유지)
        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        //검증 --> 하나의 객체다
        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);
    }

    @Test
    @DisplayName("@configuration 바이트코드 확인")
    void configurationDeep(){
        ApplicationContext ac =new AnnotationConfigApplicationContext(AppConfig.class);

        //AppConfig 꺼내기
        AppConfig bean = ac.getBean(AppConfig.class);

        //xxxCGLIB이 붙으면서 복잡한 클래스 명이 나온다.
        //AppConfig를 상속받은 복제된?애가 빈에 등록된다
        //애노테이션을 빼면 순수한 AppConfig가 나온다?! --> 그러면서 싱글톤패턴이 깨진다...
       System.out.println("bean. = " + bean.getClass()); //클래스타입 출력
    }
}
