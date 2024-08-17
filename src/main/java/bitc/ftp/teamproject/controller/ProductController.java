package bitc.ftp.teamproject.controller;


import bitc.ftp.teamproject.dto.CategoryDTO;
import bitc.ftp.teamproject.dto.UserDTO;
import bitc.ftp.teamproject.dto.myPage.MyPageAddressDTO;
import bitc.ftp.teamproject.dto.product.*;
import bitc.ftp.teamproject.service.ProductService;

import bitc.ftp.teamproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService pService;
    private final UserService uService;

    public void categoryTest(Model model){
        List<CategoryDTO> c1DTO = pService.getAllCategory1();
        List<CategoryDTO> c2DTO = pService.getAllCategory2();
        model.addAttribute("category1List", c1DTO);
        model.addAttribute("category2List", c2DTO);
    }

    @GetMapping("/{category2No}")
    public String getProductList(@PathVariable int category2No, Model model, Principal principal) {
        List<ProductDTO> productList = pService.getAllProducts(category2No);
        List<CategoryDTO> category1List = pService.getAllCategory1();
        List<CategoryDTO> category2List = pService.getAllCategory2();
        model.addAttribute("pList", productList);
        model.addAttribute("category1List", category1List);
        model.addAttribute("category2List", category2List);

        if(principal != null){
            UserDTO dto = uService.getUserById(principal.getName());
            model.addAttribute("user", dto);
        }

        model.addAttribute("principal", principal);

        return "product/product";
    }
    @GetMapping("/addcart/{productNo}")
    public String addProduct(@PathVariable int productNo, Model model, Principal principal) {
        AddCartDTO dto = new AddCartDTO();
        UserDTO udto = null;
        dto.setProductNo(productNo);


        if(principal != null){
            udto = uService.getUserById(principal.getName());
            model.addAttribute("user", udto);
        }

        if(principal == null){
            return "redirect:/";
        }
        dto.setUserNo(udto.getUserNo());
        pService.addCartDTO(dto);
        model.addAttribute("principal", principal);

        return "redirect:/product/detail/" + productNo;
    }
    @PostMapping("/payment")
    public String payment(@ModelAttribute("objectDTO")BuyDTO buyDTO, Model model, Principal principal, @ModelAttribute("myPageAddressDTO") MyPageAddressDTO myPageAddressDTO) {
        categoryTest(model);
        UserDTO dto = null;
        if(principal != null){
            dto = uService.getUserById(principal.getName());
            model.addAttribute("user", dto);
        }

        ProductUserDTO productUserDTO = pService.getUserVO(dto.getUserNo());
        ProductDetailDTO productDetail = pService.getProductDetail(buyDTO.getProductNo());
        List<AddressDTO> addressDTOList = pService.getAddressVO(dto.getUserNo());
        ColorDTO colorDTO = pService.getOneColorVO(buyDTO.getColorNo());
        SizeDTO sizeDTO = pService.getOneSizeVO(buyDTO.getSizeNo());

        model.addAttribute("userDTO", productUserDTO);
        model.addAttribute("pd", productDetail);
        model.addAttribute("addressDTOList", addressDTOList);
        model.addAttribute("colorOneDTO", colorDTO);
        model.addAttribute("sizeOneDTO", sizeDTO);
        model.addAttribute("principal", principal);


        return "product/payment";
    }
    @PostMapping("/payment/buy")
    public String buyProduct(@ModelAttribute("objectDTO")BuyDTO buyDTO, Model model, Principal principal, RedirectAttributes ra) {
        pService.buyDTO(buyDTO);

        ra.addFlashAttribute("success", "success");

        if(principal != null){
            UserDTO dto = uService.getUserById(principal.getName());
            model.addAttribute("user", dto);
        }

        model.addAttribute("principal", principal);

        return "redirect:/";
    }
    @GetMapping("/detail/{productNo}")
    public String getProductDetail(@PathVariable int productNo, @ModelAttribute("objectDTO") BuyDTO buyDTO, Model model, Principal principal) {

        UserDTO dto=null;

        if(principal != null){
            dto = uService.getUserById(principal.getName());
            List<QuestionDTO> questionList = pService.getQuestionList(productNo, dto.getUserNo());

            List<List<AnswerDTO>> answerListList = new ArrayList<>();

			for (QuestionDTO questionDTO : questionList) {
				List<AnswerDTO> answerList = pService.getAnswerList(questionDTO.getQuestionNo());
				answerListList.add(answerList);
			}

            model.addAttribute("user", dto);
            model.addAttribute("questionList", questionList);
            model.addAttribute("answerListList", answerListList);
        }

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
        model.addAttribute("principal", principal);

        return "product/productDetail";
    }
    @PostMapping("/detail/{productNo}/questionAdd")
    public String addQustion(@PathVariable int productNo, @ModelAttribute("questionVO")QuestionDTO questionDTO, Model model, Principal principal){
        UserDTO dto = null;
        if(principal != null){
            dto = uService.getUserById(principal.getName());
            model.addAttribute("user", dto);
        }
        questionDTO.setProductNo(productNo);
        questionDTO.setUserNo(dto.getUserNo());
        pService.addQuestion(questionDTO);

        return "redirect:/product/detail/" + productNo;
    }
}