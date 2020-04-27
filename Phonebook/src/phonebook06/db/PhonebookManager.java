package phonebook06.db;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

// CONTROLLER 객체
//   어플리케이션의 동작, 데이터 처리(CRUD), (Business logic 담당)

public class PhonebookManager implements Pb, Closeable {

	//
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// singleton적용
	private PhonebookManager() {

		// JDBC 프로그래밍
		// 클래스 로딩
		// 연결 Connection 은 마지막에 닫아라.
		// Statement ( 필요하다면 ) 생성.
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWD);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private static PhonebookManager instance = null;

	public static PhonebookManager getInstance() {
		if (instance == null) {
			instance = new PhonebookManager();
		}
		return instance;
	} // end getInstance()

	// 전화번호부 생성 등록
	@Override
	public int insert(String name, String phoneNum, String memo) {

		// 매개변수 검증 : 이름 필수
		if (name == null || name.trim().length() == 0) {
			throw new PhonebookException("insert() 이름입력오류: ", Pb.ERR_EMPTY_STRING);
		}
		int cnt = 0;

		try {
			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, name);
			pstmt.setString(2, phoneNum);
			pstmt.setString(3, memo);

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return cnt;
	}

	@Override
	public PhonebookModel[] selectAll() {
		
		ArrayList<PhonebookModel> pbList = new ArrayList<PhonebookModel>();
		try {
			pstmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int uid = rs.getInt(COL_LABEL_UID);
				String name = rs.getString(COL_LABEL_NAME);
				String pn = rs.getString(COL_LABEL_PHONENUM);
				String memo = rs.getString(COL_LABEL_MEMO);
				Date d = rs.getDate(COL_LABEL_REGDATE);
				Time t = rs.getTime(COL_LABEL_REGDATE);
				String date = new SimpleDateFormat("yyyy-MM-dd").format(d) + " "
						+ new SimpleDateFormat("hh:mm:ss").format(t);

				SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date regDate;
				try {
					regDate = a.parse(date);
					pbList.add(new PhonebookModel(uid, name, pn, memo, regDate));
				} catch (ParseException e) {

					e.printStackTrace();
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return pbList.toArray(new PhonebookModel[pbList.size()]);
	}

	// 특정 uid 의 데이터 검색 리턴
	// 못찾으면 null 리턴
	@Override
	public PhonebookModel selectByUid(int uid) {
      
		PhonebookModel ph = null;

		try {
			pstmt = conn.prepareStatement(SQL_SELECT_BY_UID);
			pstmt.setInt(1, uid);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				ph = new PhonebookModel(uid, rs.getString(COL_LABEL_NAME), rs.getString(COL_LABEL_PHONENUM),
						rs.getString(COL_LABEL_MEMO), rs.getDate(COL_LABEL_REGDATE));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return ph; // 못찾으면 null 리턴
	}// end selectByUid()

	@Override
	public int updateByUid(int uid, String name, String phoneNum, String memo) {

		// 매개변수 검증
		if (uid < 1)
			throw new PhonebookException("update() uid 오류: " + uid, Pb.ERR_UID);

		if (name == null || name.trim().length() == 0) // 이름 필수
			throw new PhonebookException("update() 이름입력 오류: ", Pb.ERR_EMPTY_STRING);

		// 특정 uid 값을 가진 데이터의 배열 인덱스 찾기
		int cnt = 0;

		try {
			pstmt = conn.prepareStatement(SQL_UPDATE_BY_UID);
			pstmt.setString(1, name);
			pstmt.setString(2, phoneNum);
			pstmt.setString(3, memo);
			pstmt.setInt(4, uid);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return cnt;
	}

	@Override
	public int deleteByUid(int uid) {
		// 매개변수 검증
		if (uid < 1)
			throw new PhonebookException("deleteByUid() uid 오류: " + uid, Pb.ERR_UID);

		int cnt = 0;
		try {
			pstmt = conn.prepareStatement(SQL_DELETE_BY_UID);
			pstmt.setInt(1, uid);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return cnt;
	}

	// 현재 데이터중 가장 큰 uid 값을 찾아서 리턴
	private int getMaxUid() {
		int maxUid = 0;

		// TODO 쓰는곳은 없음. 
		try {
			pstmt = conn.prepareStatement(SQL_SELECT_MAX_UID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				maxUid = rs.getInt(COL_LABEL_UID);
			}			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		
		 return maxUid;
	}

	// 특정 uid 값을 가진 데이터의 배열 인덱스 찾기

	@Override
	public void close() throws IOException {

		// ResultSet
		// Statement
		// Connection
		// 들 close()..
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

} // end PhonebookManager

// 예의 클래스 정의
// 예외발생하면 '에러코드' + '에러메세지'를 부여하여 관리하는게 좋다.
class PhonebookException extends RuntimeException {

	private int errCode = Pb.ERR_GENERIC;

	public PhonebookException() {
		super("Phonebook 예외 발생");
	}

	public PhonebookException(String msg) {
		super(msg);
	}

	public PhonebookException(String msg, int errCode) {
		super(msg);
		this.errCode = errCode;
	}

	// Throwable 의 getMessage 를 오버라이딩 가능
	@Override
	public String getMessage() {
		String msg = "ERR-" + errCode + "]" + Pb.ERR_STR[errCode] + " " + super.getMessage();
		return msg;
	}

} // end PhonebookException
