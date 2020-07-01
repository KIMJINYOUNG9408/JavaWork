package com.lec.sts15_mybatis.board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.jdbc.core.JdbcTemplate;

public class C {
	// 스프링 컨테이너에 생성된 JdbcTemplate 을 받아와서
	// 언제든지 원할때마다 가져다 쓸수 있도록 public static 으로 설정
	
	
	// MyBatis 용 SqlSession
	public static SqlSession sqlSession;
	


	
	
}
