package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join(){
        //given: 이런 환경에서
        Member member = new Member(1L,"springA",Grade.VIP); //이와같은 객체가 있을 때

        //when: 어떻게하면
        memberService.join(member); //회원가입을 하고
        Member findMember = memberService.findMember(1L);//id가 1L인 객체를 가져온다

        //then: 결과는 이래야 한다.
        Assertions.assertThat(member).isEqualTo(findMember); //성공 시 같은 객체임을 확인한 것!
    }
}
