package bitc.ftp.teamproject.dto.admin;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminAnswerDTO {

    private int answerNo;
    private int questionNo;
    private String content;
    private Date uploadDate;
    private int userNo;
}
