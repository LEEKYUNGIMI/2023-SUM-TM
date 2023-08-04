package hello.springmvc.example.v1.controller;


import hello.springmvc.example.v1.domain.GenderType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MemberForm {

    private String name; //이름

    private String loginId; //로그인 ID

    private String password; //비밀번호

    private String email; //이메일

    private GenderType gender; //성별

    private List<String> fields; //관심 분야
}