package hello.springmvc.example.v1.repository;

import hello.springmvc.example.v1.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);


    void delete(Member member);
    Optional<Member> findById(Long id);
    List<Member> findAll();
}
