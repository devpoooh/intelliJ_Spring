package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    //MemberRepository 인터페이스에 memoryMemberRepository 생성
    //private final MemberRepository memberRepository = new MemoryMemberRepository(); //DIP 위반 -> 리포지토리가 변경될 때 코드 수정 필요
    private final MemberRepository memberRepository;

    //자동 의존관계 주입
    @Autowired //ac.getBean(MemberRepository.class)
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

    //테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
