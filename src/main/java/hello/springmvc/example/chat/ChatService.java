package hello.springmvc.example.chat;

import hello.springmvc.example.chat.domain.ChatMessageDTO;
import hello.springmvc.example.chat.domain.ChatRoomDTO;
import hello.springmvc.example.v1.service.MemberService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatService {

    private final MemberService memberService;      // 멤버 정보 검색을 위해 사용

    private Map<String, ChatRoomDTO> chatRooms;
    private Map<String, ChatMessageDTO> messages;


    // 로그
    public static final Logger logger = LogManager.getLogger(ChatService.class);

    //의존관게 주입완료되면 실행되는 코드
    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
        messages = new LinkedHashMap<>();
    }

    //채팅방 불러오기
    public List<ChatRoomDTO> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
        List<ChatRoomDTO> result = new ArrayList<>(chatRooms.values());
        Collections.reverse(result);
        return result;
    }

    // 사용자 id로 채팅방 불러오기
    // 전체 채팅방에서 userId가 포함된 roomId를 검색해서 찾는다.
    // db 사용 시 select 조회하도록 변경 필요.
    public List<ChatRoomDTO> findByUserId(String userId) {
        List<ChatRoomDTO> temp = findAllRoom();
        List<ChatRoomDTO> result = new ArrayList<>();

        for(ChatRoomDTO c : temp) {
            if(c.getRoomId().contains(userId)){
                result.add(c);
            }
        }

        if(result.size() == 0) return null;
        else return result;
    }

    public ChatRoomDTO findByRoomId(String roomId) {
        if(chatRooms.get(roomId) != null)
            return chatRooms.get(roomId);
        else return null;
    }

    //채팅방 생성
    public ChatRoomDTO createRoom(String id, String name, String submembers) {
        ChatRoomDTO chatRoom = ChatRoomDTO.create(id, name, submembers);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    //메시지 생성
    public ChatMessageDTO createMessage(ChatMessageDTO message) {
        ChatMessageDTO msg = ChatMessageDTO.create(message);
        messages.put(msg.getRoomId(), msg);
        logger.info("MSG: " + message.getMessage());
        return msg;
    }

    // 세션에서 로그인 정보를 가져와서 모델에 세팅
    public void setLoginInfo(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            String loginMemberId = session.getAttribute("loginMemberId").toString();
            String loginMemberName = session.getAttribute("loginMemberName").toString();
            model.addAttribute("loginMemberId", loginMemberId);
            model.addAttribute("loginMemberName", loginMemberName);
        }

        // 임시 프로필 상세 페이지 / 모집 글 상세 페이지 들어가서 채팅할 때 사용
        // 고유 id가 111인 임시 회원과 대화하도록 연결함. 나중에 /p/{고유 id}형태 페이지로 만들어졌을 때는 이 것을 삭제해야함.
        else {
            model.addAttribute("loginMemberId", "111");
            model.addAttribute("loginMemberName", "김철수");
        }
    }

    public void setChatTargetInfo(Long targetId, Model model) {
        // 채팅 상대방 고유 id 세팅
        model.addAttribute("targetId", targetId);

        // 채팅 상대방 이름 세팅
        String targetName = memberService.findMemberNameById(targetId);
        model.addAttribute("targetName", targetName);
    }
}
