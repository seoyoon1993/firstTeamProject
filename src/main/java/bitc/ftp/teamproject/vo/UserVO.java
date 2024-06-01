package bitc.ftp.teamproject.vo;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
	int userNo;
	String id;
	String pw;
	String name;
	String birth;
	String tel;
	int gender;
	String email;
	String refundAccount;
	int userGradeNo;
}
