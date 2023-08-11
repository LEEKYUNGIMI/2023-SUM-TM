package hello.springmvc.example.chat.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageDTO {

        public enum MessageType {
                TALK, ENTER;
        }

        // 메시지 ID
        private String messageId;

        // 채팅방 ID
        private String roomId;

        //보내는 사람이름
        private String senderName;

        //보내는 사람id
        private String senderId;

        //내용
        private String message;

        //메시지 타입
        private MessageType type;

        // 읽음 확인
        private Boolean checked;

        public static ChatMessageDTO create(ChatMessageDTO msg) {
                ChatMessageDTO message = new ChatMessageDTO();
                message.messageId = UUID.randomUUID().toString();
                message.roomId = msg.roomId;
                message.senderName = msg.senderName;
                message.senderId = msg.senderId;
                message.message = msg.message;
                message.type = msg.type;
                message.checked = msg.checked;
                return message;
        }
}
