<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>게시판 홈</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="resources/js/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<link rel="stylesheet" href="resources/css/style.css">
<script type="text/javascript">
$(function(){
	var check = "${msg}";
	
	if(check){
		alert(check);
		location.reload(true);
	}
	
	$('.slider').bxSlider({
		auto: true,
		slideWidth: 500,
	});
	
	var mql = window.matchMedia("screen and (max-width: 768px)");
	mql.addListener(function(e){
		if(!e.matches){
			slider.reloadSlider();
		}
	});
});

</script>
</head>
<body>
<div class="wrap">
<header>
<jsp:include page="header.jsp"/>
</header>
<section>
	<div class="content-home">
		<div class="slider">
			<div><img src="resources/images/arin_a1.jpg"></div>
			<div><img src="resources/images/arin_a2.jpg"></div>
			<div><img src="resources/images/arin_a3.jpg"></div>
			<div><img src="resources/images/arin_a4.jpg"></div>
			<div><img src="resources/images/arin_b1.jpg"></div>
			<div><img src="resources/images/arin_b2.jpg"></div>
			<div><img src="resources/images/arin_b3.jpg"></div>
			<div><img src="resources/images/arin_c1.jpg"></div>
			<div><img src="resources/images/arin_c2.jpg"></div>
			<div><img src="resources/images/arin_c3.jpg"></div>
			<div><img src="resources/images/arin_c4.jpg"></div>
		</div>
	</div>
</section>
<footer>
<jsp:include page="footer.jsp"/>
</footer>
</div>
</body>
</html>
