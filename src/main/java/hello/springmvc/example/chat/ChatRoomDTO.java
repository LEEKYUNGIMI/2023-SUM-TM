package hello.springmvc.example.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomDTO {

    private String roomId;
    private String roomName;
    private String submembers;


    public static ChatRoomDTO create(String name, String submembers) {
        ChatRoomDTO room = new ChatRoomDTO();
        room.roomId = submembers + name;    // 임시로 지정.
        room.roomName = name;
        room.submembers = submembers;
        return room;
    }
}