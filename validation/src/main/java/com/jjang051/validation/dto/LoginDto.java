package com.jjang051.validation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank(message = "사용자 아이디는 필수입력사항입니다.")
    private String userID;

    @NotBlank(message = "비밀번호는 필수입력사항입니다.")
    @Size(min = 2, max = 16, message = "비민번호는 2글자 이상 16자 이하로 압력해주세요.")
    private String userPW;
}
