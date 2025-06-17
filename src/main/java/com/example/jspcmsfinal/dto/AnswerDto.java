package com.example.jspcmsfinal.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AnswerDto {
    private String ansId;
    private String complainId;
    private String subject;
    private String message;
    private String date;
}
