<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="cnt" class="com.lec.beans.CountBean" scope="session"></jsp:useBean>
<jsp:setProperty name="cnt" property="count" />
<%-- cnt.setCount (Integer. parseInt(request.getParameter("count")) --%>

<h3>session1<br>cnt와 getCount 호출</h3>
<jsp:getProperty property="count" name="cnt"/>
<%--  cnt.getCount()--%>

<a href="scope3_session2.jsp">session2로...</a> 