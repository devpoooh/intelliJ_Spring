package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        //해당 방법의 경우 클라이언트마다 각각의 객체를 생성하면서 메모리 낭비가 심하다.
        //해결방안: 해당 객체는 딱 1개만 생성되고 공유하여 설계한다. ==> 싱글톤 패턴
        AppConfig appConfig = new AppConfig();

        //1. 조회: 호출할 때마다 객체 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        //객체마다 참조값이 다른 것을 확인할 수 있다
        System.out.println("memberService1 = " + memberService1 + "memberService2 = " + memberService2);

        //다른 참조값을 갖는지 검증 --> 통과
        Assertions.assertThat(memberService1).isNotEqualTo(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴으로 멤버서비스 객체 확인")
    //스프링은 기본적으로 싱글톤을 기반으로 한다.
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance(); //싱글톤 객체의 인스턴스를 가져온다.
        SingletonService singletonService2 = SingletonService.getInstance();

        //동일한 참조값을 갖는 객체를 확인할 수 있다
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        //검증
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        //isSameAs ==
        //equeal은 값 비교느낌
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    //스프링은 기본적으로 싱글톤을 기반으로 한다.
    void springContainer(){

        //bean등록 클래스 호출
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //객체 호출
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        //객체마다 참조값이 다른 것을 확인할 수 있다
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //같은 참조값을 갖는 것을 확인할 수 있다 --> 통과
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
