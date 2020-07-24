<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 


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
<title>수정 ${list[0].subject}</title>
</head>

<body>
<h2>수정</h2>

<form name="frm" action="updateOk.do" method="post" onsubmit="return chkSubmit()">
<input type="hidden" name="uid" value="${list[0].uid}"/>

카테고리:
<select name="category">
<c:if test="${list[0].category eq '공지사항'}">
	<option>공지사항</option>
</c:if>
	<option>기업후기</option>
	<option>면접후기</option>
	<option>정보공유</option>
</select>
제목:
<input type="text" name="subject" value="${list[0].subject}"/><br>
내용:<br>
<textarea name="content" value="${list[0].content}"></textarea><br>
유저:  ${list[0].writeName } <br>

회사:

<br><br>
 <select name="c_uid">
 <c:forEach var="a" items="${comlist}" varStatus="status">
				<option value = "${a.c_uid }">${a.companyName }</option> 
			</c:forEach>
	

</select>
<br><br>
<input type="submit" value="수정"/>
</form>
<br>
<button onclick="history.back()">이전으로</button>
<button type="button" onclick="location.href='list.do'">목록으로</button>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

</body>
</html>
</c:otherwise>
</c:choose>














