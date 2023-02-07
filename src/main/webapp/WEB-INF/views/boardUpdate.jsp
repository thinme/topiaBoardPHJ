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
	
	<script src="./resources/js/board.js" charset="UTF-8"></script> 
	<link rel="stylesheet" type="text/css" href="./resources/css/board.css">
	<link rel="stylesheet" type="text/css" href="./resources/compnent/jquery-ui-1.12.1.custom/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="./resources/compnent/jquery-loading-master/dist/jquery.loading.min.css">
	<link rel="stylesheet" href="./resources/compnent/bootstrap-5.3.0/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
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
<form id="upFrm" method="post">
		<input type="hidden" name="boardIdx" id="boardIdx" value="${boardVo.boardIdx}">
		<c:if test="${sessionId eq boardVo.boardMemberId or sessionAuth eq 'master' }">
        <table  style="padding-top:50px"  align = center width=840 border=0 cellpadding=2 >
                <tr>
                <td class="title" height=40 align= center bgcolor=#ccc ><font color=whit> 글 수정하기</font></td>
                </tr>
                <tr>
                <td bgcolor=white>
                <table class = "table2">
                		<tr>
                		<td>공지여부</td>
                		<td><input type="radio" name="boardNoticeYn" value="1" />공지등록
                			<input type="radio" name="boardNoticeYn" value="0" checked="checked" />공지해제</td>
                		</tr>
                		<tr>
                		<td>작성일자</td>
                		<td><input type="text" class="dateInput" id="boardWriteDate" name="boardWriteDate"/></td>
                		</tr>
                        <tr>
                        <td>작성자</td>
                        <td><input type="text" id="boardMemberName" name="boardMemberName" value="${boardVo.boardMemberName}" readonly="readonly"></td>
                        </tr>
                        <td>작성Id</td>
                        <td><input type="text" id="boardMemberId" name="boardMemberId" value="${boardVo.boardMemberId}" readonly="readonly"></td>
                        </tr>
 
                        <tr>
                        <td>제목</td>
                        <td><input type="text" name="boardTitle" id="boardTitle" maxlength="30" style="width:700px;"  value="${boardVo.boardTitle}"/></td>
                        </tr>
 
                        <tr>
                        <td>내용</td>
                        <td><textarea cols=85 rows=15 id="boardContent" name="boardContent" maxlength="500" >${boardVo.boardContent}</textarea></td>
                        </tr>
                        </table>
 
                        <div class="btn">
                        <button type="button" id="listBtn" >목록가기</button>
                        <button type="button" id="upBtn" value="수정완료">수정완료</button>
                        </div>
                </td>
                </tr>
        </table>
     </c:if>
  </form>

</body>
</html>