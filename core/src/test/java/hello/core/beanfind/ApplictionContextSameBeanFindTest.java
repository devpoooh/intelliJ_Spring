package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ApplictionContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면 중복 오류 발생")
    void findBeanByTypeDuplicate(){
        //err: NoUniqueBeanDefinitionException
       // ac.getBean(MemberRepository.class); //같은 클래스 타입이 2개면 어떤 걸 불러와야 할지 모른다.

        //예외 터트리기
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 빈 이름을 지정하자")
    void findBeanByName(){
        //이름으로 불러오기
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class);

        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);

    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType(){
        //해당 타입을 가지는 모든 빈 부르기
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String s : beansOfType.keySet()) {
            //s:클래스명 value:클래스타입
            System.out.println("s  = " + s+" value = " + beansOfType.get(s));

        }
    }

    @Configuration
    static class SameBeanConfig{

        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }

}
