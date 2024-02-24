<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="resources/js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="resources/css/style.css">
<script type="text/javascript">
	var check = "${msg}";
	
	if(check){
		alert(check);
	}
	
	function checked(){
		const form = document.forms.joinForm;
		const length = form.elements.length -1;
		for(var i=0; i<length; i++){
			if(form[i].type !== "button" && !form[i].value){
					alert(form[i].title+"를 입력하세요");
					form[i].focus();
					return false;
			}
		}
		return true;
	}
	//아이디 중복확인
	function idcheck(){
		const id = document.querySelector("#mid");
		if(!id.value){
			alert("아이디를 입력하세요!");
			return;
		}
		const currId = {id : id.value};
		$.ajax({
			url : "idCheck",
			type : "GET",
			data : currId,
			success : function(result){
				console.log("success : "+result);
				const msg = (result) ? "중복된 아이디입니다. " : "사용가능한 아이디입니다. ";
				alert(msg+id.value);
				if(result){
					id.value = "";
					id.focus();
				}
			},
			error : function(error){
				console.log(error);
			}
		});
	}
</script>
</head>
<body>
<div class="wrap">
<header>
<jsp:include page="header.jsp"/>
</header>
<section>
<div class="content">
<form name="joinForm" action="memberInsert" method="post" onsubmit="return checked()" class="login-form">
<h2 class="login-header">회원 가입</h2>
<input type="text" class="login-input" id="mid" name="m_id" autofocus placeholder="아이디" title="아이디">
<input type="button" class="idcheck-btn" value="중복확인" onclick="idcheck()">
<input type="password" class="login-input" id="" name="m_pwd" placeholder="패스워드" title="패스워드">
<input type="text" class="login-input" name="m_name" placeholder="이름" title="이름">
<input type="text" class="login-input" name="m_birth" placeholder="생일" title="생일">
<input type="text" class="login-input" name="m_addr" placeholder="주소" title="주소">
<input type="text" class="login-input" name="m_phone" placeholder="연락처" title="연락처">
<input type="submit" class="login-btn" value="회원가입">
</form>
</div>
</section>
<footer>
<jsp:include page="footer.jsp"/>
</footer>
</div>
</body>
</html>
