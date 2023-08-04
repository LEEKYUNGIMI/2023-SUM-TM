package hello.springmvc.example.chat;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatService {

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

    //사용자 id로 채팅방 불러오기
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
    public ChatRoomDTO createRoom(String name, String submembers) {
        ChatRoomDTO chatRoom = ChatRoomDTO.create(name, submembers);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        logger.info("ID="+chatRoom.getRoomId());
        logger.info("getroom: " + chatRooms.get(chatRoom.getRoomId()));
        return chatRoom;
    }

    //메시지 생성
    public ChatMessageDTO createMessage(ChatMessageDTO message) {
        ChatMessageDTO msg = ChatMessageDTO.create(message);
        messages.put(msg.getRoomId(), msg);
        logger.info("MSG: " + message.getMessage());
        return msg;
    }
}
