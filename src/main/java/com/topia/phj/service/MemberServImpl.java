package com.topia.phj.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topia.phj.dao.MemberDao;
import com.topia.phj.vo.MemberVo;

@Service
public class MemberServImpl implements MemberServ{
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public MemberVo loginCheck(HashMap<String, Object> reqMap) {
		MemberVo memberVo = memberDao.loginCheck(reqMap);
		
	

		// 회원이 존재하지 않습니다.
		if(memberVo == null) {
			memberVo = new MemberVo();
			memberVo.setResultYn("N");
		}
		// 비밀번호가 일치하지 않습니다.
		else if(!memberVo.getMemberPassword().equals(reqMap.get("pw"))) {
			memberVo.setResultYn("N");
		}
		// 성공
		else {			
			memberVo.setResultYn("Y");
		}
		return memberVo;
	}

	@Override
	public int loginIdCheck(String id) {
		
		return memberDao.loginIdCheck(id);
	}
	
	@Override
	public int loginPwCheck(String pw) {
		
		return memberDao.loginPwCheck(pw);
	}
	
	@Override
	public void memberInsert(MemberVo member) {
		System.out.println("insertServ 페이지 진입");
		
		 memberDao.memberInsert(member);
	}

	@Override
	public List<MemberVo> memberList(HashMap<String, Object> mlist) {
		System.out.println("memberListServ 페이지 진입");
		
		return memberDao.memberList(mlist);
	}

	@Override
	public MemberVo memberDetail(Integer idx) {
		System.out.println("memberDetailServ 페이지 진입");
		return memberDao.memberDetail(idx);
	}

	@Override
	public void memberUpdate(MemberVo param) {
		System.out.println("memberUpdateServ 페이지 진입");
		memberDao.memberUpdate(param);
	}

	@Override
	public void memberDelete(Integer idx) {
		System.out.println("memberDeleteServ 페이지 진입");
		memberDao.memberDelete(idx);
	}

	@Override
	public void memberDeleteChkList(HashMap<String, Object> reqMap) {
		System.out.println("memberDeleteCheckServ 페이지 진입");
		memberDao.memberDeleteChkList(reqMap);
	}

	@Override
	public int memberListCount(HashMap<String, Object> reqMap) {
		System.out.println("memberListCountServ 페이지 진입");
		
		int totalCount = memberDao.memberListCount(reqMap);
		return totalCount;
	}


	
	
	
	
}
