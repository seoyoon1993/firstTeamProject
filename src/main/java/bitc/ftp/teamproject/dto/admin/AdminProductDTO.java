package bitc.ftp.teamproject.dto.admin;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminProductDTO {


  private int photoNo;
  private String photoName;

  private int productNo;
  private String productName;
  private int price;
  private int category2No;

  private String category2Name;
  private int category1No;
  private String category2Name_ko;

  private String category1Name;
  private String category1Name_ko;

  private int colorNo;
  private String colorName;

}
