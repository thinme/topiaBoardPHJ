<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
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
<title>Member Insert</title>
<style type="text/css">
.imgfile{
	width:50px;
	height:50px;
	cursor:pointer;
	display: inline;
	float:right;
	margin-right:10px;
}
:root {
  /* COLORS */
  --white: #e9e9e9;
  --gray: #333;
  --blue: #0367a6;
  --lightblue: #008997;

  /* RADII */
  --button-radius: 0.7rem;

  /* SIZES */
  --max-width: 758px;
  --max-height: 420px;

  font-size: 16px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}

.container__form {
  height: 900px;
  position: absolute;
  margin-top:400px;
  margin-left:400px;
  transition: all 0.6s ease-in-out;
  text-align:center;
}

.container--signup {
  left: 0;
  width: 50%;
  z-index: 1;
  margin-top:100px;
}
.btn {
  background-color: var(--blue);
  background-image: linear-gradient(90deg, var(--blue) 0%, var(--lightblue) 74%);
  border-radius: 20px;
  border: 1px solid var(--blue);
  color: var(--white);
  cursor: pointer;
  font-size: 0.8rem;
  font-weight: bold;
  letter-spacing: 0.1rem;
  padding: 0.9rem 4rem;
}

.form > .btn {
  margin-top: 1.5rem;
}
.form {
  background-color: var(--white);
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 3rem;
  height: 100%;
  text-align: center;
}
.form__title {
  font-weight: 300;
  margin: 0;
  margin-bottom: 1.25rem;
}

.input {
  background-color: #fff;
  border: none;
  padding: 0.9rem 0.9rem;
  margin: 0.5rem 0;
  width: 100%;
}
</style>
</head>
<body>
<div class="logo">
	<a onclick="goBoardList()"><img src="./resources/img/logo.png"></a>
	<div class="loginstatus">
	<a onclick="goLogin()"><img src="./resources/img/white.png" class="imgfile"/></a>	
	</div>
</div>
  <!-- Sign Up -->
 <div class="container__form container--signup">
    <form name="memberInsert" id="memberInsert" class="form">
	<input type="hidden" id="memberAuth" value="${memberAuth}">
      <h2 class="form__title">Sign Up</h2>
      <input type="text" placeholder="UserName" id="memberName" name="memberName" class="input"/>
      <span id="nameChkresult"></span>
      
      <input type="text" placeholder="UserId" class="input" id="memberId" name="memberId" maxlength=20/>
      <span id="idChkresult"></span>
      
      <input type="password" placeholder="Password" class="input" id="memberPassword" name="memberPassword" />
      <span id="pswdChkresult"></span>
      
      <input type="password" placeholder="PasswordCheck" class="input" id="membertempPswd" name="membertempPswd"/>
      <span id="pswdDplChkresult"></span>
      
      <!-- input type="email" placeholder="Email" class="input" id="memberEmail" name="memberEmail" />
      <span id="emailChkresult"></span> -->
      <span>
      <input type="hidden" name="memberEmail" id="memberEmail" value="" class="input" style="width:270px;">
      <input type="text" name="email1" id="memberEmail" class="input" style="width:270px;">   @
      <input type="text" name="email2" id="memberEmail" class="input" style="width:230px;" disabled >

	      <select name="email" onchange="email_change()" class="input" style="width: 130px;">
	
	      <option value="0" >선택하세요</option>
	
	      <option value="9">직접입력</option>
	
	      <option value="naver.com">naver.com</option>
	      <option value="nate.com">nate.com</option> 
	      <option value="daum.net">daum.net</option> 
	
	      </select></span>
	      <span id="emailChkresult"></span>
      <span style="margin-right:390px; margin-top:20px;"><input type="text" name="memberZipcode" id="memberZipcode" />
      <input type="button" id="memberZipcodeBtn" value="주소검색" /></span>
      <span id="ZipcodeResult" style="margin-left: 5px;"></span>
      <input type="text" name="memberAdress" id="memberAdress" class="input"/>
      <input type="text" placeholder="상세주소" name="memberDetailAdress" id="memberDetailAdress" class="input"/>
      <input type="text" id="memberExtraAdress" name="memberExtraAdress" placeholder="참고항목" class="input">
      
      <button type="button" class="btn" id="insertBtn">Sign Up</button>
    </form>
  </div>
</body>
</html>