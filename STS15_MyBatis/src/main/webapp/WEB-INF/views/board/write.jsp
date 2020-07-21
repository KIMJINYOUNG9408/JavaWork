<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글작성</title>
</head>
<script>
function chkSubmit(){  // 폼 검증
	
	return true;
}

</script>
<body>
<h2>글작성</h2>


			
			

<%-- 글 내용이 많을수 있기 때문에 POST 방식 사용 --%>
<form name="frm" action="writeOk.do" method="post" onsubmit="return chkSubmit()">
카테고리:
<select name="category">
	<option>기업후기</option>
	<option>면접후기</option>
	<option>정보공유</option>
</select>
제목:
<input type="text" name="subject"/><br>
내용:<br>
<textarea name="content"></textarea><br>
유저:
<input type="text" name="u_uid"/><br>
회사:

<br><br>
 <select name="c_uid">
 <c:forEach var="a" items="${comlist}" varStatus="status">
				<option value = "${a.c_uid }">${a.companyName }</option> 
			</c:forEach>
	

</select>
<br><br>
<input type="submit" value="등록"/>
</form>
<br>
<button type="button" onclick="location.href='list.do'">목록으로</button>


<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>















