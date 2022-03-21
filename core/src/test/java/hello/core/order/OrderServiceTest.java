package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    //멤버서비스:회원가입, 회원조회
    MemberService memberService =  new MemberServiceImpl();
    //주문서비스: 주문하기(할인금액차감)
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        //given
        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);

        //when
        memberService.join(member); //회원가입
        Order order = orderService.createOrder(memberId, "밥", 10000);

        //then
        //할인받은 금액이 1000원인가?
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
