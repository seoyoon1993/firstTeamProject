package bitc.ftp.teamproject.service.admin;


import bitc.ftp.teamproject.dto.admin.AdminUserDTO;
import bitc.ftp.teamproject.mapper.admin.AdminUserMapper;
import bitc.ftp.teamproject.vo.admin.AdminUserVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminUserService {
  private final AdminUserMapper adminUserMapper;

  public List<AdminUserDTO> getAllUsers(int userNo) {
    List<AdminUserVO> voList = adminUserMapper.getUserList(userNo);
    return convertToDTOList(voList);
  }

  public AdminUserDTO getUserDetails(int userNo) {
    AdminUserVO vo = adminUserMapper.getUserDetails(userNo);
    return convertToDTO(vo);
  }

  public void deleteUser(int userNo) {
    adminUserMapper.deleteUser(userNo);
  }

  public List<AdminUserDTO> searchUsers(String keyword) {
    List<AdminUserVO> voList = adminUserMapper.searchUsers(keyword);
    return convertToDTOList(voList);
  }

  private List<AdminUserDTO> convertToDTOList(List<AdminUserVO> voList) {
    List<AdminUserDTO> dtoList = new ArrayList<>();
    for (AdminUserVO vo : voList) {
      dtoList.add(convertToDTO(vo));
    }
    return dtoList;
  }

  private AdminUserDTO convertToDTO(AdminUserVO vo) {
    if (vo == null) return null;
    AdminUserDTO dto = new AdminUserDTO();
    dto.setUserNo(vo.getUserNo());
    dto.setName(vo.getName());
    dto.setBirth(vo.getBirth());
    dto.setTel(vo.getTel());
    dto.setGender(vo.getGender());
    dto.setEmail(vo.getEmail());
    dto.setId(vo.getId());
    dto.setPw(vo.getPw());
    dto.setRefundAccount(vo.getRefundAccount());
    dto.setUserGradeNo(vo.getUserGradeNo());
    dto.setGrade(vo.getGrade());
    return dto;
  }
}
