package bitc.ftp.teamproject.service;

import bitc.ftp.teamproject.dto.CategoryDTO;
import bitc.ftp.teamproject.dto.Product.ProductDTO;
import bitc.ftp.teamproject.mapper.ProductMapper;
import bitc.ftp.teamproject.vo.CategoryVO;
import bitc.ftp.teamproject.vo.product.ProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper pMapper;

    public List<ProductDTO> getAllProducts(int category2No) {
        List<ProductVO> voList = pMapper.getProductList(category2No);

        List<ProductDTO> dtoList = new ArrayList<>();
        for (ProductVO vo : voList) {
            ProductDTO dto = new ProductDTO();
            dto.setProductNo(vo.getProductNo());
            dto.setName(vo.getName());
            dto.setPrice(vo.getPrice());
            dto.setPhotoName(vo.getPhotoName());
            dtoList.add(dto);
        }

        return dtoList;
    }
    public List<CategoryDTO> getAllCategory1() {
        List<CategoryVO> voList1 = pMapper.getAllCategory1List();

        List<CategoryDTO> dtoList1 = new ArrayList<>();
        for (CategoryVO vo : voList1) {
            CategoryDTO dto = new CategoryDTO();
            dto.setName(vo.getName());
            dto.setName_ko(vo.getNameKo());
            dtoList1.add(dto);
        }
        return dtoList1;
    }
    public List<CategoryDTO> getAllCategory2() {
        List<CategoryVO> voList2 = pMapper.getAllCategory2List();

        List<CategoryDTO> dtoList2 = new ArrayList<>();
        for (CategoryVO vo : voList2) {
            CategoryDTO dto = new CategoryDTO();
            dto.setName(vo.getName());
            dto.setName_ko(vo.getNameKo());
            dtoList2.add(dto);
        }
        return dtoList2;
    }

}
