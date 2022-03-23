package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//리팰토링을 함으로써 구현 그림(관계)이 한 눈에 파악이 가능하다
@Configuration  //설정정보를 담겠다
public class AppConfig {
// * 빈이름은 중복해서는 안된다.

    //@Bean memberService -> MemoryMemberRepository()
    //@Bean orderService -> MemoryMemberRepository(), RateDiscountPolicy()

    //*call 호출 확인
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.discountPolicy

    //생성자 주입
    @Bean      //스프링 컨테이너에 저장한다
    public MemberService memberService() { //memberService로 명명
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); //리턴되는 객체를 이름에 등록한다
    }

    //리포지토리는 뭐로 할건지
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        //리포지토리 형태가 달라지면 해당 코드만 변경해주면 된다.
        return new MemoryMemberRepository(); //메모리리포지토리를 쓰겠다
    }

    //주문서비스는 뭐로 할건지
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        //멤버리포지토리가 설정된 저장소와 할인정책이 선택한 할인정보를 사용하겠다
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    //할인정책 뭐로 할건지
    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("call AppConfig.discountPolicy");
        //할인정책이 바뀌면 해당 코드만 변경해주면 된다.
        // return new FixDiscountPolicy(); //1000원할인을 하겠다.
        return new RateDiscountPolicy(); //정률 할인을 하겠다
    }

}
