<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="top-bar">
	<div class="content">
		<img src="resources/images/icons.png" class="top-left logo" alt="logo" onClick="goHome()"/>
		<h2 class="top-left">Web Board</h2>
		<nav class="top-right">
			<ul>
				<li class="after" id="mname"></li>
				<li class="after"><a href="logout">Logout</a></li>
				<li class="before"><a href="loginForm">Login</a></li>
				<li class="before"><a href="joinForm">Join</a></li>
			</ul>
		</nav>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	//로그인 관련 출력(header.jsp)
	let identy = "${member.m_name}";
	if(identy){
		$("#mname").html(identy+"님");
		//$(".after").css("display","block");
		//로그인, 회원가입 메뉴 숨김
		$(".before").css("display","none");
	}else{
		$(".after").css("display","none");
		//$(".before").css("display","block");
	}

});

//현재 URL의 루트 디렉터리로 리디렉션
function goHome() {
	var id ="${member.m_id}"
	if(!id){
		location.href="./";//로그인 전에는 home으로..
	}else if(id){
		location.href="boardList";//로그인후 목록으로..		
	}
}
</script>