package bitc.ftp.teamproject.dto.product;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    int productNo;
    int userNo;
    String buyDate;
    int addressListNo;
    int colorNo;
    int sizeNo;
}
