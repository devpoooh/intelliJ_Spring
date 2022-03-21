package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static final Map<Long, Member> store = new HashMap<>(); //저장소

    @Override
    public void save(Member member) {
        store.put(member.getId(),member); //회원 id를 키로 member 객체 넣기
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId); //들어온 memberId로 Member객체 꺼내기
    }
}
