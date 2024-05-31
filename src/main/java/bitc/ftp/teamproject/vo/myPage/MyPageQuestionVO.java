package bitc.ftp.teamproject.vo.myPage;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyPageQuestionVO {
	int userNo;
	int questionNo;
	String questionContent;
	Date questionUploadDate;
	int productNo;
	String productName;
}
