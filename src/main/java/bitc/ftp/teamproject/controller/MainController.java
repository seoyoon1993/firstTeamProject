package bitc.ftp.teamproject.controller;

import bitc.ftp.teamproject.dto.CategoryDTO;
import bitc.ftp.teamproject.dto.UserDTO;
import bitc.ftp.teamproject.dto.admin.AdminNoticeDTO;
import bitc.ftp.teamproject.service.ProductService;
import bitc.ftp.teamproject.service.UserService;
import bitc.ftp.teamproject.service.admin.AdminNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final ProductService pService;
	private final UserService uService;
	private final AdminNoticeService anService;
	private final PasswordEncoder passwordEncoder;

	@GetMapping("")
	public String main(Model model, Principal principal){
		List<CategoryDTO> c1DTO = pService.getAllCategory1();
		List<CategoryDTO> c2DTO = pService.getAllCategory2();
		model.addAttribute("category1List", c1DTO);
		model.addAttribute("category2List", c2DTO);

		if(principal != null){
			UserDTO dto = uService.getUserById(principal.getName());
			model.addAttribute("user", dto);
		}

		model.addAttribute("principal", principal);
		return "nono";
	}

	@GetMapping("/register")
	public String register(Model model, @ModelAttribute("user")UserDTO userDTO){
		List<CategoryDTO> c1DTO = pService.getAllCategory1();
		List<CategoryDTO> c2DTO = pService.getAllCategory2();
		model.addAttribute("category1List", c1DTO);
		model.addAttribute("category2List", c2DTO);
		return "register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("user")UserDTO userDTO, Model model, RedirectAttributes ra){
		userDTO.setPw(passwordEncoder.encode(userDTO.getPw()));
		uService.addRegister(userDTO);
		ra.addFlashAttribute("register", "success");
		return "redirect:/";
	}

	@GetMapping("/notice")
	public String notice(@ModelAttribute("notice")AdminNoticeDTO adminNoticeDTO, Model model){
		List<CategoryDTO> c1DTO = pService.getAllCategory1();
		List<CategoryDTO> c2DTO = pService.getAllCategory2();
		List<AdminNoticeDTO> noticeDTO = anService.getAllNotices(0);
		model.addAttribute("category1List", c1DTO);
		model.addAttribute("category2List", c2DTO);
		model.addAttribute("noticeList", noticeDTO);

		return "notice";
	}
}
