package hello.core.member;

public interface MemberRepository {
    void save(Member member); //회원 저장 기능
    Member findById(Long memberId); //회원의 아이디로 회원 찾기
}
