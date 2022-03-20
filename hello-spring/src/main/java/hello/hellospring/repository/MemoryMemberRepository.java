package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {     //save할 member객체에 대해
        member.setId(++sequence);   //id 1씩 증가해서 설정해주겠다.
        store.put(member.getId(), member); //store에 <id,member객체>

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { //id찾기

        //return store.get(id); null인경우?
        //null인경우를 위해서 optional로 감싸서 넘긴다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {   //name 찾기
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //name이랑 같은 member객체 필터링
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //store에 있는 member들 리턴
        return new ArrayList<>(store.values());
    }

    //객체 비우기
    public void clearStore(){
        store.clear();
    }
}
