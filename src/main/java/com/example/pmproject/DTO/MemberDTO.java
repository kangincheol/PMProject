package com.example.pmproject.DTO;

import com.example.pmproject.Constant.Role;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

    private Long memberId; //멤버 아이디

    @NotEmpty(message = "이메일은 필수 입력 사항입니다.")
    private String email;

    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력하여 주십시오.")
    @NotEmpty(message = "비밀번호는 필수 입력 사항입니다.")
    private String password;

    @NotEmpty(message = "닉네임은 필수 입력 사항입니다.")
    private String name;

    private String address;

    private String tel;

    private Integer findPwdHint;

    @NotEmpty(message = "비밀번호 찾기 답변은 필수 입력 사항입니다.")
    private String findPwdAnswer;

    private Role role;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
