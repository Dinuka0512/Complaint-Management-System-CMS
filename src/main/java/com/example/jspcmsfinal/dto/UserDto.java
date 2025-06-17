package com.example.jspcmsfinal.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    private String uId;
    private String name;
    private String email;
    private String password;
    private String role;
}
