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
public class AnswerVO {
	int userNo;
	String name;
	int questionNo;
	String content;
	Date uploadDate;
}
