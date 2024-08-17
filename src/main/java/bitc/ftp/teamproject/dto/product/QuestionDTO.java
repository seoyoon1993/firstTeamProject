package bitc.ftp.teamproject.dto.product;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionDTO {
	int questionNo;
	int userNo;
	String name;
	int productNo;
	String content;
	String uploadDate;
}

