package com.lec.sts16_interceptor.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 컨트롤러 실행 전 ( Handler 실행 전)
		
		// session 객체 가져오기
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		System.out.println("[preHandle]" + id);
		
		if (id == null) { // 로그인 상태가 아니다
			
			// 직전 요청 url 을 세션에 기록한다.
			String urlPrior = 
					request.getRequestURL().toString() + "?" + request.getQueryString(); // parameter들
			request.getSession().setAttribute("url_prior_login", urlPrior); // 세션에 저장한다.
			
			response.sendRedirect(request.getContextPath() + "/user/login"); // 로그인 상태가 아니라면 로그인 페이지로 redirect
			return false; // 더 이상 컨트롤러가 진행하지 않도록 false 리턴
		}
		
		return true; // true 리턴하면 컨트롤러 핸들러 진행
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러 수행 후 ( Handler 실행 직후) , 뷰 직전
		System.out.println("[postHandle]");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 뷰 response 후
		System.out.println("[afterCompletion]");
		super.afterCompletion(request, response, handler, ex);
	}
  
}
