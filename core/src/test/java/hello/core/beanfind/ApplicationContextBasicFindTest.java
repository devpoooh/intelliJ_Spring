package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회하기")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        //memberService가 memberServiceImpl에 의존되니
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름없이 타입으로로 조회기")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class); //타입만 주기

        //memberService가 memberServiceImpl에 의존되니
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회하기")
    void findBeanByName2(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class); //Impl로 조회가능

        //memberService가 memberServiceImpl에 의존되니
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름 조회 실패")
    void findBeanByNameX(){
//        MemberService xxxx = ac.getBean("XXXX", MemberService.class); //XXXX로 등록된 빈이 없다
        //예외 일부러 터지게 하기
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("XXXX", MemberService.class));
    }
}
