package com.topia.phj.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.topia.phj.vo.BoardVo;

public interface BoardServ {
	
	//게시글목록보기(list)
	public List<BoardVo> boardList(HashMap<String, Object> list);
	
	public int boardListCount(HashMap<String, Object> reqMap);
	
	//게시글등록하기
	public void boardInsert(BoardVo board);
	
	//상세게시글보기
	public BoardVo boardDetail(int bno);
	
	//게시글수정하기
	public void boardUpdate(BoardVo board);
	//게시글삭제하기
	public void boardDelete(int bno);
	//선택시 제거가능
	public void boardDeleteChkList(HashMap<String, Object> param);
	//조회수
	public void boardViewCount(int bno);
}
