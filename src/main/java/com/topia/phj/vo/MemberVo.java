package com.topia.phj.vo;

import java.sql.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class MemberVo {
	private Integer rowNum;
	private int memberIdx;
	private String memberName;
	private String memberId;
	private String memberPassword;
	private String memberEmail;
	private String memberAuth;
	private Date   memberRegDate;
	private Date   memberUpdateDate;
	private String memberRegIp;
	private String memberUpdateIp;
	private String memberRegId;
	private String memberUpdateId;
	private String resultYn;
	private String memberZipcode;
	private String memberAdress;
	private String memberDetailAdress;
	private String memberExtraAdress;
	
	
	
	public String getMemberExtraAdress() {
		return memberExtraAdress;
	}
	public void setMemberExtraAdress(String memberExtraAdress) {
		this.memberExtraAdress = memberExtraAdress;
	}
	public String getMemberZipcode() {
		return memberZipcode;
	}
	public void setMemberZipcode(String memberZipcode) {
		this.memberZipcode = memberZipcode;
	}
	public String getMemberAdress() {
		return memberAdress;
	}
	public void setMemberAdress(String memberAdress) {
		this.memberAdress = memberAdress;
	}
	public String getMemberDetailAdress() {
		return memberDetailAdress;
	}
	public void setMemberDetailAdress(String memberDetailAdress) {
		this.memberDetailAdress = memberDetailAdress;
	}
	public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	public String getMemberRegId() {
		return memberRegId;
	}
	public void setMemberRegId(String memberRegId) {
		this.memberRegId = memberRegId;
	}
	public String getMemberUpdateId() {
		return memberUpdateId;
	}
	public void setMemberUpdateId(String memberUpdateId) {
		this.memberUpdateId = memberUpdateId;
	}
	public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberAuth() {
		return memberAuth;
	}
	public void setMemberAuth(String memberAuth) {
		this.memberAuth = memberAuth;
	}
	public Date getMemberRegDate() {
		return memberRegDate;
	}
	public void setMemberRegDate(Date memberRegDate) {
		this.memberRegDate = memberRegDate;
	}
	public Date getMemberUpdateDate() {
		return memberUpdateDate;
	}
	public void setMemberUpdateDate(Date memberUpdateDate) {
		this.memberUpdateDate = memberUpdateDate;
	}
	public String getMemberRegIp() {
		return memberRegIp;
	}
	public void setMemberRegIp(String memberRegIp) {
		this.memberRegIp = memberRegIp;
	}
	public String getMemberUpdateIp() {
		return memberUpdateIp;
	}
	public void setMemberUpdateIp(String memberUpdateIp) {
		this.memberUpdateIp = memberUpdateIp;
	}
	public String getResultYn() {
		return resultYn;
	}
	public void setResultYn(String resultYn) {
		this.resultYn = resultYn;
	}
	
	@Override
	 public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}

}
