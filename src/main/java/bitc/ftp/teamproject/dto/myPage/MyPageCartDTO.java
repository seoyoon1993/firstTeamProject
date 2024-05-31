package bitc.ftp.teamproject.dto.myPage;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyPageCartDTO {
	int cartNo;
	int userNo;
	int productNo;
	String productName;
	String productPrice;
}
