package bitc.ftp.teamproject.service.admin;


import bitc.ftp.teamproject.dto.admin.AdminQuestionDTO;
import bitc.ftp.teamproject.mapper.admin.AdminAnswerMapper;
import bitc.ftp.teamproject.mapper.admin.AdminQuestionMapper;
import bitc.ftp.teamproject.vo.admin.AdminQuestionVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AdminQuestionService {
  private final AdminQuestionMapper adminQuestionMapper;
  private final AdminAnswerMapper adminAnswerMapper;

  //모든 질문목록을 반환
  public List<AdminQuestionDTO> getAllQuestions(int questionId) {
    List<AdminQuestionVO> voList = adminQuestionMapper.getQuestionList();
    //확인용 sout
    for(AdminQuestionVO vo : voList){
      System.out.println(vo);
    }

    List<AdminQuestionDTO> dtoList = new ArrayList<>();
    for (AdminQuestionVO vo : voList) {
      AdminQuestionDTO dto = new AdminQuestionDTO();

      dto.setQuestionNo(vo.getQuestionNo());
      dto.setUserNo(vo.getUserNo());
      dto.setContent(vo.getContent());
      dto.setUploadDate(vo.getUploadDate());
      dto.setProductNo(vo.getProductNo());
      dto.setUserName(vo.getUserName());
      dto.setProductName(vo.getProductName());

      dto.setHasAnswer(adminAnswerMapper.countAnswersByQuestionNo(vo.getQuestionNo()) > 0); // 답변 상태 설정
      dto.setAnswerContent(adminAnswerMapper.findLatestAnswerContentByQuestionNo(vo.getQuestionNo())); // 최신 답변 내용 설정

      dtoList.add(dto);
    }
    return dtoList;
  }
}
