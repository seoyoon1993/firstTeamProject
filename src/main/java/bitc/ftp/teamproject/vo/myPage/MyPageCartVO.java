package bitc.ftp.teamproject.vo.myPage;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyPageCartVO {
	int cartNo;
	int userNo;
	int productNo;
	String productName;
	String productPrice;
}
