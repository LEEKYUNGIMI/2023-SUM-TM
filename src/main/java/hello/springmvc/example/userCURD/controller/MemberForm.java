package hello.springmvc.example.userCURD.controller;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MemberForm {
    private String name;
    private String gender;
    private String field;
    private Integer age;
}
