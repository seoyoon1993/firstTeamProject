package bitc.ftp.teamproject.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
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