package com.jjang051.db02.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Member {
    private Integer id;
    private String userID;
    private String userName;
    private String userEmail;
    private String userPW;
}
