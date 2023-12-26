package com.example.pmproject.DTO;

import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberPasswordDTO {

    private String email;

    private Integer findPwdHint;

    private String findPwdAnswer;

    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력하여 주십시오.")
    private String password;
}
