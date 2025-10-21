package com.jjang051.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private String title;
    private String content;
    private String writer;
    private int hit;
    private LocalDateTime regdate;
    private int idx;


}
