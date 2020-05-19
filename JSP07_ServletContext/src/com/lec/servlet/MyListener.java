package com.lec.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
        
	@Override // 웹 어플리케이션 (컨텍스트) 종료할때 호출
	public void contextDestroyed(ServletContextEvent sce) {
		
		System.out.println("JSP07 어플리케이션 종료");
		ServletContextListener.super.contextDestroyed(sce);
	}
	
	@Override // 웹 어플리케이션 (컨텍스트) 시작할때 호출 
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("JSP07 어플리케이션 시작");
		ServletContextListener.super.contextInitialized(sce);
	}
	
}
