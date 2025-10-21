package com.jjang051.member.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private int idx;
    private String userID;
    private String userPW;
    private String userName;

}
