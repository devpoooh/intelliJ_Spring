package hello.hellospring.repository;

import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//직접 스프링 빈 등록하기
@Configuration //스프링이 올라올 때 빈이 있으면 올려줘라.
public class SpringConfig {

    @Bean   //스프링 빈을 등록하겠다.
    public MemberService memberService(){
        //서비스와 리포지토리 연결
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
