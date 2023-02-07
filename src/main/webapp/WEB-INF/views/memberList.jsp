<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	 
	
	<script src="./resources/compnent/jquery-3.3.1.min.js"></script>
	<script src="./resources/compnent/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
	<script src="./resources/compnent/jquery-loading-master/dist/jquery.loading.min.js"></script>
	<script src="./resources/compnent/jqueryPrint/jqueryPrint.js"></script>
	
	<script src="./resources/js/member.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="./resources/css/board.css">
	<link rel="stylesheet" href="./resources/compnent/bootstrap-5.3.0/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>List page</title>
<style type="text/css">
.imgfile{
	width:50px;
	height:50px;
	cursor:pointer;
	display: inline;
	float:right;
	margin-right:10px;
}
#container {
			width: 85%;
			margin: 0 auto;
			font-size:15px;			/* 가로로 중앙에 배치 */
			
		}

/* Bootstrap 수정 */
.table > thead {
	background-color: #b3c6ff;
}
.table > thead > tr > th {
	text-align: center;
}
.table-hover > tbody > tr:hover {
	background-color: #e6ecff;
}
.table > tbody > tr > td {
	text-align:left;
}
.table > tbody > tr > #title {
	text-align: left;
}

div > #paging {
	text-align: center;
}

.hit {
	animation-name: blink;
	animation-duration: 1.5s;
	animation-timing-function: ease;
	animation-iteration-count: infinite;
	/* 위 속성들을 한 줄로 표기하기 */
	/* -webkit-animation: blink 1.5s ease infinite; */
}

/* 애니메이션 지점 설정하기 */
/* 익스플로러 10 이상, 최신 모던 브라우저에서 지원 */
@keyframes blink {
	from {color: white;}
	30% {color: yellow;}
	to {color: red; font-weight: bold;}
	/* 0% {color:white;}
	30% {color: yellow;}
	100% {color:red; font-weight: bold;} */
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
	
<div id="container">
	<h1 style="text-align:center;">회원 목록</h1>
	<br>
	<br>

<input type="hidden" name="loginId" id="loginId" value="${sessionId}">

	<form id="memberSearchfrm" method="post" class="searchBox">
	
			<select id="searchCondition" name="searchCondition" >
				<option value="" <c:if test="${search.searchCondition == null || search.searchCondition eq ''}">selected</c:if>>검색조건</option>
				<option value="memberId" <c:if test="${search.searchCondition eq 'memberId'}">selected</c:if>>아이디</option>
				<option value="memberName" <c:if test="${search.searchCondition eq 'memberName'}">selected</c:if>>이름</option>
			</select>
			<input type="text" id="memberSearchWord" name="memberSearchWord" value="${search.memberSearchWord}">
			<button id="searchBtn">검색</button>
		</form>
<div class="btn">
<c:choose>
	<c:when test="${sessionAuth eq 'master'}">
		<button type="button" onclick="memberDeleteChkList()" >삭제</button>
	</c:when>
</c:choose>
</div>
<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
				<td><input type="checkbox" id="checkAll" name="checkAll"></td>
				<td>이름</td>
				<td>ID</td>
				<td>이메일</td>
				<td>회원등록일자</td>
				<!-- <td>회원수정일자</td> -->
				<td>회원등록ID</td>
				<td>회원수정ID</td>
				<!-- <td>회원IP</td> -->
				<!-- <td>회원수정IP</td> -->
				<td>권한</td>
			</tr>
	</thead>
	<tbody>
		<c:forEach items="${memberVo}" var="memberVo">
				<tr ondblclick="goDetail(${memberVo.memberIdx})">
					<td>
					<input type="checkbox" id="${memberVo.memberIdx}" name="check" value="${memberVo.memberIdx}"/>
				</td>
					<td>${memberVo.memberName}</td>
					<td>${memberVo.memberId}</td>
					<td>${memberVo.memberEmail}</td>
					<td>${memberVo.memberRegDate}</td>
					<%-- <td>${memberVo.memberUpdateDate}</td> --%>
					<td>${memberVo.memberRegId}</td>
					<td>${memberVo.memberUpdateId}</td>
					<%-- <td>${memberVo.memberRegIp}</td>
					<td>${memberVo.memberUpdateIp}</td> --%>
					<td>${memberVo.memberAuth}</td>	
			</tr>
		</c:forEach>
	</tbody>
	</table>
	
	
	
	<div class="paging">
		<ul>
			<c:if test="${paging.prev}">
		    	<span>
		    		<a href='<c:url value="/memberList?page=${paging.startPage - 1}"/>'></a>
		    	</span>
		    </c:if>
		    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="num">
				<c:choose>
					<c:when test="${paging.cri.page eq num}">
						<span><a>${num}</a></span>
					</c:when>
					<c:otherwise>
						<span><a href='<c:url value="/memberList?page=${num}"/>'>${num}</a></span>
					</c:otherwise>
				</c:choose>
		    </c:forEach>
		    <c:if test="${paging.next && paging.endPage > 0}">
		    	<span>
		    		<a href='<c:url value="/memberList?page=${paging.endPage + 1}"/>'></a>
		    	</span>
		    </c:if>
		</ul>
	</div>
</body>
</html>
