package bitc.ftp.teamproject.service;

import bitc.ftp.teamproject.dto.UserDTO;
import bitc.ftp.teamproject.mapper.UserMapper;
import bitc.ftp.teamproject.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserMapper uMapper;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		UserVO user = uMapper.getUserById(id);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(
				user.getId(),
				user.getPw(),
				new ArrayList<>()
		);
	}

	public UserDTO getUserById(String id){
		UserVO vo =  uMapper.getUserById(id);
		UserDTO dto = new UserDTO();
		dto.setId(vo.getId());
		dto.setEmail(vo.getEmail());
		dto.setName(vo.getName());
		dto.setBirth(vo.getBirth());
		dto.setGender(vo.getGender());
		dto.setRefundAccount(vo.getRefundAccount());
		dto.setTel(vo.getTel());
		dto.setUserNo(vo.getUserNo());
		dto.setUserGradeNo(vo.getUserGradeNo());
		return dto;
	}
}
