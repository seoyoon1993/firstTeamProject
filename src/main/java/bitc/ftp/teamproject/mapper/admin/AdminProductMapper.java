package bitc.ftp.teamproject.mapper.admin;


import bitc.ftp.teamproject.dto.CategoryDTO;
import bitc.ftp.teamproject.vo.admin.AdminCategoryVO;
import bitc.ftp.teamproject.vo.admin.AdminProductPhotoVO;
import bitc.ftp.teamproject.vo.admin.AdminProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminProductMapper {
  List<AdminProductVO> getProductList();
  List<AdminProductVO> searchProducts(String keyword);
  List<AdminProductVO> searchProductsByCategory(String keyword, String category);
  List<AdminCategoryVO> getCategory1List();

  AdminProductVO getProductById(int productNo);
  void insertProduct(AdminProductVO adminProductVO);

  void insertProductPhoto(AdminProductPhotoVO adminProductPhotoVO);

  void updateProduct(AdminProductVO adminProductVO);
  void updateProductPhoto(AdminProductPhotoVO adminProductPhotoVO);

  void deleteProduct(@Param("productNo") int productNo);

  void deleteProductPhotosByProductNo(int productNo);

  String findCategory1NameByCategory2No(int category2No);
  String findCategory2NameByCategory2No(int category2No);
}
