package hello.springmvc.example.userCURD.repository;

import hello.springmvc.example.userCURD.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);


    void delete(Member member);
    Optional<Member> findById(Long id);
    List<Member> findAll();
}
