<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="resources/js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="resources/css/style.css">
<script type="text/javascript">
$(document).ready(function(){
	let msg = "${msg}";
	if(msg) alert(msg);
	msg = "";
});
</script>
</head>
<body>
<div class="wrap">
<header>
<jsp:include page="header.jsp"/>
</header>
<section>
<div class="content">
<form name="loginForm" action="loginProc" method="post" class="login-form">
<h2 class="login-header">로그인</h2>
<input type="text" class="login-input" id="mid" name="m_id" autofocus placeholder="아이디" title="아이디" required>
<input type="password" class="login-input" id="" name="m_pwd" placeholder="패스워드" title="패스워드" required>
<input type="submit" class="login-btn" value="로그인">
</form>
</div>
</section>
<footer>
<jsp:include page="footer.jsp"/>
</footer>
</div>
</body>
</html>
