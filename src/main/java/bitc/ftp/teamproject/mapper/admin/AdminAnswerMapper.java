package bitc.ftp.teamproject.mapper.admin;


import bitc.ftp.teamproject.vo.admin.AdminAnswerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminAnswerMapper {
  void insertAnswer(AdminAnswerVO adminAnswerVO);

  @Select("SELECT COUNT(*) FROM answer WHERE questionNo = #{questionNo}")
  int countAnswersByQuestionNo(int questionNo);

  @Select("SELECT content FROM answer WHERE questionNo = #{questionNo} ORDER BY uploadDate DESC LIMIT 1")
  String findLatestAnswerContentByQuestionNo(int questionNo); // 질문 번호로 가장 최근 답변 내용 조회
}
