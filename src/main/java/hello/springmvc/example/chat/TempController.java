package hello.springmvc.example.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 채팅 기능 실행용 임시 컨트롤러
 * 프로필 상세 페이지 매핑: profile.html
 * 모집 글 상세 페이지 매핑: post.html
 */
@Controller
@RequiredArgsConstructor
public class TempController {

    // 프로필 상세 페이지 매핑
    @GetMapping("/p/{pageId}")
    public String profileDetailPage(@PathVariable long pageId, Model model) {
        model.addAttribute("pageId", pageId);
        System.out.println("test");
        return "chat/profile";
    }

    // 모집 글 상세 페이지 매핑
    @GetMapping("/h/{pageId}")
    public String postDetailPage(@PathVariable long pageId, Model model) {
        model.addAttribute("pageId", pageId);
        return "chat/post";
    }

    // 마이페이지 채팅방들 표시 페이지 매핑
    // id는 추후 로그인정보 활용.
    @GetMapping("/my")
    public String myPage(Model model) {
        return "chat/myChatRooms";
    }
}
