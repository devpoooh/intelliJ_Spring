package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    /*
    DIP위반방법
    //정보를 꺼내올 리포지토리 필요
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //할인정보(고정금액)를 가져올 객체 필요
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //할인정보(정률금액)를 가져올 객체
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    */

    //인터페이스에만 의존하도록 코드 변경
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자 주입으로 주문서비스에서가 아닌 appConfig에서 결정할 수 있도록 설계한다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //주문한 id를 갖는 member객체를 가져와
        int discountPrice = discountPolicy.discount(member, itemPrice); //걔가 할인받을 금액을 가져와

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
