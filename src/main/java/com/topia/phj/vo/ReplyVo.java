package com.topia.phj.vo;

import java.sql.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ReplyVo {
	
	
	private int perPageNum;
	private String searchCondition;
	private String boardSearchWord;
	
	private int rno;
	private int bno;
	private String replyText;
	private String replyer;
	private String name;
	private Date regDate;
	private Date updateDate;
	private String writer;
	
	
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getBoardSearchWord() {
		return boardSearchWord;
	}
	public void setBoardSearchWord(String boardSearchWord) {
		this.boardSearchWord = boardSearchWord;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getReplyText() {
		return replyText;
	}
	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	@Override
	 public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}
	
}
