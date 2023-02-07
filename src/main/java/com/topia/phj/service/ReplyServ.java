package com.topia.phj.service;

import java.util.HashMap;
import java.util.List;

import com.topia.phj.vo.ReplyVo;

public interface ReplyServ {

	//댓글리스트
	public List<ReplyVo> replyList(HashMap<String, Object> reqMap);
	
	//댓글입력
	public void insertReply(ReplyVo reple);
	
	//댓글수정
	public int replyUpdate(ReplyVo reple);
		
	//댓글삭제
	public void replyDelete(int rno);
}
