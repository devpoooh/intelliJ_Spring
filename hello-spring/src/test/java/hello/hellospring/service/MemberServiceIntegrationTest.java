package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

//스프링컨테이너와 테스트함께 실행
@SpringBootTest
@Transactional //디비테스트 후 알아서 롤백해준다
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    //객체를 생성했기 때문에 사실상 다른 리포지토리로 테스트를 하고 있는 것이다.
    @Autowired
    MemberRepository memberRepository;

    @Test
        //회원가입에 대한 테스트 로직
    void 회원가입() {
        //given: 주어진 상황
        Member member = new Member(); //객체 생성
        member.setName("hello");

        //when: 실행했을 때
        Long saveId = memberService.join(member); // join하면

        //then: 결과가 나와야한다.(검증부분)
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        /* try-catch로 잡기
        try{
            memberService.join(member2);
            fail(); //잘모르겟음
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        //assertThrows(발생할 예외 클래스의 class타입, 실행할 구문) --> 구문 실행시 첫번째 인자와 같은지 검증한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}