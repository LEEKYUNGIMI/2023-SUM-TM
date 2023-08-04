package hello.springmvc;

import hello.springmvc.example.v1.repository.*;
import hello.springmvc.example.v1.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() { //memberService 빈 등록
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() { //memberRepository 빈 등록

        return new MemoryMemberRepository();
    }
}