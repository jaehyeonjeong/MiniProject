package com.jjang051.member.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JoinDto {
    //Dto data tranfer object
    private String userID;
    private String userPW;
    private String userName;
    private int userAge;
}

