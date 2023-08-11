package hello.springmvc.example.v1.controller;


import hello.springmvc.example.v1.domain.Member;
import hello.springmvc.example.v1.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);

        if(session != null) {
            Member loginMember = (Member) session.getAttribute("loginMember");
            model.addAttribute("loginMember", loginMember);
        }

        return "index";
    }
}
