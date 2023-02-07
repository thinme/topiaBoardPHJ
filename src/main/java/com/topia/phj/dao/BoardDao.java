package com.topia.phj.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topia.phj.vo.BoardVo;

@Repository
public class BoardDao {
	
	//의존성주입
	@Autowired
	private SqlSessionTemplate tpl;
	
	//게시글목록보기
	public List<BoardVo> boardList(HashMap<String, Object> list){
		System.out.println("BoardlistCountDao 페이지 진입");
		return tpl.selectList("boardDao.boardList", list);
	}
	
	public int boardListCount(HashMap<String, Object> reqMap) {
		System.out.println("BoardlistCountDao 페이지 진입");
		int totalCount =tpl.selectOne("boardDao.boardListCount", reqMap);
		return totalCount;
	}	
	
	//게시글등록하기
	public void boardInsert(BoardVo board){
		System.out.println("insertDao 페이지 진입");
		tpl.insert("boardDao.boardInsert", board);
	}
	
	//상세게시글보기
	public BoardVo boardDetail(int bno) {
		System.out.println("detailDao 페이지 진입");
		return tpl.selectOne("boardDao.boardDetail", bno);
	}
	
	//게시글수정하기
	public void boardUpdate(BoardVo board) {
		System.out.println("updateDao 페이지 진입");
		 tpl.update("boardDao.boardUpdate", board);
	}
	//게시글삭제하기
	public void boardDelete(int bno) {
		System.out.println("deleteDao 페이지 진입");
		 tpl.delete("boardDao.boardDelete", bno);
	}
	//선택박스만들어서 삭제하기
	public void boardDeleteChkList(HashMap<String, Object> param) {
		System.out.println("deleteChkListDao 페이지 진입");
		tpl.delete("boardDao.boardDeleteChkList", param);
	}
	//조회수 증가
	public void boardViewCount(int bno) {
		System.out.println("boardViewCountDao 페이지 진입");
		tpl.update("boardDao.boardViewCount", bno);
	}
}
