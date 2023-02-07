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
	

	<script src="./resources/js/member.js" charset="UTF-8"></script> 
	<link rel="stylesheet" type="text/css" href="./resources/css/board.css">
	<link rel="stylesheet" href="./resources/compnent/bootstrap-5.3.0/css/bootstrap.min.css">
	
<meta charset="UTF-8">
<title>Login page</title>
<style type="text/css">
.imgfile{
	width:50px;
	height:50px;
	cursor:pointer;
	display: inline;
	float:right;
	margin-right:10px;
}		
		
.container__form {
  height: 500px;
  position: absolute;
  margin-top:400px;
  margin-left:500px;
  transition: all 0.6s ease-in-out;
  text-align:center;
  
}

.container--signin {
  left: 0;
  width: 30%;
  z-index: 2;
  margin-top:100px;
  text-align:center;
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

.input {
  background-color: #fff;
  border: none;
  padding: 0.9rem 0.9rem;
  margin: 0.5rem 0;
  width: 100%;
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
  text-transform: uppercase;
  transition: transform 80ms ease-in;
}

.form > .btn {
  margin-tom: 0.5rem;
}
.form__title {
  font-weight: 300;
  margin: 0;
  margin-bottom: 1.25rem;
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




</style>
</head>
<body>
<div class="logo">
	<a onclick="goBoardList()"><img src="./resources/img/logo.png"></a>
	<div class="loginstatus">
	<a onclick="goLogin()"><img src="./resources/img/white.png" class="imgfile"/></a>	
	</div>
</div>
<div class="container__form container--signin">
	<form id="memberAuth" method="post" onsubmit="return false;" class="form" >
	     <h2 class="form__title">Sign In</h2>
	     <input type="text" placeholder="ID" id="idLogin" name="memberId" class="input" />
	     <input type="password" placeholder="Password" id="pwLogin" name="memberPassword" class="input" />
	     <button class="btn" id="loginBtn">Sign In</button>
	     <button id="goRegBtn" class="btn" style="margin-bottom:15px;">Sign UP</button>
    </form>
  </div>
</body>
</html>