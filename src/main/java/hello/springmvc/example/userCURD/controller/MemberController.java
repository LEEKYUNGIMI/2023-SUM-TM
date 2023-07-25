package hello.springmvc.example.userCURD.controller;


import hello.springmvc.example.userCURD.domain.Member;
import hello.springmvc.example.userCURD.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;


    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }

    //회원 목록 조회
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }


    //회원 상세 조회
    @GetMapping("/{memberId}")

    public String member(@PathVariable long memberId, Model model) {
        Member member = memberService.findOne(memberId);
        model.addAttribute("member", member);
        return "members/member";
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

        member.setName(memberForm.getName());
        member.setGender(memberForm.getGender());
        member.setField(memberForm.getField());
        member.setAge(memberForm.getAge());

        memberService.join(member);

        return "redirect:/members";
    }



    //회원 수정 폼
    @GetMapping("/{memberId}/edit")
    public String updateFrom(@PathVariable long memberId, Model model) {
        Member member = memberService.findOne(memberId);
        model.addAttribute("member", member);
        return "members/updateMemberForm";
    }


    //회원 수정
    @PutMapping("/{memberId}")
    public String update(@PathVariable long memberId, MemberForm memberForm, RedirectAttributes redirectAttributes) {

        Member member = memberService.findOne(memberId);

        member.setName(memberForm.getName());
        member.setGender(memberForm.getGender());
        member.setField(memberForm.getField());
        member.setAge(memberForm.getAge());

        memberService.modify(member);

        redirectAttributes.addAttribute("memberId", memberId);
        return "redirect:/members/{memberId}";
    }



    //회원 삭제
    @DeleteMapping("{memberId}")
    public String delete(@PathVariable long memberId) {

        Member member = memberService.findOne(memberId);

        memberService.withdraw(member);

        return "redirect:/members";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

}
