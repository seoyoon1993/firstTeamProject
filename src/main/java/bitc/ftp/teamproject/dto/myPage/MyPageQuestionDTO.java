package bitc.ftp.teamproject.dto.myPage;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyPageQuestionDTO {
	int userNo;
	int questionNo;
	String questionContent;
	String questionUploadDate;
	int productNo;
	String productName;
}
