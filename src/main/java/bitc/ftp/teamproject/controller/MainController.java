package bitc.ftp.teamproject.controller;

import bitc.ftp.teamproject.dto.CategoryDTO;
import bitc.ftp.teamproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final ProductService pService;

	@GetMapping("")
	public String main(Model model){
		List<CategoryDTO> c1DTO = pService.getAllCategory1();
		List<CategoryDTO> c2DTO = pService.getAllCategory2();
		model.addAttribute("category1List", c1DTO);
		model.addAttribute("category2List", c2DTO);
		return "nono";
	}

	@GetMapping("/register")
	public String register(Model model){
		List<CategoryDTO> c1DTO = pService.getAllCategory1();
		List<CategoryDTO> c2DTO = pService.getAllCategory2();
		model.addAttribute("category1List", c1DTO);
		model.addAttribute("category2List", c2DTO);
		return "register";
	}
}
