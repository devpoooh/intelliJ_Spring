package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되지 않은, 실무에서 ConcurrentHashMap, AtomicLong 사용 고려
 */
//메모리 리포지토리
public class MemberRepository {
    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L; //id로 사용

    //싱글톤으로 만들기
    private static final MemberRepository instance = new MemberRepository();

   public static MemberRepository getInstance(){
        return instance;
    }

    //기본생성자 private로 만들어버리기
    private MemberRepository(){

    }

    //저장하기
    public Member save(Member member){
        //id(pk)증가
        member.setId(++sequence);
        //store(메모리저장소)에 넣기
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(long id){
        return store.get(id);//key로 값꺼내기
    }

    //조회하기
    public List<Member> findAll(){
        return new ArrayList<>(store.values()); //store에 값을 꺼내서 넘겨주기
    }

    //clear
    public void clearStore(){
        store.clear();
    }
}
