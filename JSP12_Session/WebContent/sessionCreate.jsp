<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.text.*" %>
<%@ page import = "java.util.*" %>

<%
	String sessionName1 = "num1";
	String sessionValue1 = "" + (int)(Math.random() * 10);
	
	// 세션 생성
	session.setAttribute(sessionName1, sessionValue1);
	
	String sessionName2 = "datetime";
	String sessionValue2  = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
	session.setAttribute(sessionName2, sessionValue2);
%>    

<script>
alert("<%= sessionName1 %> , <%= sessionName2 %> 세션 생성");
location.href = "sessionList.jsp";
</script>