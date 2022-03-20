package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     **/
    public Long join(Member member) {
        /*
        // 같은 이름이 있는 경우 회원가입 X
        Optional<Member> result = memberRepository.findByName(member.getName());
        //null 체크 if문: 값이 있으면 함수 호출
        //Optional을 사용함으로써 null값을 감싸 ifPresent와 같은 것을 사용할 수 있음
        result.ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        //result로 꺼내지 않고 바로 함수 처리가 더 깔끔하다.
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member); //통과하면 조회

        return member.getId();
    }

    /**
     * 전체 회원 조회
     **/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

}
