//페이지 로드시 바로 실행
 $(window).on("load",function(){
	
	//체크이벤트
	checkEve();
	
	// 버튼 이벤트
	btnEve();
	
	//유효성 체크
	regexCheck();
	
	// 자동완성 끄기
	$("input").prop("autocomplete","off");

	//id타이핑시 유효성체크 표시
	$("#memberId").on("keyup", function(){
		var memberId = $("#memberId").val();
		if(memberId != ''){
			$.ajax({
				url: "./loginIdChck",
				type:"POST",
				data: "memberId="+memberId,
				success: function(result){
					if (result == '0') {
						var regExp = /^[a-z0-9]{1}[a-z0-9]{4,19}$/;
						var regResult = regExp.test($("#memberId").val().trim());
						if(regResult){
							$('#idChkresult').css("color", "green");
	                    	$('#idChkresult').text('사용 가능한 아이디입니다.');
						}else{
							if(!regResult){
								$('#idChkresult').css("color", "red");
		                    	$('#idChkresult').text('5~20자의 영문 소문자, 숫자만 사용가능합니다.');
								$('#memberId').focus();
								return false;
                    			
							}else{
								$('#idChkresult').text("");
							}
						}
                	} else {
						$('#idChkresult').css("color", "red");
                    	$('#idChkresult').text('이미 사용중인 아이디입니다.');
                    	$('#memberId').focus();
                    	return false;
                	}
				},
				error:function(request,status,error){
			        alert("code:"+request.status);
					alert("status:"+request.responseText);
					alert("error:"+error);
				}       
			});
		}else{
			$('#idChkresult').css("color", "red");
			$('#idChkresult').text('필수정보입니다.');
			$('#memberId').focus();
			return false;
		}
	});
	
});

var checkEve = function(){

	$("#checkAll").click(function() {
		if($("#checkAll").is(":checked")){
			$("input[name=check]").prop("checked", true);
		}else{
			 $("input[name=check]").prop("checked", false);
		}
	});
	
	$("input[name=check]").click(function() {
		var total = $("input[name=check]").length;
		var checked = $("input[name=check]:checked").length;

		if(total != checked){
			 $("#checkAll").prop("checked", false);
		 }else{
		 	 $("#checkAll").prop("checked", true); 
		}
	});
	$("#resetSearchBtn").unbind().click(function(){
		$("#searchCondition").val("");
		$("#memberSearchWord").val("");
	});

}

var btnEve = function(){	
	
	$("#goRegBtn").click(function(){
		location.href = "./memberInsert";
	});
	
	$("#listBtn").on("click",function(){
		location.href = "./memberList";
	});
}

//funcion 버튼 이벤트
function goMemberPage(memberIdx){
	location.href = "./memberDetail?memberIdx=" + memberIdx;	
}
//detail로 가기
var goDetail = function(memberIdx){
	location.href = "./memberDetail?memberIdx=" + memberIdx;
}

//게시글목록
var goBoard = function(){
	location.href = "./boardList";
}

//업데이트 호출시 이벤트발생
function goUpdate(memberIdx){
	location.href = "./memberUpdate?memberIdx=" + memberIdx;
}
//게시글목록가기
function goBoardList(){
	location.href = "./boardList";
}
//로그인하러가기
var goLogin = function(){
	location.href = "./login";
}
//이미지클릭시, 하단메뉴바생성
var dropMenu =  function(){
    var click = document.getElementById("dropMenu");
    if(click.style.display == "none"){
    	click.style.display = "block";
    }else{
    	click.style.display = "none";
    }
}

//이메일 선택 test용
function email_change(){

if(document.memberInsert.email.options[document.memberInsert.email.selectedIndex].value == '0'){

 document.memberInsert.email2.disabled = true;

 document.memberInsert.email2.value = "";

}

if(document.memberInsert.email.options[document.memberInsert.email.selectedIndex].value == '9'){

 document.memberInsert.email2.disabled = false;

 document.memberInsert.email2.value = "";

 document.memberInsert.email2.focus();

} else{

 document.memberInsert.email2.disabled = true;

 document.memberInsert.email2.value = document.memberInsert.email.options[document.memberInsert.email.selectedIndex].value;

}

}

//체크시 삭제되는 이벤트
function memberDeleteChkList(){

	var checkArr = new Array();
	var cnt = $("input[name='check']");
	
	for (var i=0; i<cnt.length; i++){
    	if(cnt[i].checked){	
    		checkArr.push(cnt[i].value);
    	};
	}
	if(checkArr.length == 0){
		alert("선택된 항목이 없습니다.");
	}else{
		var chk = confirm("해당 게시글을 삭제하시겠습니까?");
		$.ajax({
			url : "./memberDeleteChkList",
			type: "POST",
			traditional : true, // 배열넘기기 option
			data : {
				checkArr : checkArr
			},
			success :  function(result){
				alert("성공적으로 삭제되었습니다.");
				location.href =  "./memberList";
			},
			error: function(){  
				alert("삭제 실패");  
			}
		});
	}
}


//빈값체크
var emptyCheck = function() {

	if($("#memberId").val() == ""){
		$('#idChkresult').css("color", "red");
		$('#idChkresult').text("필수정보입니다.");
		$("#userId").focus();
		return false;
		}
	if($("#memberPassword").val() == ""){
		$('#pswdChkresult').css("color", "red");
		$('#pswdChkresult').text("필수정보입니다.");
		$("#userPswd").focus();
		return false;
	}
	if($("#membertempPswd").val() == ""){		    
		$('#pswdChkresult').css("color", "red");
		$('#pswdChkresult').text("필수정보입니다.");
		$("#userPswd").focus();	       
	}
	if($("#memberName").val() == ""){
		$('#nameChkresult').css("color", "red");
		$('#nameChkresult').text("필수정보입니다.");
		$("#userName").focus();
		return false;
	}
	/*if($("#memberEmail").val() == ""){
		$('#emailChkresult').css("color", "red");
		$('#emailChkresult').text("필수정보입니다.");
		$("#userEmail").focus();
		return false;
	}*/
		if($("#memberZipcode").val().trim() == "" && $("#memberAdress").val().trim() == ""){
		$('#ZipcodeResult').css("color", "red");
		$('#ZipcodeResult').text("필수정보입니다.");
		$("#memberZipcode").focus();
		return false;
	}
	
	
	return true;
}

var regexCheck = function(){
	
	/*이름 유효성검사*/
	$("#memberName").blur(function(){
		if($("#memberName").val() == ""){
			$('#nameChkresult').css("color", "red");
			$('#nameChkresult').text("필수정보입니다.");
			$("#memberName").focus();
			return false;
		}else{
			$('#nameChkresult').text("");
		}
	});
	
	/*아이디 유효성검사*/
	$("#memberId").blur(function(){
		if($("#memberId").val().trim() == ""){
			$('#idChkresult').css("color", "red");
			$('#idChkresult').text("필수정보입니다.");
			return false;
		}
	});	

	
	/*비밀번호 유효성검사*/
	$("#memberPassword").blur(function(){
		var pswd = $("#memberPassword").val(); 
		if(pswd == ""){
			$('#pswdChkresult').css("color", "red");
			$('#pswdChkresult').text("필수정보입니다.");
			$("#memberPassword").focus();
			return false;
		}else if(pswd.length < 8 || pswd.length > 20){
			$('#pswdChkresult').text("비밀번호는 8~20자 이내로 입력해주세요.");
			$('#memberPassword').focus();
		}else if(pswd.search(/\s/) != -1){
			$('#pswdChkresult').text("비밀번호는 공백없이 입력해주세요.");
		}else{
			var regExp = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
			var regResult = regExp.test(pswd);
			if(!regResult){
				$('#pswdChkresult').text("비밀번호는 8자 이상이어야 하며, 숫자/소문자/특수문자를 모두 포함해야 합니다.");
				$('#memberPassword').focus();
				return false;
			}else{
				$('#pswdChkresult').text("");
			}
		}
	});

	$("#membertempPswd").blur(function(){
		if($("#membertempPswd").val() != ""){
			if($("#memberPassword").val() != $("#membertempPswd").val()){
				$("#pswdDplChkresult").css("color", "red");
				$("#pswdDplChkresult").text("비밀번호가 일치하지 않습니다.");
				$("#membertempPswd").val(""); 
				$("#membertempPswd").focus();
			}else{
				$("#pswdDplChkresult").text("");
			}
		}else{
			$("#pswdDplChkresult").css("color", "red");
			$("#pswdDplChkresult").text("필수정보입니다.");
			return false;
		}	
	});
	
	/*이메일 유효성검사
	$('#memberEmail').blur(function(){
		if($('#memberEmail').val() == ""){
			$('#emailChkresult').css("color", "red");
			$('#emailChkresult').text("필수정보입니다.");
			$("#memberEmail").focus();
			return false;
		}else{
			var regExp = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
			var regResult = regExp.test($('#memberEmail').val());
			if(!regResult){
				$('#emailChkresult').text("잘못된 이메일 형식입니다.");
				$("#memberEmail").val("");
				$('#memberEmail').focus();
				return false;
			}else{
				$('#emailChkresult').text("");
			}
		}
	});*/
	
	/*주소 유효성검사*/
	$("#memberZipcode").blur(function(){
		if($("#memberZipcode").val().trim() == ""){
			$("memberZipcode").focus();
			return false;
		}else{
			$('memberZipcode').text("");
		}
	});
	return true; 	
}

$(document).ready(function(){

	//로그인 버튼 클릭시 호출
	$("#loginBtn").on("click", function(){
		$.ajax({
			url: "./loginCheck",
			type: "POST",
			data: $("#memberAuth").serialize(),
			dataType: "JSON",
			async: false,
			success: function(res){
			console.log(res.resultYn);
				if(res.resultYn == "Y"){
					//listAll페이지로 이동
						location.href="./boardList";
				}else{
					alert("아이디와 비밀번호를 확인하세요");
				}
			},
		    error:function(request,status,error){
		        alert("code:"+request.status);
				alert("message:"+request.responseText);
				alert("error:"+error);       
			}
		});
	});


	//회원가입화면에서 등록 버튼 클릭시 호출
	$("#insertBtn").on("click", function(){
	 var test = $("input[name=email1]").val();
	 var test2 = $("input[name=email2]").val();
	 
	 var test3 = test + "@" + test2;
	 $("input[name=memberEmail]").attr("value",test3);
	 var eeee =
	  $("input[name=memberEmail]").val();
	 
	 var param =  $("#memberInsert").serialize();
	 console.log(param);
	
		var check = emptyCheck();
		var regCheck = regexCheck();
		//아이디 유효성 호출
		if(idCheckvalidation()=="XX") return;
		
		var memberAuth = $("#memberAuth").serialize();
			//if(check && regCheck){
				$.ajax({
					url: "./memberInsertOk",
					type:"POST",
					data: param,
					async: false,
					success: function(data){
						alert("회원가입이 완료되었습니다.");
							location.href="./boardList";
					},
					error: function(){
						alert("등록 실패");
					}
				});
			//}else{
			//	return false;
			//}
	});

	$("#memberZipcodeBtn").unbind().click(function() {
		daum.postcode.load(function(){
	        new daum.Postcode({
	            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
                
                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("memberExtraAdress").value = extraAddr;
                
                } else {
                    document.getElementById("memberExtraAdress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                //document.getElementById('memberZipcode').value = data.zonecode;
                //document.getElementById("memberAdress").value = addr;
                $("#memberZipcode").val(data.zonecode);
                $("#memberAdress").val(addr);
                // 커서를 상세주소 필드로 이동한다.
                //document.getElementById("memberDetailAdress").focus();
                $("#memberDetailAdress").focus();
            }
        }).open();
	    });
	});
	
	//수정완료버튼시 호출
	$("#upBtn").on("click", function(){
	var uptChk = confirm("수정하시겠습니까?");
	if(uptChk){
		$.ajax({
			url: "./memberUpdateOk",
			type:"POST",
			data: $("#detailfrm").serialize(),
			success: function(data){
				if(data.resultYn == 'Y'){
					alert("성공적으로 수정되었습니다.");
					location.href ="./boardList";
				}else{
					alert("수정에 실패했습니다.");
				}
				
			},
			error: function(){  
				alert("수정 실패");  
			}
			
		});
	}});
	
	//권한여부 수정시 선택가능케 함
	$("#memberAuth").on("change", function(){
		var selectVal = $(obj).val();
	$("#auth").val(selectVal);
		});
	
	//삭제버튼시 호출
	$("#delBtn").on("click", function(memberidx){
		var delChk = confirm("삭제하시겠습니까?");
		if(delChk){
		var memberIdx = $("#memberIdx").val();
			$.ajax({
				url:  "./memberDelete",
				data: "memberIdx="+ memberIdx,
				type: "POST",
				success: function(data){
					alert("성공적으로 삭제되었습니다.");
					location.href ="./boardList";
				},
				error: function(){  
					alert("삭제 실패");  
				}
			});
		}});
});

//등록시 아이디 유효성 체크
function idCheckvalidation(){
	var memberId = $("#memberId").val().trim();
	var idCheckResult = "OK";
	$.ajax({
		url: "./loginIdChck",
		type:"POST",
		async: false,    // True(기본) : 비동기 처리, false : 동기처리
		data: "memberId="+memberId,
		success: function(result){
			if (result == '0') {
				var regExp = /^[a-z0-9]{1}[a-z0-9]{4,19}$/;
				var regResult = regExp.test($("#memberId").val().trim());
				if(!regResult){
					$('#idChkresult').css("color", "red");
                	$('#idChkresult').text('5~20자의 영문 소문자, 숫자만 사용가능합니다.');
					//$('#memberId').focus();
					idCheckResult = "XX";
				}else {
					idCheckResult = "OK";
				}
        	} else {
				$('#idChkresult').css("color", "red");
            	$('#idChkresult').text('이미 사용중인 아이디입니다.111');
            	//$('#memberId').focus();
            	idCheckResult = "XX";
        	}
		},
		error:function(request,status,error){
	        alert("code:"+request.status);
			alert("status:"+request.responseText);
			alert("error:"+error);
		}       
	});
	
	//alert("결과 : " + idCheckResult);
	
	return idCheckResult;
}

//절대경로
function getContextPath(){
	var hostIndex = location.href.indexOf( location.host ) + location.host.length;	
	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
}