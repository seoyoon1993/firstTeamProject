package bitc.ftp.teamproject.vo.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVO {
    int productNo;
    int userNo;
    String buyDate;
    int addressListNo;
    int colorNo;
    int sizeNo;
}
