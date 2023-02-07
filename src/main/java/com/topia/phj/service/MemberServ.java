package com.topia.phj.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.topia.phj.vo.MemberVo;

public interface MemberServ {
	
	//로그인체크서비스
	MemberVo loginCheck(HashMap<String, Object> reqMap) throws SQLException, IOException;
	
	//id중복체크
	public int loginIdCheck(String id);

	//password일치체크
	public int loginPwCheck(String pw);
	
	//회원가입서비스
	public void memberInsert(MemberVo member);
	
	//회원목록서비스
	public List<MemberVo> memberList(HashMap<String, Object> mlist);
	
	//페이지와 검색어
	public int memberListCount(HashMap<String, Object> reqMap);
	
	//상세보기
	public MemberVo memberDetail(Integer idx);
	
	//수정하기
	public void memberUpdate(MemberVo param);
	
	//삭제하기
	public void memberDelete(Integer idx);
	
	//체크박스
	public void memberDeleteChkList(HashMap<String, Object> reqMap);
}
