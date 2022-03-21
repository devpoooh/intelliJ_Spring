package hello.core.member;

//회원가입, 회원조회
public interface MemberService {
    void join(Member member); //회원가입
    Member findMember(Long memberId); //회원조회
}
