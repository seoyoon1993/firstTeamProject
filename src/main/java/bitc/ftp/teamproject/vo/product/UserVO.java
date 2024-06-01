package bitc.ftp.teamproject.vo.product;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    int userNo;
    String name;
    String tel;
    String email;
    String refundAccount;
}
