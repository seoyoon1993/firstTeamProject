package bitc.ftp.teamproject.service.admin;



import bitc.ftp.teamproject.dto.CategoryDTO;
import bitc.ftp.teamproject.dto.admin.AdminCategoryDTO;
import bitc.ftp.teamproject.dto.admin.AdminProductDTO;
import bitc.ftp.teamproject.mapper.ProductMapper;
import bitc.ftp.teamproject.mapper.admin.AdminProductMapper;
import bitc.ftp.teamproject.vo.admin.AdminCategoryVO;
import bitc.ftp.teamproject.vo.admin.AdminProductPhotoVO;
import bitc.ftp.teamproject.vo.admin.AdminProductVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@AllArgsConstructor
@Service
public class AdminProductService {
  private final AdminProductMapper adminProductMapper;
  private final ProductMapper productMapper;

  //메인화면 모든 리스트 나열
  public List<AdminProductDTO> getAllProduct() {
    List<AdminProductVO> voList = adminProductMapper.getProductList();
    List<AdminProductDTO> dtoList = new ArrayList<>();
    for (AdminProductVO vo : voList) {
      AdminProductDTO dto = convertToDTO(vo);
      dtoList.add(dto);
    }
    return dtoList;
  }

  // 상품 등록 메소드 추가
  public void addProduct(AdminProductDTO adminProductDTO) {
    AdminProductVO adminProductVO = convertToVO(adminProductDTO);
    adminProductMapper.insertProduct(adminProductVO);

    int productNo = adminProductVO.getProductNo();

    if (adminProductDTO.getPhotoName() != null) {
      AdminProductPhotoVO photoVO = new AdminProductPhotoVO();
      photoVO.setPhotoName(adminProductDTO.getPhotoName());
      photoVO.setProductNo(productNo); // 생성된 productNo를 설정합니다.
      adminProductMapper.insertProductPhoto(photoVO);
    }
  }
  public String getCategory1Name(int category2No) {
    return adminProductMapper.findCategory1NameByCategory2No(category2No);
  }

  public String getCategory2Name(int category2No) {
    return adminProductMapper.findCategory2NameByCategory2No(category2No);
  }


  // ID로 제품 정보 가져오기
  public AdminProductDTO getProductById(int productNo) {
    AdminProductVO adminProductVO = adminProductMapper.getProductById(productNo);
    return convertToDTO(adminProductVO);
  }

  public List<AdminProductDTO> searchProducts(String keyword) {
    List<AdminProductVO> voList = adminProductMapper.searchProducts(keyword);
    List<AdminProductDTO> dtoList = new ArrayList<>();
    for (AdminProductVO vo : voList) {
      AdminProductDTO dto = convertToDTO(vo);
      dtoList.add(dto);
    }
    return dtoList;
  }

  public List<AdminProductDTO> searchProductsByCategory(String keyword, String category) {
    List<AdminProductVO> voList = adminProductMapper.searchProductsByCategory(keyword, category);
    List<AdminProductDTO> dtoList = new ArrayList<>();
    for (AdminProductVO vo : voList) {
      AdminProductDTO dto = convertToDTO(vo);
      dtoList.add(dto);
    }
    return dtoList;
  }

  public List<AdminCategoryDTO> getCategory1List(){
    List<AdminCategoryVO> voList = adminProductMapper.getCategory1List();
    List<AdminCategoryDTO> dtoList = new ArrayList<>();
    for (AdminCategoryVO vo : voList) {
      AdminCategoryDTO dto = new AdminCategoryDTO();
      dto.setName(vo.getName());
      dto.setNameKo(vo.getNameKo());
      dtoList.add(dto);
    }
    return dtoList;
  }
  public void updateProduct(AdminProductDTO adminProductDTO) {
    // ProductDTO를 ProductVO로 변환하여 업데이트
    AdminProductVO adminProductVO = convertToVO(adminProductDTO);
    adminProductMapper.updateProduct(adminProductVO);

    int productNo = adminProductVO.getProductNo();
    if(adminProductDTO.getPhotoName() != null) {
      AdminProductPhotoVO photoVO = new AdminProductPhotoVO();
      photoVO.setPhotoName(adminProductDTO.getPhotoName());
      photoVO.setProductNo(productNo);
      adminProductMapper.updateProductPhoto(photoVO);
    }
  }

  public void deleteProduct(int productNo) {
    // 해당 제품의 사진 정보를 먼저 가져옵니다.
    AdminProductVO product = adminProductMapper.getProductById(productNo);
    String photoName = product.getPhotoName();
    String category1Name = getCategory1Name(product.getCategory2No());
    String category2Name = getCategory2Name(product.getCategory2No());

    adminProductMapper.deleteProductPhotosByProductNo(productNo);
    adminProductMapper.deleteProduct(productNo);

    // 사진 파일을 삭제합니다.
    if (photoName != null) {
      String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/" + category1Name + "/" + category2Name + "/";
      File file = new File(uploadDir + photoName);
      if (file.exists()) {
        file.delete();
        System.out.println("File deleted: " + file.getAbsolutePath());
      } else {
        System.out.println("File not found: " + file.getAbsolutePath());
      }
    }
  }

  private AdminProductVO convertToVO(AdminProductDTO dto) {
    AdminProductVO vo = new AdminProductVO();
    vo.setPhotoNo(dto.getPhotoNo());
    vo.setPhotoName(dto.getPhotoName());
    vo.setProductNo(dto.getProductNo());
    vo.setProductName(dto.getProductName());
    vo.setPrice(dto.getPrice());
    vo.setCategory2No(dto.getCategory2No());
    vo.setCategory2Name(dto.getCategory2Name());
    vo.setCategory1No(dto.getCategory1No());
    vo.setCategory2NameKo(dto.getCategory2Name_ko());
    vo.setCategory1Name(dto.getCategory1Name());
    vo.setCategory1NameKo(dto.getCategory1Name_ko());
    return vo;
  }

  // VO를 DTO로 변환하는 메소드
  private AdminProductDTO convertToDTO(AdminProductVO vo) {
    AdminProductDTO dto = new AdminProductDTO();
    dto.setPhotoNo(vo.getPhotoNo());
    dto.setPhotoName(vo.getPhotoName());
    dto.setProductNo(vo.getProductNo());
    dto.setProductName(vo.getProductName());
    dto.setPrice(vo.getPrice());
    dto.setCategory2No(vo.getCategory2No());
    dto.setCategory2Name(vo.getCategory2Name());
    dto.setCategory1No(vo.getCategory1No());
    dto.setCategory2Name_ko(vo.getCategory2NameKo());
    dto.setCategory1Name(vo.getCategory1Name());
    dto.setCategory1Name_ko(vo.getCategory1NameKo());
    return dto;
  }
}


