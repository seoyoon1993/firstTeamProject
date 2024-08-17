package bitc.ftp.teamproject.vo.admin;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminQuestionVO {

  private int questionNo;
  private int userNo;
  private String content;
  private Date uploadDate;
  private int productNo;
  private String userName;
  private String productName;

}
