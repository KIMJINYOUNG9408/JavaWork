<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.lec.beans.WriteDTO" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EL request</title>
</head>
<body>
<%
	request.setAttribute("myage", 27);
	request.setAttribute("mydto", new WriteDTO(100, "제목", "내용", "작성자", 3));
	pageContext.setAttribute("myage", "흥~!");
%>

${myage }<br>
${requestScope.myage }<br>

${mydto} <br> <%-- toString() 값. --%>
<%= ((WriteDTO)request.getAttribute("mydto")).getUid() %><br>
<%= ((WriteDTO)request.getAttribute("mydto")).getSubject() %><br>
<%= ((WriteDTO)request.getAttribute("mydto")).getContent() %><br>
${mydto.uid }<br>
${mydto.subject}<br>
${mydto.content } <br>

</body>
</html>