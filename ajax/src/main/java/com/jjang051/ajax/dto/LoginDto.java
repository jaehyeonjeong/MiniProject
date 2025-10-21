package com.jjang051.ajax.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String userID;
    private String userPW;
}
