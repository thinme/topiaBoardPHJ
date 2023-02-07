<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>memberUpdate title here</title>
<head>
	
	<script src="./resources/compnent/jquery-3.3.1.min.js"></script>
	<script src="./resources/compnent/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
	<script src="./resources/compnent/jquery-loading-master/dist/jquery.loading.min.js"></script>
	<script src="./resources/compnent/jqueryPrint/jqueryPrint.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="./resources/js/member.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="./resources/css/board.css">
	<link rel="stylesheet" href="./resources/compnent/bootstrap-5.3.0/css/bootstrap.min.css">
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

<form action="./memberUpdateOk" id="detailfrm" method="post">
	<input type="hidden" id="memberIdx" name="memberIdx" value="${memberVo.memberIdx}">
	<input type="hidden" id="memberName" name="memberName" value="${memberVo.memberName}">
        <table  style="padding-top:50px"  align = center width=840 border=0 cellpadding=2 >
                <tr>
                <td class="title" height=40 align= center bgcolor=#ccc><font color=whit> 회원정보 수정하기</font></td>
                </tr>
                <tr>
                <td bgcolor=white>
                <table class = "table2">
               		<tr>
                		<td>이름</td>
						<td>${memberVo.memberName}</td>
               		</tr>
           			<tr>
                		<td>ID</td>
						<td><input type="text" value="${memberVo.memberId}" name="memberId" id="memberId" >
						    <span id="idChkresult"></span></td>
						
               		</tr>
                    <tr>
                        <td>PW</td>
						<td><input type="password"  value="${memberVo.memberPassword}" name="memberPassword" id="memberPassword" >
							<span id="pswdChkresult"></span></td>
                   </tr>
                        <td>이메일</td>
						<td><input type="text" value="${memberVo.memberEmail}" name="memberEmail" id="memberEmail" ></td>
               	   </tr>
 
                   <tr>
                        <td>주소 : </td>
						<td> 
							<div>
								<input type="text" name="memberZipcode" id="memberZipcode" value="${memberVo.memberZipcode}"/>
								<input type="button" id="memberZipcodeBtn" value="주소검색">
								<span id="ZipcodeResult" style="margin-left: 5px;"></span>
								<br>
								<input type="text" name="memberAdress" id="memberAdress" value="${memberVo.memberAdress}" style="width: 400px;"/>
								<br>
								<input type="text" name="memberDetailAdress" id="memberDetailAdress" value="${memberVo.memberDetailAdress}"/>
								<br>
								<input type="text" id="memberExtraAdress" name="memberExtraAdress" value="${memberVo.memberExtraAdress}" placeholder="참고항목">
							 </div>
						</td>
             </tr>
  					<c:if test="${ sessionAuth eq 'master' or sessionAuth eq 'manager'}">
			  <tr>
						<td> 권한</td>
						<td><select name="memberAuth" id="memberAuth" >
							<option value="member"> member</option>
							<option value="master"> master</option>
							<option value="manager">manager</option>
							</select>
						</td>
			 </tr>
		</c:if>
             </table>
 
              <div class="btn" style="padding-right:0px;">
				<button type="button" id="listBtn">회원목록</button>
				<button type="button" id = "upBtn">수정완료</button>
             	</td>
                </tr>
        </table>
  </form>
</body>
</html>