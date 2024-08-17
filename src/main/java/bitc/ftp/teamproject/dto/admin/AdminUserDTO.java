package bitc.ftp.teamproject.dto.admin;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminUserDTO {

    private int userNo;
    private String name;
    private String birth;
    private String tel;
    private int gender;
    private String email;
    private String id;
    private String pw;
    private String refundAccount;
    private int userGradeNo;
    private String grade;

}
