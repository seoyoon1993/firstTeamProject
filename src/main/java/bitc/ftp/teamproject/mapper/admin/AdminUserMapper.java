package bitc.ftp.teamproject.mapper.admin;


import bitc.ftp.teamproject.vo.admin.AdminUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminUserMapper {
  List<AdminUserVO> getUserList(int userNo);
  void deleteUser(int userNo);
  AdminUserVO getUserDetails(int userNo);
  List<AdminUserVO> searchUsers(String keyword);
}

