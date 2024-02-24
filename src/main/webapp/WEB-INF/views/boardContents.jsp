<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>상세 보기</title>
<link rel="stylesheet" href="resources/css/style.css">
<script type="text/javascript" src="resources/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	//컨트롤러에서 전달하는 메시지 출력.
	var msg = "${msg}";
	
	if(msg){
		alert(msg);
		//location.reload(true);//페이지 새로 고침(안씀!)
	}
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
		<div class="write-form">
			<div class="user-info">
				<div class="user-info-sub">
					<p class="grade">등급 [${member.g_name}]</p>
					<p class="point">POINT [${member.m_point}]</p>
				</div>
			</div>
			<h2 class="login-header">상세 보기</h2>
			<!-- 게시글 상세 내용 출력(table) -->
			
			<!-- 댓글 작성 양식(table) -->
			
			<!-- 댓글 목록 출력 부분(table) -->
		</div>
	</div>
	</section>
	<footer>
	<jsp:include page="footer.jsp"/>
	</footer>
</div>
</body>

</html>
