const targetId = window.targetId;                 // 페이지 작성자 id
const targetName = window.targetName;             // 페이지 작성자 이름
const loginMemberId = window.loginMemberId;       // 로그인 사용자 id
const loginMemberName = window.loginMemberName;   // 로그인 사용자 이름

let vm = new Vue({
    el: '#app',
    data: {
        roomId : 'roomId',              // 채팅방 id
        roomName : 'roomName',          // [상대방이름] 님과의 채팅방
        subMembers : 'subMembers',      // 구독자: 채팅 요청자 - 수신자 id
        chatrooms: [],
    },
    created() {
        this.findByUserId();
    },
    methods: {
        // 마이페이지에서 내가 참여중인 채팅방들 검색해서 chatrooms로 가져온다.
        findByUserId: function() {
            axios.get(`/chat/rooms/` + loginMemberId).then(response => {  // 로그인 id 정보로 참여중인 채팅방 조회해서 가져온다.
                this.chatrooms = response.data;
            });
        },
        // 기존 채팅방이 존재하는지 검사
        searchRoom: function() {
            console.log("targetId = " + targetId);
            console.log("targetName = " + targetName);
            console.log("loginMemberId = " + loginMemberId);

            this.subMembers = loginMemberId + "-" + targetId;
            this.roomId = targetId + "-" + loginMemberId;
            this.roomName = targetName + ", " + loginMemberName;

            console.log("roomName : " + this.roomName);
            console.log("subMembers : " + this.subMembers);
            console.log("roomId : " + this.roomId);

            console.log("searchRoom!");
            axios.get(`/chat/room/` + this.loginMemberId)
                .then(response => {  // 로그인 id 정보로 조회해서 가져온다.

                    if(response.data.roomId != null) { // 기존 채팅방이 존재하면 입장
                        this.enterRoom();
                    }
                    else { // 기존 채팅방이 없으면 새로 만들고 입장
                        this.createRoom();
                    }
                })
                .catch(error => {
                    console.error('Error:', error); // 에러가 발생하면 콘솔에 오류 출력
                });
        },
        // 기존 채팅방이 없으면 생성
        createRoom: function() {
            console.log("createRoom!");
            let params = new URLSearchParams();

            // 방 생성용 정보들을 post 하면서 같이 보낸다.
            params.append("id", this.roomId);
            params.append("name",this.roomName);
            params.append("subMembers", this.subMembers);

            axios.post('/chat/room', params)
                .then(
                    response => {
                        alert(response.data.roomName + "방 개설에 성공하였습니다.")
                        this.enterRoom(null);
                    }
                )
                .catch( response => { alert("채팅방 개설에 실패하였습니다."); } );
        },
        // 채팅방 입장
        enterRoom: function(item) {
            console.log("enterRoom!");
            let tempId;

            if(item == null) tempId = this.roomId;      // post, profile.html 에서 채팅방 입장하는 경우
            else tempId = item.roomId;                // 마이페이지에서 채팅방 입장하는 경우
            localStorage.setItem('wschat.roomId', tempId);

            // 채팅방을 팝업창으로 열기
            let url = "/chat/room/enter/" + tempId;
            let name = "_blank";
            let option = "width = 500, height = 800, top = 100, left = 200, location = no"
            window.open(url, name, option);
        },
    }
});