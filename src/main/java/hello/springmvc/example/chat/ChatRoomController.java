package hello.springmvc.example.chat;import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class ChatRoomController {

    private final ChatService chatService;
    public static final Logger logger = LogManager.getLogger(ChatRoomController.class);

    // 전체 채팅 리스트 페이지 매핑
    @GetMapping("/allroom")
    public String rooms(Model model) {
        return "chat/showAllChatRoom";
    }

    // 모든 채팅방 목록 반환 showAllChatRooms.html에서 사용됨
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoomDTO> allRoom() {
        return chatService.findAllRoom();
    }


    // 마이페이지에서 요청 - 사용자가 참여중인 채팅방들 userId로 조회 후 반환.
    @GetMapping("/rooms/{userId}")
    @ResponseBody
    public List<ChatRoomDTO> myRoom(@PathVariable String userId) {
        return chatService.findByUserId(userId);
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoomDTO createRoom(@RequestParam String name, @RequestParam String subMembers) {
        logger.info("ROOM CRATED!");
        logger.info("name: " + name + "subMembers: " + subMembers);
        return chatService.createRoom(name, subMembers);
    }

    // roomId로 채팅방 조회 후 반환 - startRoom.js - searchRoom()에서 사용
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoomDTO findRoom(@PathVariable String roomId) {
        logger.info("searchRoom id: " + roomId);
        if(chatService.findByRoomId(roomId) == null) logger.info("findroom is null");
        return chatService.findByRoomId(roomId);
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String enterRoom(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "chat/chattingRoom";
    }
}
