package bitc.ftp.teamproject.service.admin;


import bitc.ftp.teamproject.dto.admin.AdminNoticeDTO;
import bitc.ftp.teamproject.mapper.admin.AdminNoticeMapper;
import bitc.ftp.teamproject.vo.admin.AdminNoticeVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class AdminNoticeService {
  private final AdminNoticeMapper adminNoticeMapper;

  private AdminNoticeDTO convertToDTO(AdminNoticeVO vo) {
    return new AdminNoticeDTO(vo.getNoticeNo(), vo.getContent(), vo.getStartDate(), vo.getEndDate(), vo.getUploadDate(), vo.getTitle(), vo.getUserNo(), vo.getName());
  }

  private AdminNoticeVO convertToVO(AdminNoticeDTO dto) {
    return new AdminNoticeVO(dto.getNoticeNo(), dto.getContent(), dto.getStartDate(), dto.getEndDate(), dto.getUploadDate(), dto.getTitle(), dto.getUserNo(), dto.getName());
  }

  public List<AdminNoticeDTO> getAllNotices(int noticeNo) {
    List<AdminNoticeVO> voList = adminNoticeMapper.getNoticeList(noticeNo);
    List<AdminNoticeDTO> dtoList = new ArrayList<>();
    for (AdminNoticeVO vo : voList) {
      dtoList.add(convertToDTO(vo));
    }
    return dtoList;
  }

  public AdminNoticeDTO getNoticeById(int noticeNo) {
    AdminNoticeVO vo = adminNoticeMapper.getNoticeById(noticeNo);
    return convertToDTO(vo);
  }

  public void createNotice(AdminNoticeDTO notice) {
    AdminNoticeVO vo = convertToVO(notice);
    adminNoticeMapper.insertNotice(vo);
  }

  public List<AdminNoticeDTO> searchNotices(String keyword) {
    List<AdminNoticeVO> voList = adminNoticeMapper.searchNotices(keyword);
    List<AdminNoticeDTO> dtoList = new ArrayList<>();
    for (AdminNoticeVO vo : voList) {
      dtoList.add(convertToDTO(vo));
    }
    return dtoList;
  }

  public void updateNotice(AdminNoticeDTO notice) {
    notice.setUploadDate(new Date());
    AdminNoticeVO vo = convertToVO(notice);
    adminNoticeMapper.updateNotice(vo);
  }

  public void deleteNotice(int noticeNo) {
    adminNoticeMapper.deleteNotice(noticeNo);
  }
}
