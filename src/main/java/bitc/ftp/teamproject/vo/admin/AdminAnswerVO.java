package bitc.ftp.teamproject.vo.admin;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminAnswerVO {

  private int answerNo;
  private int questionNo;
  private String content;
  private Date uploadDate;
  private int userNo;
}
