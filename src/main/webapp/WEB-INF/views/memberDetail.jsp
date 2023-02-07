<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="./resources/compnent/jquery-3.3.1.min.js"></script>
	<script src="./resources/compnent/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
	<script src="./resources/compnent/jquery-loading-master/dist/jquery.loading.min.js"></script>
	<script src="./resources/compnent/jqueryPrint/jqueryPrint.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="./resources/js/member.js" charset="UTF-8"></script> 
	<link rel="stylesheet" type="text/css" href="./resources/css/board.css">
	<link rel="stylesheet" href="./resources/compnent/bootstrap-5.3.0/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Detail title here</title>
<style>
imgfile{
	width:50px;
	height:50px;
	cursor:pointer;
	display: inline;
	float:right;
	margin-right:10px;
}

table{
	margin-top:40px;
	
}

.title{
	font-size:20px;
	font-weight: bolder;
}

table.table2{
        border-collapse: separate;
        border-spacing: 1px;
        text-align: left;
        line-height: 1.5;
        border-top: 1px solid #ccc;
        margin : 20px 10px;
        width:100%;
}
table.table2 tr {
         width: 500px;
         padding: 10px;
        font-weight: bold;
        vertical-align: top;
        border-bottom: 1px solid #ccc;
}
table.table2 td {
         width: 100px;
         padding: 10px;
         vertical-align: top;
         border-bottom: 1px solid #ccc;
}
</style>
</head>
<body>
<div class="logo">
	<a onclick="goBoard()" ><img src="./resources/img/logo.png"></a>	
		<div class="loginstatus">
		<c:choose>
	 		<c:when test="${sessionId eq null}">
					<button onclick="goLogin()" type="button" class="login" style=" margin-right:10px;">로그인</button>
					<button onclick="goInsert()" type="button" class="login" style="margin-right:5px; ">회원가입</button>
			</c:when> 
		 	<c:when test="${sessionId ne null}">
				<a onclick="dropMenu()"><img src="./resources/img/white.png" class="imgfile"/></a>
 				<p style="color:white; text-align:center;">${sessionName} 님</p>
				<div id="dropMenu" style="display:none;">
					<c:choose>
						<c:when test="${sessionAuth eq 'master' or sessionAuth eq 'manager'}">
							<a onclick="goMemberList()">회원관리</a>
						</c:when>
						<c:when test="${sessionAuth eq 'member'}">
							<a onclick="goMyPage(${sessionIdx})">내 정보</a>
						</c:when>
					</c:choose>
					<hr>
					<a href="./logout" style="text-decoration:none; color:black;">로그아웃</a>
				</div>
			</c:when>
		</c:choose>	
		</div>
</div>

<form id="memberdetail" method="post">	
	<input type="hidden" id="memberIdx" name="memberIdx" value="${memberVo.memberIdx}"/>
        <table  style="padding-top:50px"  align = center width=840 border=0 cellpadding=2 >
                <tr>
                <td class="title" height=40 align= center bgcolor=#ccc ><font color=whit> 회원 상세정보</font></td>
                </tr>
                <tr>
                <td bgcolor=white>
                <table class = "table2">
                		<tr>
				<td>번호</td>
				<td>${memberVo.memberIdx}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${memberVo.memberName}</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>${memberVo.memberId}</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${memberVo.memberEmail}</td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td>${memberVo.memberZipcode}</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>${memberVo.memberAdress}${memberVo.memberDetailAdress}${memberVo.memberExtraAdress}</td>
			</tr>
			<tr>
				<td>등록일자</td>
				<td>${memberVo.memberRegDate}</td>
			</tr>
			<tr>
				<td>업데이트일자</td>
				<td>${memberVo.memberUpdateDate}</td>
			</tr>
			<tr>
				<td>등록ID</td>
				<td>${memberVor.memberRegId}</td>
			</tr>
			<tr>
				<td>업데이트ID</td>
				<td>${memberVo.memberUpdateId}</td>
			</tr>
			<tr>
				<td>등록IP</td>
				<td>${memberVo.memberRegIp}</td>
			</tr>
			<tr>
				<td>업데이트IP</td>
				<td>${memberVo.memberUpdateIp}</td>
			</tr>
			<tr>
				<td>직위</td>
				<td>${memberVo.memberAuth}</td>
			</tr>
   		</table>
 
               	<div class="btn">
				<button  type="button" onclick="goBoardList()">글 목록</button>
				<button id="delBtn" name="delBtn" type="button">삭제</button> 
				<button type="button" onclick="goUpdate(${memberVo.memberIdx})" >수정하기</button>
				</div>
         </td>
         </tr>
        </table>
  </form>

</body>
</html>