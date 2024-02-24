<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>글쓰기</title>
<link rel="stylesheet" href="resources/css/style.css">
<script type="text/javascript" src="resources/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
/* $(function(){
	var identy = "${member.m_name}";
	$("#mname").html(identy + "님");
	$(".after").css("display", "block");
	$(".before").css("display", "none");
	 */
	//컨트롤러에서 전달하는 메시지 출력.
	var msg = "${msg}";
	
	if(msg != ""){
		alert(msg);
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
		<form action="writeProc" class="write-form"
			method="post" enctype="multipart/form-data">
			<div class="user-info">
				<div class="user-info-sub">
					<p class="grade">등급 [${member.g_name}]</p>
					<p class="point">POINT [${member.m_point}]</p>
				</div>
			</div>
			<h2 class="login-header">글쓰기</h2>
			<input type="hidden" name="bid" value="${member.m_id}">
			<input type="text" class="write-input"
				name="btitle" autofocus placeholder="제목"
				required>
			<textarea rows="15" name="bcontents"
				placeholder="내용을 적어주세요..."
				class="write-input ta"></textarea>
			<div class="filebox">
				<label for="file">업로드</label>
				<input type="file" name="files" id="file" multiple>
				<input class="upload-name" value="파일선택"
					readonly>
				<!-- 업로드할 파일이 있으면 1, 없으면 0. 자바스크립트로 value 값을 변경 -->
				<input type="hidden" id="fileCheck" value="0"
					name="fileCheck">
			</div>
			<div class="btn-area">
				<input class="btn-write" type="submit" value="Write">
				<input class="btn-write" type="reset" value="Reset">
				<input class="btn-write" type="button" value="Back"
					onclick="location.href='boardList?pageNum=${pageNum}'">
			</div>
		</form>
	</div>
	</section>
	
	<footer>
	<jsp:include page="footer.jsp"/>
	</footer>
</div>
</body>
<script type="text/javascript">
//파일 업로드 용 자바스크립트
//업로드 버튼 클릭하여 파일 선택창이 뜨면, 업로드할 파일을 선택하게 되는데
//이 때 선택한 파일이름을 class가 'upload-file'인 input 태그에 출력.
//fileCheck의 value를 0 -> 1로 바꿈.
//fileCheck는 서버스에서 값을 확인하여 1일 경우 파일 업로드 처리 과정을 수행하고,
//0일 경우 업로드 과정을 처리하지 않도록 분기 하는데 사용하는 값.
//선택한 요소에 이벤트 핸들러를 연결하는 데 사용되는 jQuery 메서드(on)
$("#file").on("change",function(){
	//파일창으로 선택한 파일 목록 가져오기
	let files = $("#file")[0].files;
	console.log(files);
	
	let fileName ="";
 	for(let i=0; i<files.length; i++){
		fileName += files[i].name;//이름 뒤에 콤마구분.
		if(files.length!==1 && i !== files.length-1) fileName += ", ";
	}
	//if(files.length!==0)fileName = files[0].name + " 외 "+ (files.length -1) +"개";
	
	console.log(fileName);
	
	$(".upload-name").val(fileName);
	
	//fileCheck의 value 변경( 0 -> 1)
	if(!fileName){//값이 없을떄(파일선택 취소)
		$("#fileCheck").val(0);
		$(".upload-name").val("파일선택");
	}
	else {//파일을 선택한 경우
		$("#fileCheck").val(1);
	}
});
</script>
</html>

