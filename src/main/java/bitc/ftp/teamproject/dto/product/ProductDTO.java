package bitc.ftp.teamproject.dto.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {

    private int productNo;
    private String name;
    private int price;
    private int category2No;
    private String photoName;

}
