package com.topia.phj.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.topia.phj.dao.BoardDao;
import com.topia.phj.vo.BoardVo;

@Service
public class BoardServImpl implements BoardServ {
	//의존성주입(dao를 받기)
	@Autowired
	private BoardDao boardDao;
	
	//게시글목록보기 override받아서 메소드구현해주어야함
	@Override
	public List<BoardVo> boardList(HashMap<String, Object> list) {
		System.out.println("boardListServ 페이지 진입");
		return	boardDao.boardList(list);
	}
	
	@Override
	public int boardListCount(HashMap<String, Object> reqMap ){
		System.out.println("boardListCountServ 페이지 진입");
		
		int totalCount = boardDao.boardListCount(reqMap);
	
		return totalCount;
	}
	
	//게시글등록하기
	@Override
	public void boardInsert(BoardVo board) {
		System.out.println("insertServ 페이지 진입");
		boardDao.boardInsert(board);
		
	}
	//상세게시글보기
	@Override
	public BoardVo boardDetail(int bno) {
		System.out.println("detailServ 페이지 진입");
		BoardVo detail = boardDao.boardDetail(bno);
		return detail;
	}
	//게시글수정하기
	@Override
	public void boardUpdate(BoardVo board) {
		System.out.println("updateServ 페이지 진입");
		boardDao.boardUpdate(board);
		
	}
	//게시글삭제하기
	@Override
	public void boardDelete(int bno) {
		System.out.println("deleteServ 페이지 진입");
		 boardDao.boardDelete(bno);
		
	}

	@Override
	public void boardDeleteChkList(HashMap<String, Object> param) {
		System.out.println("boardDeleteChkList 페이지 진입");
		boardDao.boardDeleteChkList(param);
		
	}

	@Override
	public void boardViewCount(int bno) {
		System.out.println("boardViewCountServ 페이지 진입");
		boardDao.boardViewCount(bno);
	}
	

}
