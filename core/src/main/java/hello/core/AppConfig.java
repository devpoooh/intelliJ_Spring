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

//리팰토링을 함으로써 구현 그림(관계)이 한 눈에 파악이 가능하다
public class AppConfig {

    //생성자 주입
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    //리포지토리는 뭐로 할건지
    private MemberRepository memberRepository() {
        //리포지토리 형태가 달라지면 해당 코드만 변경해주면 된다.
        return new MemoryMemberRepository(); //메모리리포지토리를 쓰겠다
    }

    //주문서비스는 뭐로 할건지
    public OrderService orderService(){
        //멤버리포지토리가 설정된 저장소와 할인정책이 선택한 할인정보를 사용하겠다
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    //할인정책 뭐로 할건지
    public DiscountPolicy discountPolicy(){
        //할인정책이 바뀌면 해당 코드만 변경해주면 된다.
//        return new FixDiscountPolicy(); //1000원할인을 하겠다.
        return new RateDiscountPolicy(); //정률 할인을 하겠다
    }

}
