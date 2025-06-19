package com.example.jspcmsfinal.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ComplainTm {
    private String uEmail;
    private String subject;
    private String message;
    private String status;
}
