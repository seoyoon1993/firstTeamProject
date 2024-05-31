package bitc.ftp.teamproject.controller;


import bitc.ftp.teamproject.dto.CategoryDTO;
import bitc.ftp.teamproject.dto.Product.ProductDTO;
import bitc.ftp.teamproject.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService pService;

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
}