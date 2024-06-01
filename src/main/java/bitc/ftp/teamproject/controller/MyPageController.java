package bitc.ftp.teamproject.controller;

import bitc.ftp.teamproject.dto.*;
import bitc.ftp.teamproject.dto.myPage.*;
import bitc.ftp.teamproject.service.MyPageService;
import bitc.ftp.teamproject.service.ProductService;
import bitc.ftp.teamproject.vo.myPage.MyPageBuyListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
	private final MyPageService mpService;
	private final ProductService pService;

	@GetMapping
	public String userPage(Model model) {
		List<CategoryDTO> c1DTO = pService.getAllCategory1();
		List<CategoryDTO> c2DTO = pService.getAllCategory2();

		model.addAttribute("category1List", c1DTO);
		model.addAttribute("category2List", c2DTO);

		return "myPage/myPage";
	}

	@GetMapping("/info")
	public String myPageInfo(Model model, @ModelAttribute("user") MyPageUserDTO userDTO) {
		List<CategoryDTO> c1DTO = pService.getAllCategory1();
		List<CategoryDTO> c2DTO = pService.getAllCategory2();
		model.addAttribute("category1List", c1DTO);
		model.addAttribute("category2List", c2DTO);

		int userNo = 2;
		MyPageUserDTO userdto = mpService.getMyPageUser(userNo);
		model.addAttribute("user", userdto);
		return "myPage/myPageInfo";
	}
	@PostMapping("/info/update")
	public String myPageInfoUpdate(Model model, @ModelAttribute("user")MyPageUserDTO userDTO) {
		mpService.updateMyPageUser(userDTO);
		return "redirect:/mypage/info";
	}

	@GetMapping("/address")
	public String myPageAddress(Model model, @ModelAttribute("user")MyPageUserDTO userDTO, @ModelAttribute("myPageAddressDTO") MyPageAddressDTO myPageAddressDTO) {
		List<CategoryDTO> c1DTO = pService.getAllCategory1();
		List<CategoryDTO> c2DTO = pService.getAllCategory2();
		model.addAttribute("category1List", c1DTO);
		model.addAttribute("category2List", c2DTO);

		userDTO.setUserNo(2);

		List<MyPageAddressDTO> dtoList = mpService.getMyPageAddress(userDTO.getUserNo());

		model.addAttribute("addressList", dtoList);

		return "myPage/myPageAddress";
	}
	@PostMapping("/address/register")
	public String myPageAddressRegister(Model model, @ModelAttribute("user")MyPageUserDTO userDTO, @ModelAttribute("myPageAddressDTO")MyPageAddressDTO myPageAddressDTO ) {
		myPageAddressDTO.setUserNo(2);
		if(myPageAddressDTO.getAddressListNo() == 0)
			mpService.insertMyPageAddress(myPageAddressDTO);
		else {
			mpService.updateMyPageAddress(myPageAddressDTO);
		}

		return "redirect:/mypage/address";
	}
	@GetMapping("/address/delete")
	public String myPageAddressDelete(Model model, @RequestParam int addressListNo){
		System.out.println(addressListNo);
		mpService.deleteMyPageAddress(addressListNo);
		return "redirect:/mypage/address";
	}

	@GetMapping("/question")
	public String myPageQuestion(Model model, @ModelAttribute("user")MyPageUserDTO userDTO, @ModelAttribute("myPageQuestionDTO") MyPageQuestionDTO questionDTO){
		List<CategoryDTO> c1DTO = pService.getAllCategory1();
		List<CategoryDTO> c2DTO = pService.getAllCategory2();
		model.addAttribute("category1List", c1DTO);
		model.addAttribute("category2List", c2DTO);

		userDTO.setUserNo(2);

		List<MyPageQuestionDTO> dtoList = mpService.getMyPageQuestion(userDTO.getUserNo());

		model.addAttribute("qDTOList", dtoList);

		return "myPage/myPageQuestion";
	}
	@PostMapping("/question/update")
	public String myPageQuestionUpdate(@ModelAttribute("myPageQuestionDTO")MyPageQuestionDTO questionDTO){
		mpService.updateMyPageQuestion(questionDTO);
		return "redirect:/mypage/question";
	}
	@GetMapping("/question/delete")
	public String myPageQuestionDelete(Model model, @RequestParam int questionNo){
		mpService.deleteMyPageQuestion(questionNo);
		return "redirect:/mypage/question";
	}

	@GetMapping("/cart")
	public String myPageCart(Model model, @ModelAttribute("user")MyPageUserDTO myPageUserDTO){
		categoryTest(model);

		myPageUserDTO.setUserNo(2);
		List<MyPageCartDTO> cartDTOList = mpService.getMyPageCart(myPageUserDTO.getUserNo());
		model.addAttribute("cartList", cartDTOList);
		return "myPage/myPageCart";
	}
	@GetMapping("/cart/delete")
	public String myPageCartDelete(Model model, @RequestParam int cartNo){
		mpService.deleteMyPageCart(cartNo);
		return "redirect:/mypage/cart";
	}

	@GetMapping("/buylist")
	public String myPageBuyList(Model model, @ModelAttribute("user")MyPageUserDTO myPageUserDTO ){
		categoryTest(model);

		myPageUserDTO.setUserNo(2);

		List<MyPageBuyListDTO> buyListDTOList = mpService.getMyPageBuyList(myPageUserDTO.getUserNo());
		model.addAttribute("buyList", buyListDTOList);
		return "myPage/myPageBuyList";
	}


	public void categoryTest(Model model){
		List<CategoryDTO> c1DTO = pService.getAllCategory1();
		List<CategoryDTO> c2DTO = pService.getAllCategory2();
		model.addAttribute("category1List", c1DTO);
		model.addAttribute("category2List", c2DTO);
	}
}
