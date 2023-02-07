"use strict"; // 엄격한 문법 검사

// 페이지 로드시 바로 실행
$(window).on("load",function(){
	$.datepicker.setDefaults({
		showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
        ,changeYear: true //콤보박스에서 년 선택 가능
        ,changeMonth: true //콤보박스에서 월 선택 가능                              
        ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
		,dateFormat: 'yy-mm-dd' //Input Display Format 변경
	});
	
	//체크이벤트
	checkEve();
	
	//댓글리스트
	replyList();
	// 버튼 이벤트
	btnEve();
	
	$(".dateInput").removeClass('hasDatepicker').datepicker();
	
	$("input").prop("autocomplete","off");
	
	 $('#boardWriteDate').datepicker('setDate', 'today');
});


var goDetail = function(boardIdx){
	var sessionId = $("#loginId").val();
	if( sessionId == "undefined" || sessionId == "" || sessionId == null){
		alert("로그인 후 글 열람이 가능합니다.");
		location.href = "./login";
	}else{
		location.href =  "./boardDetail?boardIdx=" + boardIdx;
	}
}
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
}

//onClick시 호출
var btnEve = function(){
	
	//id값 확인후 상세게시물로 이동
	var sessionId = $("#loginId").val();
		//alert(sessionId);
		$("#regBtn").on("click", function(){	
			if( sessionId == "undefind" || sessionId == "" || sessionId == null){
				alert("작성은 로그인후 이용가능합니다");
				location.href = "./login";
			}else{
				location.href = "./boardInsert";
			}
		});
	
	//상세게시판에서 게시글목록으로 이동
	$("#listBtn").on("click",function(){
		location.href = "./boardList";
	});
	
	
}

//게시글업데이트
var goUp = function(boardIdx){
	location.href = "./boardUpdate?boardIdx=" + boardIdx;
}

//로그인하러가기
var goLogin = function(){
	location.href = "./login";
}
var goInsert= function(){
	location.href = "./memberInsert";
}
//게시글목록
var goBoard = function(){
	location.href = "./boardList";
}

//이미지클릭시, 하단메뉴바생성
var dropMenu =  function(){
    var click = document.getElementById("dropMenu");
    if(click.style.display === "none"){
    	click.style.display = "block";
    }else{
    	click.style.display = "none";
    }
}

//나의정보 보러가기
var goMyPage = function(sessionIdx){
	location.href = "./memberDetail?memberIdx=" + sessionIdx;
}

//(관리자)회원목록으로가기
var goMemberList = function(){
	location.href = "./memberList";
}

//빈칸체크하기
var emptyChk = function(){
	
	if($("#boardWritetDate").val() == ""){
		alert("작성일자를 선택하세요.");
		$("#boardWriteDate").focus();
		return false;
		}
	if($("#boardTitle").val() == ""){
		alert("제목은 필수 입력사항입니다.");
		$("#boardTitle").focus();
		return false;
	}
	if($("#boardContent").val() == ""){
		alert("내용은 필수 입력사항입니다.");
		$("#boardContent").focus();
		return false;
	}
	
	return true;
}

//게시물 체크버튼후 삭제
function boardDeleteChkList(){

	var checkArr = new Array();
	var list = $("input[name='check']");
	
	for(var i = 0; i < list.length; i++){
		if(list[i].checked){
			checkArr.push(list[i].value);
		}
	}
	if(checkArr.length == 0){
		alert("선택된 항목이 없습니다.");
	}else{
		var chk = confirm("해당 게시글을 삭제하시겠습니까?");
		$.ajax({
			url :  "./boardDeleteChkList",
			type: "POST",
			traditional : true, // 배열넘기기 option
			data : {
				checkArr : checkArr
			},
			success :  function(result){
				alert("성공적으로 삭제되었습니다.");
				location.href = "./boardList";
			},
			error: function(){  
				alert("삭제 실패");  
			}
		});
	}
}



//각기능들 모아놓음
$(document).ready(function(){
	
	//등록하기버튼시 호출
	$("#insertBtn").unbind().on("click", function(){
		var check = emptyChk();
		if(check){
			var inChk = confirm("등록하시겠습니까?");
			if(inChk){
				$.ajax({
					url: "./boardInsertOk",
					type:"POST",
					data: $("#insertFrm").serialize(),
					success: function(data){
							alert("성공적으로 등록되었습니다.");
							location.href =  "./boardList";
						},
						error: function(){  
							alert("등록 실패");  
						}
					});
				}else{
					return false;
				}
		}
	});
	
	//수정완료누르면 호출
	$("#upBtn").on("click", function(){
		var chek = emptyChk();
		if(chek){
			var uptChk = confirm("수정하시겠습니까?");
			if(uptChk){
				$.ajax({
					url:  "./boardUpdateOk",
					type:"POST",
					data: $("#upFrm").serialize(),
					success: function(data){
						alert("성공적으로 수정되었습니다.");
						location.href = "./boardList";
					},
					error: function(){  
						alert("수정 실패");  
					}
				});
			}else{
				return false;		
			}
		}else{
			return false;
		}
	});
	
	//삭제버튼시 호출
	$("#delBtn").on("click", function(memberidx){
		var boardIdx = $("#boardIdx").val();
		var delChk = confirm("정말로 삭제하시겠습니까?");
		if(delChk){
			$.ajax({
				url:  "./boardDelete",
				data: "boardIdx="+ boardIdx,
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
	
	$("#boardContent").keyup(function(e){
			var content = $(this).val();
			
			// 글자수 카운트
			if(content.length == 0 || content == ""){
				$(".textCnt").text("0");
			}else{
				$(".textCnt").text(content.length);
			}
		
		// 글자수 제한
		if(content.length >= 500){
			$(this).val($(this).val().substring(0, 500));
			alert("글자수는 500자까지 입력 가능합니다.");
		}
	});
	
	
	
//댓글등록	
	$("#replyInsert").click(function(){
	   var bno = $('input[name=bno]').val();
		var replyText = $("#replyText").val();
		var writer = $("#loginId").val();
		var param = {"replyText" : replyText, "writer":writer, "bno":bno};
		
		$.ajax({
			type: "post",
			contentType : 'application/json',
			url:"./replyInsert",
			async:false,
			data:JSON.stringify(param),
			success: function(data){
				alert("댓글작성이 완료되었습니다.");
				$('#replyText').val(""); // 초기화
				replyList();
				
			}
		})
		
	});
        
})



//댓글조회
	function replyList(){
		var bno = $("#bno").val();
		var param = { "bno" : bno};
		$.ajax({
			type:'post',
			url:"./replyList",
			contentType : 'application/json',
			dataType:'JSON',
			data:JSON.stringify(param),
			success: function(data) {
				console.log(data);
				getReplyList(data);
			},
			error: function(result){
			},
			complete: function(){
			}
		})
	}
//댓글목록에 뿌려주기
function getReplyList(data){
	var replyList = data.replyList;
	var sessionId = $("#loginId").val();
	var replyListHtml = $("#replyList");
	replyListHtml.html(''); // 초기화
		
	for(var i=0; i<replyList.length; i++){
		if (sessionId == replyList[i].replyer) {
      replyListHtml.append($('<input/>', {type: "hidden", name:"rno", value:replyList[i].rno}))
             .append("<div class='topAppend' style='border: 1px solid; border-right:1px solid; '>")
             .append('<div style="text-align:left; margin-bottom:10px;"> 작성자 : '+replyList[i].replyer+ ' | 등록일 : '+replyList[i].regDate+' </div>')
             .append('<button style="float:right; border-radius: 10px; border: 5px solid #F4E8F6; background-color:white; cursor:pointer" onclick="repDelBtn('+replyList[i].rno+')">삭제하기</button>')
             .append('<button onclick="repUpBtn('+ replyList[i].rno +')" style="float:right; border-radius: 10px; border: 5px solid #F4E8F6; background-color:white; cursor:pointer">수정하기</button>')
             .append('<div style="text-align:left;">' +replyList[i].replyText + ' </div>')
             .append("</div><br><br>");
    }else{
      replyListHtml.append($('<input/>', {type: "hidden", name:"rno", value:replyList[i].rno}))
            .append("<div class='topAppend' style='border: 1px solid ;'>")
            .append("<div style='text-align:left; margin-bottom:10px; '> 작성자 :"+replyList[i].replyer+" | 등록일 :"+replyList[i].regDate+"</div>")
            .append("<div style='text-align:left; padding-left: 15px;'>"+replyList[i].replyText+"</div>")
            .append("</div><br><br>");
      }
  }
}
//댓글수정
function repUpBtn(rno) {
	console.log("rno :", rno);

	
	var bno = $("#bno").val();
	var param = { "bno" : bno};
	$.ajax({
		type:'post',
		url:"./replyList",
		contentType : 'application/json',
		dataType:'JSON',
		data:JSON.stringify(param),
		success: function(data) {
			var replyList = data.replyList;
	var sessionId = $("#loginId").val();
	var replyListHtml = $("#replyList");
	replyListHtml.html(''); // 초기화
	console.log(data);

	// .append('<textarea>' +replyText + ' </textarea>')
	for(var i=0; i<replyList.length; i++){
		if (replyList[i].rno === rno) {
			replyListHtml.append($('<input/>', {type: "hidden", name:"rno", value:replyList[i].rno}))
				 .append("<div class='topAppend' style='border: 1px solid;'>")
				 .append('<div style="text-align:left; margin-bottom:10px; "> 작성자 : '+replyList[i].replyer+ ' | 등록일 : '+replyList[i].regDate+' </div>')
				 .append('<button onclick="repDeleteBtn('+replyList[i].rno+')" style="float:right; border-radius: 10px; border: 5px solid #F4E8F6; background-color:white; cursor:pointer;">수정취소</button>')
				 .append('<button onclick="repModify('+ replyList[i].rno +' , \''+ replyList[i].replyText +'\')" style="float:right; border-radius: 10px; border: 5px solid #F4E8F6; background-color:white; cursor:pointer;">수정완료</button>')
				 .append('<textarea id="modifyText" style="text-align:left; padding-left: 15px; border: 1px solid; height:70px; width:100%;">' +replyList[i].replyText + ' </textarea>')
				 .append("</div><br><br>");
		}else{
			replyListHtml.append($('<input/>', {type: "hidden", name:"rno", value:replyList[i].rno}))
             .append("<div class='topAppend' style='border: 1px solid;'>")
             .append('<div style="text-align:left; margin-bottom:10px;"> 작성자 : '+replyList[i].replyer+ ' | 등록일 : '+replyList[i].regDate+' </div>')
             .append('<button style="float:right; border-radius: 10px; border: 5px solid #F4E8F6; background-color:white; cursor:pointer" onclick="repDelBtn('+replyList[i].rno+')">삭제하기</button>')
             .append('<button style="float:right; border-radius: 10px; border: 5px solid #F4E8F6; background-color:white; cursor:pointer" onclick="repUpBtn('+ replyList[i].rno +' , \''+ replyList[i].replyer +'\', ' + replyList[i].regDate + ' , \''+ replyList[i].replyText +'\', \''+ replyListHtml +'\')">수정하기</button>')
             .append('<div style="text-align:left; padding-left: 15px;">' +replyList[i].replyText + ' </div>')
             .append("</div><br><br>");
		}

		}

		},
		error: function(result){
		},
		complete: function(){
		}
	})
	
}

//여기 컨트롤러 넘기는 수정함수
function repModify(rno, replyText) {
	//console.log("check");
	console.log("rno:", rno);
	console.log("replyText:", replyText);
	 var replyText=$("#modifyText").val();
	 var param = {"rno":rno, "replyText":replyText};
	 //var upChk = confirm("정말로 수정하시겠습니까?");
 		$.ajax({
 			url:"./replyUpdate",
 			contentType : 'application/json',
			dataType:'JSON',
			data:JSON.stringify(param),
 			type: "POST",
 			success: function(data){
 				alert("성공적으로 수정되었습니다.");
 				
 				 replyList();
 			},
 			error: function(){  
 				alert("수정 실패");
 				//consol.log(param);  
 			}
 		})
}

//수정취소
function repDeleteBtn(rno){
	location.reload();
}

//댓글 삭제
function repDelBtn(test){
		
		//var rno = $("[name=rno]").val();
		var rno = test;

		var delChk = confirm("정말로 삭제하시겠습니까?");
		if(delChk){
			$.ajax({
				url:  "./replyDelete",
				data: "rno=" +rno,
				type: "post",
				success: function(data){
					alert("성공적으로 삭제되었습니다.");
					location.href ="./boardList";
				},
				error: function(){  
					alert("삭제 실패");  
				}
			})
	}
}

//절대경로
function getContextPath(){
	var hostIndex = location.href.indexOf( location.host ) + location.host.length;	
	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
}
