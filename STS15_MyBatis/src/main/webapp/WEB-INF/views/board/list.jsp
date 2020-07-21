<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<%-- JSTL 버전으로 바뀌니, import 번잡함도 사라진다. JAVA 변수 선언도 사라진다. --%>   
 

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<title>글 목록</title>
<style>
table {width: 100%;}
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

.gong {
	color:red;
}
</style>
</head>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$(".btnCategory").click(function(){
		location.href = "?category="+$(this).val();
	}) // p 가 클릭되면 매개변수의 함수 수행
});
</script>
<body>
${page }
		<hr>
		<h2>리스트</h2>
		<ul class="nav nav-pills nav-fill">
					<li class="nav-item btnCategory" value="1"><a
						class="nav-link list-group-item list-group-item-action list-group-item-light"
						href="#">전체목록</a></li>
					<li class="nav-item btnCategory" value="2"><a
						class="nav-link list-group-item list-group-item-action list-group-item-light"
						href="#">기업후기</a></li>
					<li class="nav-item btnCategory" value="3"><a
						class="nav-link list-group-item list-group-item-action list-group-item-light"
						href="#">면접후기</a></li>
					<li class="nav-item btnCategory" value="4"><a
						class="nav-link list-group-item list-group-item-action list-group-item-light"
						href="#">정보공유</a></li>
					
				</ul>
		
		
		<table>
			<tr>
				<th>UID</th>
				<th>기업명</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>등록일</th>
				<th>분류</th>
			</tr>
			
			<c:choose>
			<c:when test="${page == '1' }">	
			<c:forEach var="noti" items="${notice}">
				<tr class="gong">
				<td>${noti.uid }</td>
				<td>-</td>
				<td><a class="gong" href="view.do?uid=${noti.uid }">${noti.subject }</a></td>
				
				<td>ADMIN</td>			
				<td>${noti.viewCnt }</td>
				<td>${noti.regDate }</td>
				<td>${noti.category}</td>
			</tr>
			
			</c:forEach>
			</c:when>
			<c:otherwise>
			

			
			
			</c:otherwise>
		</c:choose>
			
 
		<c:choose>
			<c:when test="${empty list || fn:length(list) == 0 }">			
			</c:when>
			<c:otherwise>
			
			<c:forEach var="dto" items="${list}">
				<tr>
				<td>${dto.uid }</td>
				<td>${dto.companyName }</td>
				<td><a href="view.do?uid=${dto.uid }">${dto.subject }</a></td>
				
				<td>${dto.writeName }</td>			
				<td>${dto.viewCnt }</td>
				<td>${dto.regDate }</td>
				<td>${dto.category}</td>
			</tr>
			
			</c:forEach>
			
			
			</c:otherwise>
		</c:choose>
		
			

		</table>
		
		
				<div style="background-color: #eee; padding: 10px;">
				<c:if test="${cnt > 1 }">
					<jsp:include page="pagination.jsp">
						<jsp:param value="${5 }" name="writePages" />
						<jsp:param value="${cnt }" name="totalPage" />
						<jsp:param value="${page }" name="curPage" />
					</jsp:include>

					<hr>
				</c:if>
					<div style="width: 50%; margin: 0 auto; text-align: center">
						
						<form name='frm' method='get' action='list.do'
							style="display: block; margin-top: 5px;">
							<div class="input-group">
							<select name='col' class="custom-select custom-select-sm"
								style="width: auto; float: left; margin-bottom:5px">
								<!-- 검색 컬럼 -->
								<option value='none' selected>작성자&기업명</option>
								<option value='company'>기업명</option>
								<option value='subject'>제목</option>
							</select>
							
								<input type="text" class="form-control" name='word' value=''
									placeholder="특수문자 입력불가" aria-label="특수문자 입력 불가"
									aria-describedby="button-addon2">
								<div class="input-group-append">
									<button class="btn btn-outline-secondary" type="submit"
										id="button-addon2">검색</button>
								</div>
							</div>
						</form>
					</div>
				</div>
		<br>
		<button onclick="location.href='write.do'">신규등록</button>


</body>
</html>















