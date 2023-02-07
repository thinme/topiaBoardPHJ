package com.topia.phj.vo;

import java.sql.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BoardVo {
	
	private int boardIdx;
	private String boardMemberName;
	private String boardMemberId;
	private String boardRegId;
	private String boardUpdateId;
	private String boardRegIp;
	private String boardUpdateIp;
	private Date boardRegDate;
	private Date boardUpdateDate;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private Date boardWriteDate;
	private String boardContentReply;
	private String boardNoticeYn;
	private int rowNum;
	private int viewCount;
	
	
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getBoardMemberName() {
		return boardMemberName;
	}
	public void setBoardMemberName(String boardMemberName) {
		this.boardMemberName = boardMemberName;
	}
	public String getBoardMemberId() {
		return boardMemberId;
	}
	public void setBoardMemberId(String boardMemberId) {
		this.boardMemberId = boardMemberId;
	}
	public String getBoardRegId() {
		return boardRegId;
	}
	public void setBoardRegId(String boardRegId) {
		this.boardRegId = boardRegId;
	}
	public String getBoardUpdateId() {
		return boardUpdateId;
	}
	public void setBoardUpdateId(String boardUpdateId) {
		this.boardUpdateId = boardUpdateId;
	}
	public String getBoardRegIp() {
		return boardRegIp;
	}
	public void setBoardRegIp(String boardRegIp) {
		this.boardRegIp = boardRegIp;
	}
	public String getBoardUpdateIp() {
		return boardUpdateIp;
	}
	public void setBoardUpdateIp(String boardUpdateIp) {
		this.boardUpdateIp = boardUpdateIp;
	}
	public Date getBoardRegDate() {
		return boardRegDate;
	}
	public void setBoardRegDate(Date boardRegDate) {
		this.boardRegDate = boardRegDate;
	}
	public Date getBoardUpdateDate() {
		return boardUpdateDate;
	}
	public void setBoardUpdateDate(Date boardUpdateDate) {
		this.boardUpdateDate = boardUpdateDate;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public Date getBoardWriteDate() {
		return boardWriteDate;
	}
	public void setBoardWriteDate(Date boardWriteDate) {
		this.boardWriteDate = boardWriteDate;
	}
	public String getBoardContentReply() {
		return boardContentReply;
	}
	public void setBoardContentReply(String boardContentReply) {
		this.boardContentReply = boardContentReply;
	}
	public String getBoardNoticeYn() {
		return boardNoticeYn;
	}
	public void setBoardNoticeYn(String boardNoticeYn) {
		this.boardNoticeYn = boardNoticeYn;
	}
	
	@Override
	 public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}
	
	
	

}
