package hello.springmvc.example.v1.controller;


import hello.springmvc.example.v1.domain.Member;
import hello.springmvc.example.v1.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;


    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }

    //회원 가입 폼
    @GetMapping("/signup")
    public String createForm() {
        return "members/createMemberForm";
    }

    //회원 가입
    @PostMapping
    public String create(MemberForm memberForm){

        Member member = new Member();

        log.info("memberForm = {}", memberForm.toString());

        member.setName(memberForm.getName());
        member.setLoginId(memberForm.getLoginId());
        member.setPassword(memberForm.getPassword());
        member.setEmail(memberForm.getEmail());
        member.setGender(memberForm.getGender());
        member.setFields(memberForm.getFields());

        log.info("member = {}", member.toString());

        memberService.join(member);

        return "redirect:/";
    }

}
