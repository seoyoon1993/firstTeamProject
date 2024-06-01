package bitc.ftp.teamproject.service;

import bitc.ftp.teamproject.dto.myPage.*;
import bitc.ftp.teamproject.mapper.MyPageMapper;
import bitc.ftp.teamproject.util.DateTimeUtil;
import bitc.ftp.teamproject.vo.myPage.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {
	private final MyPageMapper mpMapper;

	public MyPageUserDTO getMyPageUser(int userNo) {
		MyPageUserVO vo = mpMapper.getMyPageUser(userNo);

		MyPageUserDTO dto = new MyPageUserDTO();
		dto.setUserNo(vo.getUserNo());
		dto.setId(vo.getId());
		dto.setName(vo.getName());
		dto.setBirth(vo.getBirth());
		dto.setTel(vo.getTel());
		dto.setGender(vo.getGender());
		dto.setEmail(vo.getEmail());
		dto.setRefundAccount(vo.getRefundAccount());

		return dto;
	}
	public List<MyPageAddressDTO> getMyPageAddress(int userNo) {
		List<MyPageAddressVO> voList = mpMapper.getMyPageAddress(userNo);
		List<MyPageAddressDTO> dtoList = new ArrayList<>();
		for (MyPageAddressVO vo : voList) {
			MyPageAddressDTO dto = new MyPageAddressDTO();
			dto.setUserNo(vo.getUserNo());
			dto.setAddressListNo(vo.getAddressListNo());
			dto.setAddressName(vo.getAddressName());
			dto.setAddress(vo.getAddress());
			dtoList.add(dto);
		}
		return dtoList;
	}
	public List<MyPageQuestionDTO> getMyPageQuestion(int userNo){
		List<MyPageQuestionVO> voList = mpMapper.getMyPageQuestion(userNo);
		List<MyPageQuestionDTO> dtoList = new ArrayList<>();
		for (MyPageQuestionVO vo : voList) {
			MyPageQuestionDTO dto = new MyPageQuestionDTO();
			dto.setUserNo(vo.getUserNo());
			dto.setQuestionNo(vo.getQuestionNo());
			dto.setQuestionContent(vo.getQuestionContent());
			dto.setQuestionUploadDate(DateTimeUtil.convertDateToString(vo.getQuestionUploadDate()));
			dto.setProductNo(vo.getProductNo());
			dto.setProductName(vo.getProductName());
			dtoList.add(dto);
		}
		return dtoList;
	}
	public List<MyPageCartDTO> getMyPageCart(int userNo){
		List<MyPageCartVO> voList = mpMapper.getMyPageCartList(userNo);
		List<MyPageCartDTO> dtoList = new ArrayList<>();
		for (MyPageCartVO vo : voList) {
			MyPageCartDTO dto = new MyPageCartDTO();
			dto.setUserNo(vo.getUserNo());
			dto.setCartNo(vo.getCartNo());
			dto.setProductNo(vo.getProductNo());
			dto.setProductName(vo.getProductName());
			dto.setProductPrice(vo.getProductPrice());
			dtoList.add(dto);
		}
		return dtoList;
	}
	public List<MyPageBuyListDTO> getMyPageBuyList(int userNo){
		List<MyPageBuyListVO> voList = mpMapper.getMyPageBuyList(userNo);
		List<MyPageBuyListDTO> dtoList = new ArrayList<>();
		for (MyPageBuyListVO vo : voList) {
			MyPageBuyListDTO dto = new MyPageBuyListDTO();
			dto.setBuylistNo(vo.getBuylistNo());
			dto.setProductNo(vo.getProductNo());
			dto.setBuyDate(DateTimeUtil.convertDateToString(vo.getBuyDate()));
			dto.setAddress(vo.getAddress());
			dto.setProductPrice(vo.getProductPrice());
			dto.setProductName(vo.getProductName());
			dto.setProductColor(vo.getProductColor());
			dto.setProductSize(vo.getProductSize());
			dto.setProductPhoto(vo.getProductPhoto());
			dtoList.add(dto);
		}
		return dtoList;
	}

	public void insertMyPageAddress(MyPageAddressDTO dto) {
		MyPageAddressVO vo = new MyPageAddressVO();
		vo.setUserNo(dto.getUserNo());
		vo.setAddressName(dto.getAddressName());
		vo.setAddress(dto.getAddress());
		mpMapper.registerMyPageAddress(vo);
	}

	public void updateMyPageUser(MyPageUserDTO dto) {
		MyPageUserVO vo = new MyPageUserVO();
		vo.setUserNo(dto.getUserNo());
		vo.setId(dto.getId());
		vo.setName(dto.getName());
		vo.setBirth(dto.getBirth());
		vo.setTel(dto.getTel());
		vo.setGender(dto.getGender());
		vo.setEmail(dto.getEmail());
		vo.setRefundAccount(dto.getRefundAccount());
		mpMapper.updateMyPageUser(vo);
	}
	public void updateMyPageAddress(MyPageAddressDTO dto){
		MyPageAddressVO vo = new MyPageAddressVO();
		vo.setAddressListNo(dto.getAddressListNo());
		vo.setAddressName(dto.getAddressName());
		vo.setAddress(dto.getAddress());
		mpMapper.updateMyPageAddress(vo);
	}
	public void updateMyPageQuestion(MyPageQuestionDTO dto){
		MyPageQuestionVO vo = new MyPageQuestionVO();
		vo.setQuestionNo(dto.getQuestionNo());
		vo.setQuestionContent(dto.getQuestionContent());
		mpMapper.updateMyPageQuestion(vo);
	}

	public void deleteMyPageAddress(int addressListNo){
		mpMapper.deleteMyPageAddress(addressListNo);
	}
	public void deleteMyPageQuestion(int questionNo){
		mpMapper.deleteMyPageQuestion(questionNo);
	}
	public void deleteMyPageCart(int cartNo){
		mpMapper.deleteMyPageCart(cartNo);
	}




}
