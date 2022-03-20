package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원 저장소에 저장
    //null로 반환되는 경우 감싸서 처리하겠다.
    Optional<Member> findById(Long id); //id찾기
    Optional<Member> findByName(String name); //name찾기
    List<Member> findAll(); //모든 리스트 조회
}
