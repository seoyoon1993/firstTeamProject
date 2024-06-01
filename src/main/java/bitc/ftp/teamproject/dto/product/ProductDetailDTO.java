package bitc.ftp.teamproject.dto.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDetailDTO {
    private int productNo;
    private String name;
    private int price;
    private String photoName;
}
