package bitc.ftp.teamproject.vo.myPage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class MyPageUserVO {
	int userNo;
	String id;
	String pw;
	String name;
	String birth;
	String tel;
	int gender;
	String email;
	String refundAccount;
}