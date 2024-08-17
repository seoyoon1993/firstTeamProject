package bitc.ftp.teamproject.vo.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionVO {
	int questionNo;
	int userNo;
	String name;
	int productNo;
	String content;
	Date uploadDate;
}