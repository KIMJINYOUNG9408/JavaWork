package com.lec.java.db03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lec.java.db.Query;

// 공통적으로 사용하는 상수들 인터페이스에 담아서 처리.
public class DB03Main implements Query {

	public static void main(String[] args) {
		System.out.println("DB 3 - PreparedStatement");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			System.out.println("드라이버 로딩 성공");

			conn = DriverManager.getConnection(URL, USER, PASSWD);
			System.out.println("Connection 성공");

			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setInt(1, 10); // 첫번째 ? 는 1부터 시작 !
			pstmt.setString(2, "헐크");
			pstmt.setString(3, "2000-10-10");
			int cnt = pstmt.executeUpdate(); // DML
			System.out.println(cnt + "개 행 (row) INSERT 성공");

			pstmt.close();
			System.out.println();

			System.out.println("UPDATE");
			pstmt = conn.prepareStatement(SQL_UPDATE_BIRTHDATE);
			pstmt.setString(1, "2020-01-01");
			pstmt.setInt(2, 10);
			cnt = pstmt.executeUpdate();
			System.out.println(cnt + " 개 행 (row) UPDATE 성공");

			pstmt.close();
			System.out.println();

			System.out.println("SELECT");
			pstmt = conn.prepareStatement(SQL_SELECT_ALL);

			rs = pstmt.executeQuery();
            int k =0;
			while (rs.next()) {
				String no = rs.getString(COL_LABEL_NO);
				String name = rs.getString(COL_LABEL_NAME);
				String birthdate = rs.getString(COL_LABEL_BIRTHDATE);
				System.out.println(no + " | " + name + " | " + birthdate);
			
			}
		
		
		
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	} // end main()

} // end class DB03Main
