package com.jjang051.board02.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private int id;

    @NotBlank(message ="제목은 필수입력사항입니다")
    @Size(min = 2, message = "제목은 2글자 이상 쓰셔야 합니다")
    private String title;

    @NotBlank(message ="내용은 필수입력사항입니다")
    private String content;

    @NotBlank(message ="글쓴이는 필수입력사항입니다")
    private String writer;

    private LocalDateTime regdate;
    private int hit;

    @NotBlank(message = "삭제 비밀번호는 필수입력사항입니다")
    @Size(min = 3,max = 16,message = "삭제 비밀번호는 4글자 이상 16자 이하로 입력해주세요")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/]).+$",
            message = "삭제 비밀번호는 대문자,소문자,특수문자,숫자를 모두 포함하여야 합니다.")
    private String password;

    private String secretValue;  // 'Y' or 'N'

    @NotBlank(message = "비밀글 비밀번호는 필수입력사항입니다")
    @Size(min = 3,max = 16,message = "비밀글 비밀번호는 4글자 이상 16자 이하로 입력해주세요")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/]).+$",
            message = "비밀글 비밀번호는 대문자,소문자,특수문자,숫자를 모두 포함하여야 합니다.")
    private String secretPW;

    private String userID;

    public String getSecret() { return secretValue; }
    public void setSecret(String secretValue) { this.secretValue = secretValue; }

    public String getWriter() { return writer; }
    public void setWriter(String writer) { this.writer = writer; }

}
