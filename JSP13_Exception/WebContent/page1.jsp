<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- page 지시자 태그를 사용한 예외 처리  --%>
<%@ page errorPage="error.jsp" %>  <%-- 에러가 발생하면 error.jsp 로 넘어갑니다. --%>  
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
<%= request.getParameter("id").toUpperCase() %>

</body>
</html>