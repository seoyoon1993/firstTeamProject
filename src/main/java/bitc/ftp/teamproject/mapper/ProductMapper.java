package bitc.ftp.teamproject.mapper;

import bitc.ftp.teamproject.vo.CategoryVO;
import bitc.ftp.teamproject.vo.product.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductVO> getProductList(int category2No);
    List<CategoryVO> getAllCategory1List();
    List<CategoryVO> getAllCategory2List();

    ProductDetailVO getProductDetail(int productNo);
    List<ColorVO> getAllColorList(int productNo);
    List<SizeVO> getAllSizeList(int productNo);

    void addCart(AddCartVO addCartVO);

    ProductUserVO getUserVO(int userNo);

    List<AddressVO> getAddressVO(int userNo);

    ColorVO getOneColorVO(int colorNo);
    SizeVO getOneSizeVO(int sizeNo);

    void buyVO(BuyVO buyVO);

    List<QuestionVO> getQuestionList(int productNo, int userNo);
    List<AnswerVO> getAnswerList(int questionNo);
    void addQuestion(QuestionVO questionVO);

}
