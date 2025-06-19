package com.example.jspcmsfinal.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AnswerTm {
    private String ansId;
    private String complainId;
    private String complainSubject;
    private String subject;
    private String message;
    private String date;
}
