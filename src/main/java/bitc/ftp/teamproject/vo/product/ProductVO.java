package bitc.ftp.teamproject.vo.product;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVO {
    private int productNo;
    private String name;
    private int price;
    private int category2No;
    private String photoName;

}
