package com.lec.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import common.D;

public class CategoryDAO {
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;   // SELECT 결과, executeQuery()
	
	// DAO 객체가 생성될때 Connection 도 생성된다.
	public CategoryDAO() {
		
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			System.out.println("WriteDAO 생성, 데이터 베이스 연결!");
		} catch(Exception e) {
			e.printStackTrace();
			// throw e;
		}		
		
	} // 생성자

	// DB 자원 반납 메소드,
	public void close() throws SQLException {
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
	}
	
	public CategoryDTO [] createArray(ResultSet rs) throws SQLException {
		CategoryDTO [] arr = null;  // DTO 배열
		
		ArrayList<CategoryDTO> list = new ArrayList<CategoryDTO>();
		
		while(rs.next()) {
			int uid = rs.getInt("ca_uid");
			String name = rs.getString("ca_name");
			int depth = rs.getInt("ca_depth");
			int parent = rs.getInt("ca_parent");
			int order = rs.getInt("ca_order");
			
		
			CategoryDTO dto = new CategoryDTO(uid, name, depth, parent, order);
			
			list.add(dto);
			
		} // end while
		
		int size = list.size();
		
		if(size == 0) return null;
		
		arr = new CategoryDTO[size];
		list.toArray(arr);  // List -> 배열		
		return arr;
	}

	
	
	public CategoryDTO [] select(int a, int b) throws SQLException{
		CategoryDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_CATEGORY_BY_DEPTH_N_PARENT);
			pstmt.setInt(1,  a);
			pstmt.setInt(2, b);
			rs = pstmt.executeQuery();
			arr = createArray(rs);	
		} finally {
			close();
		}
		
		return arr;
	}
	
	
	
	
	
	
}
