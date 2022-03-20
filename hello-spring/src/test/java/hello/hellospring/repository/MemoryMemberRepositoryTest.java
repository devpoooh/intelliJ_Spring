package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

// TTD: 테스트케이스를 먼저 만들고 개발하는 것
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    private Member member1;
    private Member member2;

    //테스트를 한번에 실행하면 오류가 발생
    //why? 순서는 랜덤하게 하는데 이미 생성된 객체를 사용하기 때문
    //AfterEach를 사용해서 함수하나 끝날때마다 실행시켜 클리어해주는 작업을 해주자.
    @AfterEach
    public void afterEach(){
        repository.clearStore(); //저장된거 지우기
    }

    @Test
    public void save() {
        Member member = new Member(); //테스트 객체 생성
        member.setName("Spring"); //이름 삽입

        repository.save(member); //member저장

        Member result = repository.findById(member.getId()).get(); //id로 찾은 값 꺼내기

        //junit에 주피터가 제공하는 값 비교
        //Assertions.assertEquals(member, result); //(기대값, 결과값) 같으면 정상실행

        //assertj 방법 : member라는 값이 result랑 같니
        Assertions.assertThat(member).isEqualTo(result); //import 가능
    }

    @Test
    public void findByName(){
        //테스트할 객체 생성하여 save
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        //Spring1을 찾아서 뱉어라. -> result에 저장
        Member result = repository.findByName("Spring1").get();

        Assertions.assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findAll(){
        //테스트할 객체 생성하여 save
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}

