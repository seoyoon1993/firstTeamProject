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
    private int category1No;
    private String category1Name;
    private String category2Name;
    private String photoName;

}
