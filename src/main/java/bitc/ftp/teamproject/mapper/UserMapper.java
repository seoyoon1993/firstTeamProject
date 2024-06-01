package bitc.ftp.teamproject.mapper;

import bitc.ftp.teamproject.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	UserVO getUserById(String id);
}
