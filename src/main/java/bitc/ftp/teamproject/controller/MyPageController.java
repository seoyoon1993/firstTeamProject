package bitc.ftp.teamproject.controller;

import bitc.ftp.teamproject.dto.*;
import bitc.ftp.teamproject.dto.myPage.*;
import bitc.ftp.teamproject.service.MyPageService;
import bitc.ftp.teamproject.service.ProductService;
import bitc.ftp.teamproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
	private final MyPageService mpService;
	private final ProductService pService;
	private final UserService uService;

	@PostMapping
	public String userPage(@RequestParam int userNo, Model model, Principal principal) {
		List<CategoryDTO> c1DTO = pService.getAllCategory1();
		List<CategoryDTO> c2DTO = pService.getAllCategory2();

		model.addAttribute("category1List", c1DTO);
		model.addAttribute("category2List", c2DTO);

		if(principal != null){
			UserDTO dto = uService.getUserById(principal.getName());
			model.addAttribute("user", dto);
		}

		model.addAttribute("principal", principal);

		return "myPage/myPage";
	}

	@GetMapping("/info")
	public String myPageInfo(Model model, @ModelAttribute("user") MyPageUserDTO userDTO, Principal principal) {
		List<CategoryDTO> c1DTO = pService.getAllCategory1();
		List<CategoryDTO> c2DTO = pService.getAllCategory2();
		model.addAttribute("category1List", c1DTO);
		model.addAttribute("category2List", c2DTO);

		UserDTO dto = null;

		if(principal != null){
			dto = uService.getUserById(principal.getName());
			model.addAttribute("user", dto);
		}

		model.addAttribute("principal", principal);

		return "myPage/myPageInfo";
	}
	@PostMapping("/info/update")
	public String myPageInfoUpdate(RedirectAttributes ra, @ModelAttribute("user")MyPageUserDTO userDTO) {
		mpService.updateMyPageUser(userDTO);
		ra.addFlashAttribute("success", "success");
		return "redirect:/mypage/info";
	}

	@GetMapping("/address")
	public String myPageAddress(Model model, @ModelAttribute("user")MyPageUserDTO userDTO, @ModelAttribute("myPageAddressDTO") MyPageAddressDTO myPageAddressDTO, Principal principal) {		List<CategoryDTO> c1DTO = pService.getAllCategory1();
		List<CategoryDTO> c2DTO = pService.getAllCategory2();
		model.addAttribute("category1List", c1DTO);
		model.addAttribute("category2List", c2DTO);

		UserDTO dto = null;

		if(principal != null){
			dto = uService.getUserById(principal.getName());
			model.addAttribute("user", dto);
		}

		model.addAttribute("principal", principal);

		List<MyPageAddressDTO> dtoList = mpService.getMyPageAddress(dto.getUserNo());

		model.addAttribute("addressList", dtoList);

		return "myPage/myPageAddress";
	}
	@PostMapping("/address/register")
	public String myPageAddressRegister(Model model, @ModelAttribute("user")MyPageUserDTO userDTO, @ModelAttribute("myPageAddressDTO")MyPageAddressDTO myPageAddressDTO, Principal principal ) {
		UserDTO dto = null;

		if(principal != null){
			dto = uService.getUserById(principal.getName());
			model.addAttribute("user", dto);
		}

		model.addAttribute("principal", principal);

		myPageAddressDTO.setUserNo(dto.getUserNo());

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
	public String myPageQuestion(Principal principal, Model model, @ModelAttribute("user")MyPageUserDTO userDTO, @ModelAttribute("myPageQuestionDTO") MyPageQuestionDTO questionDTO){
		List<CategoryDTO> c1DTO = pService.getAllCategory1();
		List<CategoryDTO> c2DTO = pService.getAllCategory2();
		model.addAttribute("category1List", c1DTO);
		model.addAttribute("category2List", c2DTO);

		UserDTO dto = null;

		if(principal != null){
			dto = uService.getUserById(principal.getName());
			model.addAttribute("user", dto);
		}

		model.addAttribute("principal", principal);

		List<MyPageQuestionDTO> dtoList = mpService.getMyPageQuestion(dto.getUserNo());

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
	public String myPageCart(Principal principal, Model model, @ModelAttribute("user")MyPageUserDTO myPageUserDTO){
		categoryTest(model);

		UserDTO dto = null;

		if(principal != null){
			dto = uService.getUserById(principal.getName());
			model.addAttribute("user", dto);
		}

		model.addAttribute("principal", principal);

		myPageUserDTO.setUserNo(dto.getUserNo());
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
	public String myPageBuyList(Principal principal, Model model, @ModelAttribute("user")MyPageUserDTO myPageUserDTO ){
		categoryTest(model);

		UserDTO dto = null;

		if(principal != null){
			dto = uService.getUserById(principal.getName());
			model.addAttribute("user", dto);
		}

		model.addAttribute("principal", principal);

		myPageUserDTO.setUserNo(dto.getUserNo());

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
