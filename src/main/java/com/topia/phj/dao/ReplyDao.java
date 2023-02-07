package com.topia.phj.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topia.phj.vo.ReplyVo;

@Repository
public class ReplyDao {
	
	@Autowired
	private SqlSessionTemplate tpl;
	
	//댓글 리스트
	public List<ReplyVo> replyList(HashMap<String, Object> reqMap) {
		System.out.println("*************댓글리스트 목록보기**********************");
		return tpl.selectList("replyDao.replyList", reqMap);
	}
	
	//댓글입력
	public void insertReply(ReplyVo reple) {
		System.out.println("*************댓글리스트 입력보기**********************");
		 tpl.insert("replyDao.insertReply", reple);
	}
	
	//댓글수정
	public int replyUpdate(ReplyVo reple) {
		System.out.println("*************댓글리스트 수정보기**********************");
		return tpl.update("replyDao.replyUpdate", reple);
	}
	//댓글삭제
	public void replyDelete(int rno) {
		System.out.println("*************댓글리스트 삭제보기**********************");
		tpl.delete("replyDao.replyDelete", rno);
	}
}
