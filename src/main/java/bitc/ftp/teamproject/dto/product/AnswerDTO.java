package bitc.ftp.teamproject.dto.product;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnswerDTO {
	int userNo;
	String name;
	int questionNo;
	String content;
	String uploadDate;
}