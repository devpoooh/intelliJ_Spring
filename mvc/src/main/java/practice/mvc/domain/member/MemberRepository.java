package practice.mvc.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//메모리 리포지토리
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); //<id, member>
    private static long sequence = 0L;

    //싱글톤만들기
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    //기본생성자 private로
    private MemberRepository() {
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);

        return member;
    }

    public Member findById(long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }
}
