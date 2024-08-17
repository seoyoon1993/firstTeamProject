package bitc.ftp.teamproject.controller.admin;

import bitc.ftp.teamproject.dto.admin.AdminQuestionDTO;
import bitc.ftp.teamproject.service.admin.AdminAnswerService;
import bitc.ftp.teamproject.service.admin.AdminQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping("/admin/questions")
public class AdminQuestionController {

  private final AdminQuestionService adminQuestionService;
  private final AdminAnswerService adminAnswerService;

  //  메인화면을 만들어야 temp를 사용
  @GetMapping
  public String questionMain(){
    return "admin/questions";
  }

  @GetMapping("/{questionNo}")
  public String getQuestion(@PathVariable int questionNo, Model model) {
    List<AdminQuestionDTO> questionList = adminQuestionService.getAllQuestions(questionNo);
    model.addAttribute("questionList", questionList);
    return "admin/questions";
  }

}
