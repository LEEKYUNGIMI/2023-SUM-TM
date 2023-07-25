package hello.springmvc.example.userCURD.service;

import hello.springmvc.example.userCURD.domain.Member;
import hello.springmvc.example.userCURD.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;


@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    //회원 목록 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }


    //회원 상세 조회
    public Member findOne(Long memberId){

    return memberRepository.findById(memberId)
            .orElseThrow(() -> new NoSuchElementException("Member not found with ID: " + memberId));

    }

    //회원 가입

    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }


    //회원 수정
    public void modify(Member member) {
        memberRepository.save(member);
    }



    //회원 탈퇴
    public void withdraw(Member member) {
        memberRepository.delete(member);
    }

}
