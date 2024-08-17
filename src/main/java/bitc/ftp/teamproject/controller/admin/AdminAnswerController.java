package bitc.ftp.teamproject.controller.admin;


import bitc.ftp.teamproject.dto.admin.AdminAnswerDTO;
import bitc.ftp.teamproject.service.admin.AdminAnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/admin/answers")
public class AdminAnswerController {

  private final AdminAnswerService adminAnswerService;

  @PostMapping
  public ResponseEntity<String> saveAnswer(@RequestBody AdminAnswerDTO adminAnswerDTO) {
    adminAnswerService.saveAnswer(adminAnswerDTO);
    return ResponseEntity.ok("Answer 가 성공적으로 저장되었습니다");
  }
}
