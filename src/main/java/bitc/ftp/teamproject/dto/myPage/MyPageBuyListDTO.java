package bitc.ftp.teamproject.dto.myPage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyPageBuyListDTO{
	int buylistNo;
	int productNo;
	String productName;
	String productPhoto;
	String productPrice;
	String productColor;
	String productSize;
	String address;
	String buyDate;
}