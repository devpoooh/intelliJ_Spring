package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //MemberRepository 인터페이스에 memoryMemberRepository 생성
    //private final MemberRepository memberRepository = new MemoryMemberRepository(); //DIP 위반 -> 리포지토리가 변경될 때 코드 수정 필요
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member); //리포지토리의 save 호출
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId); //리포지토리의 findById 호출
    }
}
