package bitc.ftp.teamproject.service.admin;

import bitc.ftp.teamproject.dto.admin.AdminAnswerDTO;
import bitc.ftp.teamproject.mapper.admin.AdminAnswerMapper;
import bitc.ftp.teamproject.vo.admin.AdminAnswerVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AdminAnswerService {

  private final AdminAnswerMapper adminAnswerMapper;

  public void saveAnswer(AdminAnswerDTO adminAnswerDTO) {
    if (adminAnswerDTO == null) {
      throw new IllegalArgumentException("AdminAnswerDTO 는 null 값은 안됩니다");
    }
    AdminAnswerVO adminAnswerVO = new AdminAnswerVO();

    adminAnswerVO.setAnswerNo(adminAnswerDTO.getAnswerNo());
    adminAnswerVO.setQuestionNo(adminAnswerDTO.getQuestionNo());
    adminAnswerVO.setContent(adminAnswerDTO.getContent());
    adminAnswerVO.setUploadDate(adminAnswerDTO.getUploadDate());
    adminAnswerVO.setUserNo(adminAnswerDTO.getUserNo());

    adminAnswerMapper.insertAnswer(adminAnswerVO);
  }
}

