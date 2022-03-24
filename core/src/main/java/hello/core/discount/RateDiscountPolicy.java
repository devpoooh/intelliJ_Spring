package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    //정률로 할인 금액
    public int discount(Member member, int price) {
        //VIP면
        if(member.getGrade() == Grade.VIP){
            return price*discountPercent/100; //가격에 10% 할인
        }else{ //BASIC이면
            return 0; //할인금액 없음
        }
    }
}
