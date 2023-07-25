package hello.springmvc.example.userCURD.repository;
import hello.springmvc.example.userCURD.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
// 스프링 데이터 JPA가 JpaRepository 를 상속받고 있으면
// SpringDataJpaMemberRepository 를 스프링 빈으로 자동 등록해준다 (구현체로 만들어서 등록을 해준다.)
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findById(Long id);

}