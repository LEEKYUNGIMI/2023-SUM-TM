package hello.springmvc.example.v1.controller;

import hello.springmvc.example.v1.domain.Member;
import hello.springmvc.example.v1.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm() {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form, HttpServletRequest request) {
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        //로그인 실패 시
        if (loginMember == null) {
            //오류 메시지 생성
            log.info("loginMember is null!");
            return "login/loginForm";
        }

        //로그인 성공시
        HttpSession session = request.getSession(true);

        session.setAttribute("loginMember", loginMember);                   // 세션에 로그인 사용자 객체 세팅
        session.setAttribute("loginMemberId", loginMember.getId());         // 세션에 로그인 id 세팅
        session.setAttribute("loginMemberName", loginMember.getName());     // 세션에 로그인 name 세팅

        log.info("login success! : " + loginMember.getLoginId());
        return "redirect:/";

    }


    @PostMapping("/logout")

    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }


    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
