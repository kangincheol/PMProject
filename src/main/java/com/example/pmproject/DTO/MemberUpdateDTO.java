package com.example.pmproject.DTO;

import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberUpdateDTO {

    private String email;

    private String recentPassword;

    private String newPassword;

    private String name;

    private String address;

    private String tel;

}
