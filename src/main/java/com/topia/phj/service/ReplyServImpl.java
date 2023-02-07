package com.topia.phj.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topia.phj.dao.ReplyDao;
import com.topia.phj.vo.ReplyVo;

@Service
public class ReplyServImpl implements ReplyServ {
	
	@Autowired
	private ReplyDao replyDao;

	@Override
	public List<ReplyVo> replyList(HashMap<String, Object> reqMap) {
		System.out.println("*************댓글리스트 목록서비스보기**********************");
		return replyDao.replyList(reqMap);
	}

	@Override
	public void insertReply(ReplyVo reple) {
		System.out.println("*************댓글리스트 목록입력서비스보기**********************");
		replyDao.insertReply(reple);
		
	}

	//댓글수정
	@Override
	public int replyUpdate(ReplyVo reple) {
		System.out.println("*************댓글리스트 목록수정서비스보기**********************");
		return replyDao.replyUpdate(reple);
	}
	//댓글삭제
	@Override
	public void replyDelete(int rno) {
		System.out.println("*************댓글리스트 목록삭제보기**********************");
			replyDao.replyDelete (rno);
	}

}
