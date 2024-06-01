package bitc.ftp.teamproject.vo.product;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailVO {
    private int productNo;
    private String name;
    private int price;
    private String photoName;
}
