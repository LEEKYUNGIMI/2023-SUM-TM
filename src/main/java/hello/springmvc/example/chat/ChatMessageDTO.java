package hello.springmvc.example.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class ChatMessageDTO {

        private String sender;
        private String roomId;
        private String content;
}
