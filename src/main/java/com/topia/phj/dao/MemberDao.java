package com.topia.phj.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topia.phj.vo.MemberVo;

@Repository
public class MemberDao {
	
	
	//의존성주입
	@Autowired
	private SqlSessionTemplate tpl;
	
	//로그인체크
	public MemberVo loginCheck(HashMap<String, Object> reqMap) {
		return tpl.selectOne("memberDao.loginCheck", reqMap);
	}
	public int loginIdCheck(String id) {
		return tpl.selectOne("memberDao.loginIdCheck", id);
	}
	public int loginPwCheck(String pw) {
		return tpl.selectOne("memberDao.loginCheck", pw);
	}
	
	//회원가입페이지만들기!!(공통)
	public void memberInsert(MemberVo member) {
		System.out.println("insertDao 페이지 진입");
		tpl.insert("memberDao.memberInsert", member);
		
	}
	//회원목록페이지(관리자영역)
	public List<MemberVo> memberList(HashMap<String, Object> mlist){
		System.out.println("listDao 페이지 진입");
		
		return tpl.selectList("memberDao.memberList", mlist);
	}
	
	public int memberListCount(HashMap<String, Object> reqMap) {
		System.out.println("listCount Dao 페이지 진입");
		int totalCount = tpl.selectOne("memberDao.memberListCount", reqMap);
		return totalCount;
	}
	//상세보기
	public MemberVo memberDetail(Integer idx) {
		System.out.println("detailDao 페이지 진입");
		return tpl.selectOne("memberDao.memberDetail",idx);
	}
	//수정페이지
	public void memberUpdate(MemberVo param) {
		System.out.println("updateDao 페이지 진입");
		tpl.update("memberDao.memberUpdate", param);
	}
	
	
	//삭제페이지
	public void memberDelete(Integer idx) {
		System.out.println("deleteDao 페이지 진입");
		tpl.delete("memberDao.memberDelete",idx);
	}
	
	//체크박스 
	public void memberDeleteChkList(HashMap<String, Object> reqMap) {
		System.out.println("checkDeleteDao 페이지 진입");
		tpl.delete("memberDao.memberDeleteChkList", reqMap);
	}
}
