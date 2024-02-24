<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시글 목록</title>
<link rel="stylesheet" href="resources/css/style.css">
<script src="resources/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	let msg = "${msg}";
	if(msg) alert(msg);
	console.warn(msg);
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
		<div class="board-form">
			<div class="user-info">
				<div class="user-info-sub">
					<p class="grade">등급 [${member.g_name}]</p>
					<p class="point">POINT [${member.m_point}]</p>
				</div>
			</div>
			<h2 class="login-header">게시글 목록</h2>
			<div class="data-area">
				<div class="title-row">
					<div class="t-no p-10">번호</div>
					<div class="t-title p-30">제목</div>
					<div class="t-name p-15">작성자</div>
					<div class="t-date p-30">작성일</div>
					<div class="t-view p-15">조회수</div> 
				</div>
			</div>
			<c:forEach var="bitem" items="${bList}">
			<div class="data-row">
				<div class="t-no p-10">${bitem.bnum}</div>
				<!-- 상세보기 화면 url + 글번호 -->
				<div class="t-title p-30"><a href="boardContents?bnum=${bitem.bnum}">${bitem.btitle}</a></div>
				<div class="t-name p-15">${bitem.mname}</div>
				<div class="t-date p-30"><fmt:formatDate value="${bitem.bdate}" pattern="yyyy-MM-dd-E HH:mm:ss" /></div>
				<div class="t-view p-15">${bitem.bviews}</div>
			</div>
			</c:forEach>
			<div class="btn-area">
				<div class="paging">${paging}</div>
				<button class="wr-btn" onclick="location.href='writeForm'">글쓰기</button>
			</div>
		</div>
	</div>
	</section>
	
	<footer>
	<jsp:include page="footer.jsp"/>
	</footer>
</div>
</body>
</html>
