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
public class BoardWithMemberDto {
    private int id;
    private String userid;
    private String title;
    private String content;
    private LocalDateTime regdate;
    private int hit;
    private String secretValue;
    private String writer_id;
    private String writer_name;
}
