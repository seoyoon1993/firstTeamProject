package bitc.ftp.teamproject.mapper;

import bitc.ftp.teamproject.vo.CategoryVO;
import bitc.ftp.teamproject.vo.product.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductVO> getProductList(int category2No);
    List<CategoryVO> getAllCategory1List();
    List<CategoryVO> getAllCategory2List();
}
