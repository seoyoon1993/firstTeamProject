package bitc.ftp.teamproject.dto.product;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    int userNo;
    String name;
    String tel;
    String email;
    String refundAccount;
}
