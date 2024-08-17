package bitc.ftp.teamproject.controller.admin;

import bitc.ftp.teamproject.dto.UserDTO;
import bitc.ftp.teamproject.dto.admin.AdminNoticeDTO;
import bitc.ftp.teamproject.service.UserService;
import bitc.ftp.teamproject.service.admin.AdminNoticeService;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/admin/notices")
public class AdminNoticeController {
  private final AdminNoticeService adminNoticeService;
  private final UserService userService;

  @GetMapping("/{noticeNo}")
  public String getNotices(@PathVariable int noticeNo, Model model) {
    List<AdminNoticeDTO> noticeList = adminNoticeService.getAllNotices(noticeNo);
    model.addAttribute("noticeList", noticeList);
    return "admin/notices";
  }

  @GetMapping("/details/{noticeNo}")
  public String getNoticeDetails(@PathVariable int noticeNo, Model model) {
    AdminNoticeDTO notice = adminNoticeService.getNoticeById(noticeNo);
    model.addAttribute("notice", notice);
    return "admin/noticeDetails";
  }

  @GetMapping("/new")
  public String createNoticeForm(Model model,Principal principal) {
    String username = principal.getName();
    UserDTO user = userService.getUserById(username);

    model.addAttribute("notice", new AdminNoticeDTO());
    model.addAttribute("userNo", user.getUserNo());
    model.addAttribute("name", user.getName());
    return "admin/createNotice";
  }

  @PostMapping("/new")
  public String createNotice(@ModelAttribute AdminNoticeDTO notice,Principal principal) {

    String username = principal.getName();
    UserDTO user = userService.getUserById(username);
    notice.setUserNo(user.getUserNo());

    adminNoticeService.createNotice(notice);
    return "redirect:/admin/notices/1";
  }

  @GetMapping("/search")
  public String searchNotices(@RequestParam("keyword") String keyword, Model model) {
    List<AdminNoticeDTO> noticeList = adminNoticeService.searchNotices(keyword);
    model.addAttribute("noticeList", noticeList);
    return "admin/notices";
  }

  @PostMapping("/edit")
  public String editNotice(@ModelAttribute AdminNoticeDTO notice) {
    adminNoticeService.updateNotice(notice);
    return "redirect:/admin/notices/details/" + notice.getNoticeNo();
  }
  @PostMapping("/delete")
  public String deleteProduct(@RequestParam int noticeNo) {
    adminNoticeService.deleteNotice(noticeNo);
    return "redirect:/admin/products";
  }
}
