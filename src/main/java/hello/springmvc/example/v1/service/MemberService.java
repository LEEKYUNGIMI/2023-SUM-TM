package hello.springmvc.example.v1.service;

import hello.springmvc.example.v1.domain.Member;
import hello.springmvc.example.v1.repository.MemberRepository;


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

}
