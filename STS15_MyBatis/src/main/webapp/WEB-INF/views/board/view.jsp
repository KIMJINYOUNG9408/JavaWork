<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="${pageContext.request.contextPath }/JS/board.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />



<c:choose>
	<c:when test="${empty list || fn:length(list) == 0 }">	
			<script>
				alert("해당 정보가 삭제되거나 없습니다");
				history.back();
			</script>	
	</c:when>
	
	<c:otherwise>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>읽기${list[0].subject}</title> <!-- title에 글제목 넣기 -->
</head>
<script>
function chkDelete(uid){
	// 삭제 여부, 다시 확인 하고 진행하기
	var r = confirm("삭제하시겠습니까?");
	if(r){
		location.href = 'deleteOk.do?uid=' + uid;
	}
}
</script>
<body>
<h2>읽기${list[0].subject}</h2>
<br>
<div id="replycnt"></div>
회사명 : ${list[0].companyName}<br>
<div id="a">${list[0].uid}</div><br>
작성자 : ${list[0].writeName}<br>
카테고리 ${list[0].category}<br>
제목 : ${list[0].subject}<br>
등록일 : ${list[0].regDate}<br>
조회수 : ${list[0].viewCnt}<br>
내용:  ${list[0].content}<br>
<div class="row">
	<div class="col-xs-5">
		<input type="text" id="copy_text_input" value="" class="form-control">
	</div>
	<div class="col-xs-2">
		<button type="button" id="copy_btn" class="btn btn-info">ㄴ클립보드 복사ㄱ</button>
	</div>
</div>


<hr>
<div>
${list[0].content}
</div>
<hr>
<br>
<button onclick="location.href='update.do?uid=${list[0].uid}'">수정하기</button>
<button onclick="location.href = 'list.do'">목록보기</button>
<button onclick="chkDelete(${list[0].uid})">삭제하기</button>
<button onclick="location.href = 'write.do'">신규등록</button>

<h3>댓글입니다 밑에는</h3>
<h3>댓글작성</h2>
<form id="frmWrite" name="frmWrite" method="post" >
<input type="hidden" name="buid" value="${list[0].uid }">
<input type="hidden" name="cuid" value="1">
<textarea name="content" placeholder="댓글을 작성해주세요"></textarea>
<button type="submit">제출</button>
</form>




<h3>댓글목록</h3>
<div id="list">
<form id="frmList" name="frmList">
		<table>
			<thead>
				<th>#</th>
				<th>작성자</th>
				<th>내용</th>
				<th>작성일</th>
				<th>수정</th>
				<th>삭제</th>
			</thead>
			
			<tbody>
			
			</tbody>
		</table>
	</form>

	</div>
	

</body>
</html>

	</c:otherwise>
</c:choose>

















