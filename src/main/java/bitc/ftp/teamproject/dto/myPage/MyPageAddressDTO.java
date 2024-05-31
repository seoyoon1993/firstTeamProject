package bitc.ftp.teamproject.dto.myPage;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MyPageAddressDTO {
	int userNo;
	int addressListNo;
	String address;
	String addressName;
}