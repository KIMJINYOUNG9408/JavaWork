package phonebook05.file;

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
import java.util.ArrayList;
import java.util.Arrays;


// CONTROLLER 객체
//   어플리케이션의 동작, 데이터 처리(CRUD), (Business logic 담당)

public class PhonebookManager implements Pb, Closeable {
    
	public static final String PB_DATA_DIR = "data";
	public static final String PB_DATA_FILE = "phonebook.dat";
    private File pbDir;
    private File pbFile;
	private ArrayList<PhonebookModel> pbList = new ArrayList<PhonebookModel>();

	// singleton적용
	private PhonebookManager() {
		
		pbDir = new File(PB_DATA_DIR);
		
		if(!pbDir.exists()) {
			if(pbDir.mkdir()) {
				System.out.println("폴더 생성 성공");
			} else {
				System.out.println("폴더 생성 실패");
			}
		} else {
			System.out.println("폴더가 이미 존재합니다. " + pbDir.getAbsolutePath());
		}
		
		pbFile = new File(pbDir, PB_DATA_FILE);
		System.out.println("파일에서 데이터를 읽습니다....");
		if(pbFile.exists()) {
		
		
		try(InputStream in = new FileInputStream(pbFile);
				ObjectInputStream oin = new ObjectInputStream(in);) {
			
			pbList = (ArrayList<PhonebookModel>)oin.readObject();
			System.out.println(pbList.size() + " 개의 데이터를 읽었습니다.");
		} catch (FileNotFoundException e) {
			System.out.println("****** 목록이 없습니다. 목록을 추가해주세요 ******");	
		} catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
			
		}
		
		} 
		else {
			System.out.println("읽어올 파일이 없습니다.");
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

		PhonebookModel p = new PhonebookModel(name, phoneNum, memo);
		p.setUid(getMaxUid() + 1);
		pbList.add(p);

		return 1;
	}

	@Override
	public PhonebookModel[] selectAll() {
		PhonebookModel[] pb = new PhonebookModel[pbList.size()];
		for (int i = 0; i < pbList.size(); i++) {
			pb[i] = pbList.get(i);
		}
		return pb;

		// 아래와 같이 한줄로 가능! toArray() : List<> --> 배열로 변환
//		return pbList.toArray(new PhonebookModel[pbList.size()]);
	}

	// 특정 uid 의 데이터 검색 리턴
	// 못찾으면 null 리턴
	@Override
	public PhonebookModel selectByUid(int uid) {

		for (int i = 0; i < pbList.size(); i++) {
			if (pbList.get(i).getUid() == uid) {
				return pbList.get(i);
			}
		}

		return null; // 못찾으면 null 리턴
	}// end selectByUid()

	@Override
	public int updateByUid(int uid, String name, String phoneNum, String memo) {

		// 매개변수 검증
		if (uid < 1)
			throw new PhonebookException("update() uid 오류: " + uid, Pb.ERR_UID);

		if (name == null || name.trim().length() == 0) // 이름 필수
			throw new PhonebookException("update() 이름입력 오류: ", Pb.ERR_EMPTY_STRING);

		// 특정 uid 값을 가진 데이터의 배열 인덱스 찾기
		int index = findIndexByUid(uid);
		if (index < 0)
			throw new PhonebookException("update() 없는 uid: " + uid, Pb.ERR_UID);

		pbList.get(index).setName(name);
		pbList.get(index).setPhoneNum(phoneNum);
		pbList.get(index).setMemo(memo);

		return 1;
	}

	@Override
	public int deleteByUid(int uid) {
		// 매개변수 검증
		if (uid < 1)
			throw new PhonebookException("deleteByUid() uid 오류: " + uid, Pb.ERR_UID);

		int index = findIndexByUid(uid);
		if (index < 0)
			throw new PhonebookException("deleteByUid() 없는 uid: " + uid, Pb.ERR_UID);

		pbList.remove(index);

		return 1;
	}

	// 현재 데이터중 가장 큰 uid 값을 찾아서 리턴
	private int getMaxUid() {
		int maxUid = 0;

		if (pbList.size() > 0) {
			int uid = pbList.get((pbList.size() - 1)).getUid();
			if (maxUid < uid)
				maxUid = uid;
		}

//		for (PhonebookModel p : pbList) {
//		if(maxUid < p.getUid()) {
//			maxUid = p.getUid();
//		}
//	}

		return maxUid;
	}

	// 특정 uid 값을 가진 데이터의 배열 인덱스 찾기
	// 못찾으면 -1 리턴
	private int findIndexByUid(int uid) {

		for (int i = 0; i < pbList.size(); i++) {
			if (pbList.get(i).getUid() == uid)
				return i;
		}

		return -1;
	}

	@Override
	public void close() throws IOException {
		// 데이터 저장
		// pbList -> 파일
		try (OutputStream out = new FileOutputStream(new File(pbDir, PB_DATA_FILE));
				ObjectOutputStream oout = new ObjectOutputStream(out);){
		    	oout.writeObject(pbList);
		    	System.out.println("파일 저장 완료");
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
