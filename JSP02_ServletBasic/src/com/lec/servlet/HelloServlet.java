package com.lec.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/He")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public HelloServlet() { 
        super();
       
    }
	
   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  // request 할때.
		System.out.println("안녕하세요 첫번째 Servlet 입니다.");
		
		// 서블릿으로 HTML 문서 response 하기
		// 1. content type 설정
		// 2. PrintWriter 객체 생성 <-- response 객체로부터
		// 3. PrintWriter 객체로 HTML 스트림에 쓰기(write) .
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang='ko'>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<title>서블릿 response</title>");
		out.println("<body>");
		out.println("<h2>HTML 문서 response</h2>");
		out.println("</body>");
		out.println("</head>");
		out.println("</html>");
		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
