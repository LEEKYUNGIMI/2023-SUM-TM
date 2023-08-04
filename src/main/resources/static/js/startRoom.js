let vm = new Vue({
    el: '#app',
    data: {
        roomId : '',
        roomName : '김철수-홍길동',   // 채팅 요청자 - 수신자 이름
        subMembers : '1-1234',      // 채팅 요청자 - 수신자 id
    },
    methods: {
        // 기존 채팅방이 존재하는지 검사
        searchRoom: function() {
            this.roomId = window.pageId;
            console.log("roomId = " + this.roomId);
            console.log("searchRoom!");
            axios.get(`/chat/room/` + this.roomId)
                .then(response => {  // 채팅방 id 정보로 조회해서 가져온다.

                    if(response.data.roomId != null) { // 기존 채팅방이 존재하면 입장
                        console.log(response.data.roomId);
                        this.enterRoom();
                    }
                    else { // 기존 채팅방이 없으면 새로 만들고 입장
                        this.createRoom();
                    }
                })
                .catch(error => {
                    console.error('Error:', error); // 에러가 발생하면 콘솔에 오류 출력
                });;
        },
        // 기존 채팅방이 없으면 생성
        createRoom: function() {
            console.log("createRoom!");
            let params = new URLSearchParams();
            // 서로 상대방의 이름이 방제목이 되도록 처리해서 room_name 변경 필요함.
            // 발신자-수신자 id로 subMembers 데이터 설정 필요함.
            params.append("name",this.roomName);
            params.append("subMembers", this.subMembers);
            axios.post('/chat/room', params)
                .then(
                    response => {
                        alert(response.data.roomName+"방 개설에 성공하였습니다.")
                        this.roomId = response.data.roomId;
                        this.enterRoom();
                    }
                )
                .catch( response => { alert("채팅방 개설에 실패하였습니다."); } );
        },
        // 채팅방 입장
        enterRoom: function() {
            console.log("enterRoom!");
            let sender = "김철수"  // 추후 접속자이름으로 변경 필요함.
            if(sender !== "") {
                localStorage.setItem('wschat.sender', sender);
                localStorage.setItem('wschat.roomId', this.roomId);
                // location.href="/chat/room/enter/" + this.roomId;
            }
        }
    }
});