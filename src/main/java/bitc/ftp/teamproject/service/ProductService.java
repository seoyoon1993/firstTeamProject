package bitc.ftp.teamproject.service;

import bitc.ftp.teamproject.dto.CategoryDTO;
import bitc.ftp.teamproject.dto.product.*;
import bitc.ftp.teamproject.mapper.ProductMapper;
import bitc.ftp.teamproject.vo.CategoryVO;
import bitc.ftp.teamproject.vo.product.*;
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
    public ProductDetailDTO getProductDetail(int productNo) {
        ProductDetailVO productDetailVO = pMapper.getProductDetail(productNo);

        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setProductNo(productDetailVO.getProductNo());
        dto.setName(productDetailVO.getName());
        dto.setPrice(productDetailVO.getPrice());
        dto.setPhotoName(productDetailVO.getPhotoName());

        return dto;
    }
    public List<CategoryDTO> getAllCategory1() {
        List<CategoryVO> voList1 = pMapper.getAllCategory1List();
        List<CategoryDTO> dtoList1 = new ArrayList<>();
        for (CategoryVO vo : voList1) {
            CategoryDTO dto = new CategoryDTO();
            dto.setCategoryNo(vo.getCategoryNo());
            dto.setName(vo.getName());
            dto.setName_ko(vo.getNameKo());
            dtoList1.add(dto);
        }
        return dtoList1;
    }
    public List<CategoryDTO> getAllCategory2() {
        List<CategoryVO> voList2 = pMapper.getAllCategory2List();
        for (CategoryVO vo : voList2) {
            System.out.println(vo);
        }
        List<CategoryDTO> dtoList2 = new ArrayList<>();
        for (CategoryVO vo : voList2) {
            CategoryDTO dto = new CategoryDTO();
            dto.setCategoryNo(vo.getCategoryNo());
            dto.setName(vo.getName());
            dto.setName_ko(vo.getNameKo());
            dtoList2.add(dto);
        }
        return dtoList2;
    }
    public List<ColorDTO> getAllColor(int productNo) {
        List<ColorVO> voList = pMapper.getAllColorList(productNo);

        List<ColorDTO> dtoList = new ArrayList<>();

        for (ColorVO vo : voList) {
            ColorDTO dto = new ColorDTO();
            dto.setColorName(vo.getColorName());
            dto.setColorNo(vo.getColorNo());
            dtoList.add(dto);
        }
        return dtoList;
    }
    public List<SizeDTO> getAllSize(int productNo) {
        List<SizeVO> voList = pMapper.getAllSizeList(productNo);

        List<SizeDTO> dtoList = new ArrayList<>();
        for (SizeVO vo : voList) {
            SizeDTO dto = new SizeDTO();
            dto.setSizeName(vo.getSizeName());
            dto.setSizeNo(vo.getSizeNo());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public void addCartDTO(AddCartDTO addCartDTO) {
        AddCartVO vo = new AddCartVO();
        vo.setProductNo(addCartDTO.getProductNo());
        vo.setUserNo(addCartDTO.getUserNo());

        pMapper.addCart(vo);
    }

    public UserDTO getUserVO(int userNo) {
        UserVO userVO = pMapper.getUserVO(userNo);
        UserDTO dto = new UserDTO();
        dto.setUserNo(userVO.getUserNo());
        dto.setName(userVO.getName());
        dto.setTel(userVO.getTel());
        dto.setEmail(userVO.getEmail());
        dto.setRefundAccount(userVO.getRefundAccount());
        return dto;
    }
    public List<AddressDTO> getAddressVO(int userNo) {
        List<AddressVO> vo = pMapper.getAddressVO(userNo);
        List<AddressDTO> dtoList = new ArrayList<>();
        for (AddressVO vo1 : vo) {
            AddressDTO dto = new AddressDTO();
            dto.setAddressListNo(vo1.getAddressListNo());
            dto.setUserNo(vo1.getUserNo());
            dto.setAddress(vo1.getAddress());
            dto.setAddressName(vo1.getAddressName());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public ColorDTO getOneColorVO(int colorNo) {
        ColorVO vo = pMapper.getOneColorVO(colorNo);

        ColorDTO dto = new ColorDTO();
        dto.setColorName(vo.getColorName());
        dto.setColorNo(vo.getColorNo());
        return dto;
    }

    public SizeDTO getOneSizeVO(int sizeNo) {
        SizeVO vo = pMapper.getOneSizeVO(sizeNo);

        SizeDTO dto = new SizeDTO();
        dto.setSizeName(vo.getSizeName());
        dto.setSizeNo(vo.getSizeNo());
        return dto;
    }

    public void buyDTO(BuyDTO buyDTO) {
        BuyVO vo = new BuyVO();
        vo.setProductNo(buyDTO.getProductNo());
        vo.setUserNo(buyDTO.getUserNo());
//        vo.setBuyDate();
        vo.setAddressListNo(buyDTO.getAddressListNo());
        vo.setColorNo(buyDTO.getColorNo());
        vo.setSizeNo(buyDTO.getSizeNo());
        pMapper.buyVO(vo);
    }

}
