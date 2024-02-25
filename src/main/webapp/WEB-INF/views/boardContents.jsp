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
			<table cellpadding="0" cellspacing="0" border="1">
				<tr height="30">
					<td width="100" bgcolor="pink" align="center">글번호</td>
					<td colspan="5">${boardContent.bnum}</td>
				</tr>
				<tr height="30">
					<td width="100" bgcolor="pink" align="center">글쓴이</td>
					<td width="150">${boardContent.mname}</td>
					<td width="60" bgcolor="pink" align="center">날짜</td>
					<td width="200">
						<fmt:formatDate value="${boardContent.bdate}" pattern="yyyy-MM-dd-E HH:mm:ss"/>
					</td>
					<td width="70"bgcolor="pink" align="center">조회수</td>
					<td width="100">${boardContent.bviews}</td>
				</tr>
				<tr height="30">
					<td width="100" bgcolor="pink" align="center">제목</td>
					<td colspan="5">${boardContent.btitle}</td>
				</tr>
				<tr height="200">
					<td width="100" bgcolor="pink" align="center">내용</td>
					<td colspan="5" bgcolor="white" >${boardContent.bcontents}</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="5" bgcolor="white" >
						<!-- 첨부파일 없을시 -->
						<c:if test="${empty boardFile}">첨부된 파일이 없습니다.</c:if>
						<!-- 첨부파일 있을시 -->
						<c:if test="${!empty boardFile}">
							<c:forEach var="bfile" items="${boardFile}">
								<span class="file-title">${bfile.bf_oriname}</span>
							</c:forEach>
						</c:if>
					</td>
				</tr>
			</table>
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
