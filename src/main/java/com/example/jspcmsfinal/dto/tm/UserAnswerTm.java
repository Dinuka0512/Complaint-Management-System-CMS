package com.example.jspcmsfinal.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserAnswerTm {
    public String uEmail;
    public String comSubject;
    public String comMessage;
    public String comDate;
    public String ansSubject;
    public String ansMessage;
    public String ansDate;
}
