package hello.springmvc.example.v1;

import hello.springmvc.example.v1.domain.GenderType;
import hello.springmvc.example.v1.domain.Member;
import hello.springmvc.example.v1.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor

public class TestDataInit {
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        Member member = new Member();
        member.setLoginId("cksgud0403");
        member.setName("cks");
        member.setPassword("111");
        member.setEmail("cksgud0403@naver.com");
        member.setGender(GenderType.Man);

        List<String> fields = new ArrayList<>();

        fields.add("CODING");
        fields.add("WEB");

        member.setFields(fields);

        memberRepository.save(member);
    }
}