package bitc.ftp.teamproject.controller.admin;


import bitc.ftp.teamproject.dto.CategoryDTO;
import bitc.ftp.teamproject.dto.admin.AdminCategoryDTO;
import bitc.ftp.teamproject.dto.admin.AdminProductDTO;
import bitc.ftp.teamproject.service.ProductService;
import bitc.ftp.teamproject.service.admin.AdminProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
  private AdminProductService adminProductService;
  private ProductService pService;

  public void categoryTest(Model model){
    List<CategoryDTO> c1DTO = pService.getAllCategory1();
    List<CategoryDTO> c2DTO = pService.getAllCategory2();
    model.addAttribute("category1List", c1DTO);
    model.addAttribute("category2List", c2DTO);
  }

  //메인화면_제품 목록 조회
  @GetMapping
  public String showProducts( Model model) {
    List<AdminProductDTO> productList = adminProductService.getAllProduct();
    List<AdminCategoryDTO> category1List = adminProductService.getCategory1List();
    int currentPage = 1; // 현재 페이지 값을 설정
    int totalPages = 10; // 전체 페이지 수를 설정

    model.addAttribute("category1List", category1List);
    model.addAttribute("productList", productList);
    model.addAttribute("currentPage", currentPage);
    model.addAttribute("totalPages", totalPages);

    return "admin/products";
  }

  // 상품 등록 페이지로 이동
  @GetMapping("/add")
  public String addProductPage(Model model) {
    model.addAttribute("product", new AdminProductDTO());

    List<CategoryDTO> c1DTO = pService.getAllCategory1();
    List<CategoryDTO> c2DTO = pService.getAllCategory2();
    model.addAttribute("category1List", c1DTO);
    model.addAttribute("category2List", c2DTO);

    return "admin/addProduct";
  }

  // 상품 등록 처리
  @PostMapping("/add")
  public String addProduct(@RequestParam String productName,
                           @RequestParam int price,
                           @RequestParam int category2No,
                           @RequestParam(value = "photo", required = false) MultipartFile photo) {
    AdminProductDTO adminProductDTO = new AdminProductDTO();
    adminProductDTO.setProductName(productName);
    adminProductDTO.setPrice(price);
    adminProductDTO.setCategory2No(category2No);

    if (photo != null && !photo.isEmpty()) {
      String category1Name = adminProductService.getCategory1Name(category2No);
      String category2Name = adminProductService.getCategory2Name(category2No);

      String originalFilename = photo.getOriginalFilename();
      String photoNameWithoutExtension = UUID.randomUUID().toString();
      String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
      String photoPath = "/" + category1Name + "/" + category2Name + "/" + photoNameWithoutExtension;
      String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images" + photoPath + extension;

      adminProductDTO.setPhotoName(photoPath);
      File uploadDirFile = new File(uploadDir).getParentFile();
      if (!uploadDirFile.exists()) {
        uploadDirFile.mkdirs();
      }
      File file = new File(uploadDir);
      try {
        photo.transferTo(file);
        System.out.println("File saved at: " + file.getAbsolutePath());
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Error saving file: " + e.getMessage());
      }
    }

    adminProductService.addProduct(adminProductDTO);
    return "redirect:/admin/products";
  }



  // 제품 수정 폼을 보여주는 메소드
  @GetMapping("/edit/{productNo}")
  public String editProduct(@PathVariable int productNo, Model model) {
    AdminProductDTO product = adminProductService.getProductById(productNo);
    String imageUrl = "/images" + product.getPhotoName() + ".jpg";
    List<AdminProductDTO> productList = adminProductService.getAllProduct();
    model.addAttribute("product", product);
    model.addAttribute("imageUrl", imageUrl);
    model.addAttribute("category2List", productList);

    List<CategoryDTO> c1DTO = pService.getAllCategory1();
    List<CategoryDTO> c2DTO = pService.getAllCategory2();
    model.addAttribute("category1List", c1DTO);
    model.addAttribute("category2List", c2DTO);

    return "admin/editProduct";
  }

  @PostMapping("/update")
  public String updateProduct(@RequestParam int productNo,
                              @RequestParam String productName,
                              @RequestParam int price,
                              @RequestParam int category2No,
                              @RequestParam(value = "photo", required = false) MultipartFile photo) {
    AdminProductDTO adminProductDTO = new AdminProductDTO();
    adminProductDTO.setProductNo(productNo);
    adminProductDTO.setProductName(productName);
    adminProductDTO.setPrice(price);
    adminProductDTO.setCategory2No(category2No);

    if (photo != null && !photo.isEmpty()) {
      AdminProductDTO product = adminProductService.getProductById(productNo);
      String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images";
      Path oldImagePath = Paths.get(uploadDir + product.getPhotoName() + ".jpg");
      System.out.println("oldIamgePath : "+oldImagePath+".jpg");
      try{
        Files.delete(oldImagePath);
      }catch(Exception e){
        System.out.println("Error : " + e.getMessage());
      }

      String category1Name = adminProductService.getCategory1Name(category2No);
      String category2Name = adminProductService.getCategory2Name(category2No);

      String storFileName = photo.getOriginalFilename();
      String photoNameWithoutExtension = UUID.randomUUID().toString();
      String photoPath = "/" + category1Name + "/" + category2Name + "/" + photoNameWithoutExtension;
      String extension = storFileName.substring(storFileName.lastIndexOf("."));
      adminProductDTO.setPhotoName(photoPath);
      File uploadDirFile = new File(uploadDir+photoPath + extension).getParentFile();
      if (!uploadDirFile.exists()) {
        uploadDirFile.mkdirs();
      }
      File file = new File(uploadDir+photoPath+extension);
      try {
        photo.transferTo(file);
        System.out.println("File saved at: " + file.getAbsolutePath());
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Error saving file: " + e.getMessage());
      }
    }

    adminProductService.updateProduct(adminProductDTO);
    return "redirect:/admin/products";
  }


  @GetMapping("/search")
  public String searchProducts(@RequestParam("keyword") String keyword,
                               @RequestParam(value = "category", required = false) String category,
                               Model model) {
    System.out.println("----------------" + keyword +"//"+category);
    List<AdminCategoryDTO> category1List = adminProductService.getCategory1List();
    List<AdminProductDTO> productList;
    if (category != null && !category.isEmpty()) {
      productList = adminProductService.searchProductsByCategory(keyword, category);
    } else {
      productList = adminProductService.searchProducts(keyword);
    }
    model.addAttribute("category1List", category1List);
    model.addAttribute("productList", productList);
    return "admin/products";
  }

  @PostMapping("/delete")
  public String deleteProduct(@RequestParam int productNo) {

    AdminProductDTO product = adminProductService.getProductById(productNo);
    String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images";
    Path oldImagePath = Paths.get(uploadDir + product.getPhotoName() + ".jpg");
    System.out.println("oldIamgePath : "+oldImagePath+".jpg");
    try{
      Files.delete(oldImagePath);
    }catch(Exception e){
      System.out.println("Error : " + e.getMessage());
    }

    adminProductService.deleteProduct(productNo);
    return "redirect:/admin/products";
  }
}
