package bitc.ftp.teamproject.controller.admin;



import bitc.ftp.teamproject.dto.admin.AdminUserDTO;
import bitc.ftp.teamproject.service.admin.AdminUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/admin/users")
public class AdminUserController {
  private AdminUserService adminUserService;

  @GetMapping("/{userNo}")
  public String getUsers(@PathVariable int userNo, Model model) {
    List<AdminUserDTO> userList = adminUserService.getAllUsers(userNo);
    model.addAttribute("userList", userList);
    return "admin/users";
  }

  @GetMapping("/details/{userNo}")
  @ResponseBody
  public AdminUserDTO getUserDetails(@PathVariable int userNo) {
    return adminUserService.getUserDetails(userNo);
  }

  @DeleteMapping("/delete/{userNo}")
  @ResponseBody
  public ResponseEntity<Void> deleteUser(@PathVariable int userNo) {
    adminUserService.deleteUser(userNo);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/search")
  public String searchUsers(@RequestParam String keyword, Model model) {
    List<AdminUserDTO> userList = adminUserService.searchUsers(keyword);
    model.addAttribute("userList", userList);
    return "admin/users";
  }
}
