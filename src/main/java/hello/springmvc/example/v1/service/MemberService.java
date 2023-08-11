package hello.springmvc.example.v1.service;

import hello.springmvc.example.v1.domain.Member;
import hello.springmvc.example.v1.repository.MemberRepository;

import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 가입

    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    // 채팅기능에서 회원정보 없는 상대방(김철수)과 대화할 때 임시로 설정을 위해 작성함 - 김인우
    // 고유 id를 매개변수로 받아서 회원을 찾아 이름을 반환하는 메서드
    public String findMemberNameById(Long id) {
        Member member = memberRepository.findById(id);

        if(member != null)
            return member.getName();

        else return "김철수";
    }
}
