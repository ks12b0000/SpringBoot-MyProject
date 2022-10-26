package com.example.myproject.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JoinReqDto {

    // 유저 아이디
    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 4, message = "아이디는 4자 이상으로 입력해주세요.")
    private String username;

    // 유저 이름
    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 2, message = "이름은 2자 이상으로 입력해주세요.")
    private String name;

    // 유저 이메일
    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

    // 유저 비밀번호
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, message = "비밀번호는 8자 이상으로 입력해주세요.")
    private String password;


}
