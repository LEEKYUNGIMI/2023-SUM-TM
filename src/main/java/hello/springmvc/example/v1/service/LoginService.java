package hello.springmvc.example.v1.service;


import hello.springmvc.example.v1.domain.Member;
import hello.springmvc.example.v1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LoginService {


    private final MemberRepository memberRepository;


    // return null 이면 로그인 실패

    public Member login(String loginId, String password) {
        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);

        Member member = findMemberOptional.get();

        if(member.getPassword().equals(password)) {
            return member;
        }else {
            return null;
        }

    }

}
