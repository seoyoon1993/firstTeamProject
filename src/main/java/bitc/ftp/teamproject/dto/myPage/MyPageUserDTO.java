package bitc.ftp.teamproject.dto.myPage;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class MyPageUserDTO {
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
