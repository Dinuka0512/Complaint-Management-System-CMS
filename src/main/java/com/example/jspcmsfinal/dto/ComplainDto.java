package com.example.jspcmsfinal.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ComplainDto {
    private String complainId;
    private String uId;
    private String subject;
    private String message;
    private String status;
    private String date;
}
