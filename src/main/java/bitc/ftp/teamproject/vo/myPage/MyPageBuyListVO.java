package bitc.ftp.teamproject.vo.myPage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyPageBuyListVO {
	int buylistNo;
	int productNo;
	String productName;
	String productPhoto;
	String productPrice;
	String productColor;
	String productSize;
	String address;
	Date buyDate;
}
