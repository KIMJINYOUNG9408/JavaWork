package com.lec.sts14_transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TicketDAO2 {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	// 트랜잭션 처리 객체 (인터페이스)
	// PlatformTransactionManager (I)
	//   └─ AbstractPlatformTransactionManager (추상클래스)
	//        └─  ...  다양한 클래스 (DB 종류, 환경에 따른 객체들이 정의되어 있다)
	PlatformTransactionManager transactionManager;	
	
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public TicketDAO2() {
		System.out.println(template);
	}
	
	// 티켓 구매
	public void buyTicket(final TicketDTO dto) {
		System.out.println("buyTicket()");
		System.out.println("user ID : " + dto.getUserId());
		System.out.println("ticket count : " + dto.getTicketCount());
				
		// TransactionDefiniton : 트랜잭션 구동을 위한 기본 객체, ( 기본 설정 값 갖고 있다 )
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		// 카드 결제
		try {
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String query = "INSERT INTO test_card VALUES (?, ?)";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, dto.getUserId());
				pstmt.setInt(2, dto.getTicketCount());
				return pstmt;
			}
		});
		
		// 티켓 발권
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String query = "INSERT INTO test_ticket VALUES (?, ?)";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, dto.getUserId());
				pstmt.setInt(2, dto.getTicketCount());
				return pstmt;
			}
		});
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
		
		}

}
