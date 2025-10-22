package com.jjang051.board02.dto;

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
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regdate;
    private int hit;
    private String password;
    private String secretValue;  // 'Y' or 'N'

    public String getSecret() { return secretValue; }
    public void setSecret(String secretValue) { this.secretValue = secretValue; }

    public String getWriter() { return writer; }
    public void setWriter(String writer) { this.writer = writer; }

}
