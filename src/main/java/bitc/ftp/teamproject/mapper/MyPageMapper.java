package bitc.ftp.teamproject.mapper;

import bitc.ftp.teamproject.vo.myPage.MyPageAddressVO;
import bitc.ftp.teamproject.vo.myPage.MyPageCartVO;
import bitc.ftp.teamproject.vo.myPage.MyPageQuestionVO;
import bitc.ftp.teamproject.vo.myPage.MyPageUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {
	MyPageUserVO getMyPageUser(int userNo);
	List<MyPageAddressVO> getMyPageAddress(int userNo);
	List<MyPageQuestionVO> getMyPageQuestion(int userNo);
	List<MyPageCartVO> getMyPageCartList(int userNo);

	void registerMyPageAddress(MyPageAddressVO myPageAddressVO);

	void updateMyPageUser(MyPageUserVO myPageUserVO);
	void updateMyPageAddress(MyPageAddressVO myPageAddressVO);
	void updateMyPageQuestion(MyPageQuestionVO myPageQuestionVO);

	void deleteMyPageAddress(int addressListNo);
	void deleteMyPageQuestion(int questionNo);
	void deleteMyPageCart(int cartNo);

}
