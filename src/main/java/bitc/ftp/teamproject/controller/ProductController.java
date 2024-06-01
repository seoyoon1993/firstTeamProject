package bitc.ftp.teamproject.controller;


import bitc.ftp.teamproject.dto.CategoryDTO;
import bitc.ftp.teamproject.dto.product.*;
import bitc.ftp.teamproject.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService pService;



    public void categoryTest(Model model){
        List<CategoryDTO> c1DTO = pService.getAllCategory1();
        List<CategoryDTO> c2DTO = pService.getAllCategory2();
        model.addAttribute("category1List", c1DTO);
        model.addAttribute("category2List", c2DTO);
    }

    @GetMapping("/{category2No}")
    public String getProductList(@PathVariable int category2No, Model model) {
        List<ProductDTO> productList = pService.getAllProducts(category2No);
        List<CategoryDTO> category1List = pService.getAllCategory1();
        List<CategoryDTO> category2List = pService.getAllCategory2();
        model.addAttribute("pList", productList);
        model.addAttribute("category1List", category1List);
        model.addAttribute("category2List", category2List);
        return "product/product";
    }

    @GetMapping("/addcart/{productNo}")
    public String addProduct(@PathVariable int productNo, Model model) {
        AddCartDTO dto = new AddCartDTO();
        dto.setProductNo(productNo);
        dto.setUserNo(2);
        pService.addCartDTO(dto);
        return "redirect:/product/detail/" + productNo;
    }

    @GetMapping("/detail/{productNo}")
    public String getProductDetail(@PathVariable int productNo, @ModelAttribute("objectDTO") BuyDTO buyDTO, Model model) {

        ProductDetailDTO productDetail = pService.getProductDetail(productNo);
        List<ColorDTO> colorDTOList = pService.getAllColor(productNo);
        List<SizeDTO> sizeDTOList = pService.getAllSize(productNo);
        List<CategoryDTO> category1List = pService.getAllCategory1();
        List<CategoryDTO> category2List = pService.getAllCategory2();

        model.addAttribute("pd", productDetail);
        model.addAttribute("colorList", colorDTOList);
        model.addAttribute("sizeList", sizeDTOList);
        model.addAttribute("category1List", category1List);
        model.addAttribute("category2List", category2List);

        return "product/productDetail";
    }

    @PostMapping("/payment")
    public String payment(@ModelAttribute("objectDTO")BuyDTO buyDTO, Model model) {
        categoryTest(model);
        int userNo = 2;
        UserDTO userDTO = pService.getUserVO(userNo);
        ProductDetailDTO productDetail = pService.getProductDetail(buyDTO.getProductNo());
        List<AddressDTO> addressDTOList = pService.getAddressVO(userNo);
        ColorDTO colorDTO = pService.getOneColorVO(buyDTO.getColorNo());
        SizeDTO sizeDTO = pService.getOneSizeVO(buyDTO.getSizeNo());

        model.addAttribute("colorOneDTO", colorDTO);
        model.addAttribute("sizeOneDTO", sizeDTO);
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("pd", productDetail);
        model.addAttribute("addressDTOList", addressDTOList);

        return "product/payment";
    }

    @PostMapping("/payment/buy")
    public String buyProduct(@ModelAttribute("objectDTO")BuyDTO buyDTO, Model model) {
        pService.buyDTO(buyDTO);
        model.addAttribute("test", "success");
        return "redirect:/";
    }
}