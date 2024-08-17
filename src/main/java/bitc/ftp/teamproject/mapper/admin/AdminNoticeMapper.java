package bitc.ftp.teamproject.mapper.admin;

import bitc.ftp.teamproject.vo.admin.AdminNoticeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminNoticeMapper {
  List<AdminNoticeVO> getNoticeList(int noticeNo);
  AdminNoticeVO getNoticeById(int noticeNo);
  void insertNotice(AdminNoticeVO notice);
  List<AdminNoticeVO> searchNotices(String keyword);
  void updateNotice(AdminNoticeVO notice);
  void deleteNotice(@Param("noticeNo") int noticeNo);
}
