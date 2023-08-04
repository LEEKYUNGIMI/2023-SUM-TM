package hello.springmvc.example.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessageSendingOperations sendingOperations; //특정 Broker로 메세지를 전달. SimpMessagingTemplate 와 같음.
    private final ChatService chatService;

    // Client가 SEND할 수 있는 경로
    // Config에서 설정한 applicationDestinationPrefixes, @MessageMapping 경로가 병합
    // "pub/chat/enter"
    @MessageMapping("/chat/message")
    public void enter(ChatMessageDTO message) {
        if (ChatMessageDTO.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender()+"님이 입장하였습니다.");
        }

        chatService.createMessage(message);
        sendingOperations.convertAndSend("/sub/chat/room/"+message.getRoomId(),message);
    }
}


