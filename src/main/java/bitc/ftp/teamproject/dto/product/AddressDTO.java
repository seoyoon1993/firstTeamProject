package bitc.ftp.teamproject.dto.product;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    int addressListNo;
    int userNo;
    String address;
    String addressName;
}
