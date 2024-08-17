package bitc.ftp.teamproject.dto.admin;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminQuestionDTO {

    private int questionNo;
    private int userNo;
    private String content;
    private Date uploadDate;
    private int productNo;
    private String userName;
    private String productName;

    private boolean hasAnswer; // 답변 상태 설정
    private String answerContent; // 답변 내용 추가

}
