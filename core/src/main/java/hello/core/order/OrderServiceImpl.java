package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    //정보를 꺼내올 리포지토리 필요
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //할인정보(고정금액)를 가져올 객체 필요
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //주문한 id를 갖는 member객체를 가져와
        int discountPirce = discountPolicy.discount(member, itemPrice); //걔가 할인받을 금액을 가져와

        return new Order(memberId, itemName, itemPrice, discountPirce);
    }
}
