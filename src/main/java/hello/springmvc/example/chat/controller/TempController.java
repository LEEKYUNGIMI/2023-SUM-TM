package hello.springmvc.example.chat.controller;
;
import hello.springmvc.example.chat.ChatService;
import hello.springmvc.example.v1.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 채팅 기능 실행용 임시 컨트롤러
 * 프로필 상세 페이지 매핑: profile.html
 * 모집 글 상세 페이지 매핑: post.html
 */
@Controller
@RequiredArgsConstructor
public class TempController {

    private final ChatService chatService;

    // 프로필 상세 페이지 매핑
    @GetMapping("/p/{pageId}")
    public String profileDetailPage(@PathVariable Long pageId, Model model, HttpServletRequest request) {
        // 채팅상대 id, 이름 세팅
        chatService.setChatTargetInfo(pageId, model);

        // 세션에서 로그인 id 정보 가져와서 세팅
        chatService.setLoginInfo(model, request);

        return "chat/profile";
    }

    // 모집 글 상세 페이지 매핑
    @GetMapping("/h/{pageId}")
    public String postDetailPage(@PathVariable Long pageId, Model model, HttpServletRequest request) {
        // 채팅상대 id, 이름 세팅
        chatService.setChatTargetInfo(pageId, model);

        // 세션에서 로그인 id 정보 가져와서 세팅
        chatService.setLoginInfo(model, request);

        return "chat/post";
    }

    // 마이페이지 채팅방들 표시 페이지 매핑
    // 임시로 아이디가 1인 사용자의 마이페이지로 임시설정
    @GetMapping("/my")
    public String myPage(Model model, HttpServletRequest request) {
        chatService.setLoginInfo(model, request);
        return "chat/myChatRooms";
    }
}
