<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="./resources/compnent/jquery-3.3.1.min.js"></script>
	<script src="./resources/compnent/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
	<script src="./resources/compnent/jquery-loading-master/dist/jquery.loading.min.js"></script>
	<script src="./resources/compnent/jqueryPrint/jqueryPrint.js"></script>
	
	<script src="./resources/js/board.js" charset="UTF-8"></script> 
	<script src="https://code.iconify.design/iconify-icon/1.0.2/iconify-icon.min.js"></script>
	<script src="https://code.iconify.design/iconify-icon/1.0.2/iconify-icon.min.js"></script>
	<link rel="stylesheet" type="text/css" href="./resources/css/board.css">
	<link rel="stylesheet" href="./resources/compnent/bootstrap-5.3.0/css/bootstrap.min.css">
<title>detail title here</title>
<style>
.all{
	width:60%;
	border: 1px solid gray;
	margin-top:60px;
	height:600px;
	
}
.notice{
	width:30px;
	heigtht:20px;
}
.boardtitle{
	
	margin-top: 20px;
	font-size:30px;
	font-weight:bold; 
}
.info{
	text-align:right;
	border-bottom:1px solid gray;
}
.contents{
	margin-top: 60px;
	margin-left:10px;
	margin-right:10px;
}
.newbtn{
	margin-top: 10px;
}
.repl{
	width:60%;
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

<div class="all">
<form id="detailFrm" method="POST">
<input type="hidden" id="boardIdx" name="boardIdx" value="${boardVo.boardIdx}">

	<div class="boardtitle" name="boardTitle" id="boardTitle" style="margin-left:25px;" readonly>
		<c:choose>
			<c:when test="${boardVo.boardNoticeYn eq 1}">
			<img src="./resources/img/warning.png" class="notice"/>
			</c:when>
			<c:when test="${boardVo.boardNoticeYn eq 0 or boardVo.boardNoticeYn eq null}">
			</c:when>
		</c:choose>
		${boardVo.boardTitle}</div>
	<div class="info">
		<div id="boardMemberName" name="boardMemberName" readonly="readonly">${boardVo.boardMemberName} |
		<span name="boardWriteDate" id="boardWriteDate" style="margin-right:10px;">${boardVo.boardWriteDate}</span>
		</div>
	</div>
	<div class="btn">
			<button type="button" id="listBtn" class="newbtn">글 목록</button>
		<c:choose>
		 <c:when test="${sessionId eq boardVo.boardMemberId or sessionAuth eq 'master' or sessionAuth eq 'manager'}">
			<button type="button"  id="delBtn" class="newbtn">삭제</button>		
			<button type="button" onclick="goUp(${boardVo.boardIdx})" class="newbtn">수정하기</button>	
		</c:when>
	</c:choose>
	</div>
	<div class="contents">
		<article id="boardContent" name="boardContent" style="word-wrap:break-word;">${boardVo.boardContent}</article>
	</div>
</div>

<div class="repl">
	<form name="replyInsert" method="POST">
    <input type="hidden" name="loginId" id="loginId" value="${sessionId}">
	<input type="hidden" id="bno" name="bno" value="${boardVo.boardIdx}" />
	<br><br><br><br>
	<c:if test="${sessionId != null}">
	<div>
		<label for="replyText">댓글</label>
	</div>
	<div class="text">
		<textarea id="replyText" name="replyText" placeholder="댓글을 적어보세요" style="height:70px; width:100%;"></textarea>
		<br>
			<button type="button" id="replyInsert" style="height:40px;" >댓글등록하기</button>
	</div>
	</c:if>
</form>
<br>
<br>
<h5 style="text-align:left; margin-bottom:25px;">전체댓글</h5>
<div id="replyList"> 
</div> 
</div>
</body>
</html>