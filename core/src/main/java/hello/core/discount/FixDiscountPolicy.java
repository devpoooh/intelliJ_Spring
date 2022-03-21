package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

//1000원 할인(고정 금액 할인)
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; //1000원 할인

    @Override
    public int discount(Member member, int price) {

        //회원 등급이 VIP면 1000원 할인
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else{
            //VIP가 아니면
            return 0; //0원
        }
    }}
