package bitc.ftp.teamproject.mapper.admin;


import bitc.ftp.teamproject.vo.admin.AdminQuestionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AdminQuestionMapper {
    List<AdminQuestionVO> getQuestionList();
}
