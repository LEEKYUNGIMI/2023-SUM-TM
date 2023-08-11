package hello.springmvc.example.chat.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomDTO {

    private String roomId;
    private String roomName;
    private String submembers;


    public static ChatRoomDTO create(String id, String name, String submembers) {
        ChatRoomDTO room = new ChatRoomDTO();
        room.roomId = id;
        room.roomName = name;
        room.submembers = submembers;
        return room;
    }
}