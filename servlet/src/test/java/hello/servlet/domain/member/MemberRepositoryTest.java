package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

//MemberRepository 테스트
class MemberRepositoryTest {

    //멤버리포지토리 가져오기
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach      //테스트끝날때마다 실행
    void afterEach(){
        memberRepository.clearStore(); //초기화함수
    }

    @Test
    void save(){
        //given
        Member member = new Member("hello",20);

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(saveMember.getId()); //id로 멤버찾기
        Assertions.assertThat(findMember).isEqualTo(saveMember); //찾아온 객체와 저장된 객체가 같아야 한다.
    }

    @Test
    void findAll(){
        Member member1 = new Member("member1", 20);
        Member member2  = new Member("member1", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(member1,member2); //해당객체를 포함하고 있냐
    }
}