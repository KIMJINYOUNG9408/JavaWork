
/* Drop Triggers */

DROP TRIGGER TRI_Admin_a_uid;
DROP TRIGGER TRI_Application_A_uid;
DROP TRIGGER TRI_board_b_uid;
DROP TRIGGER TRI_Company_c_uid;
DROP TRIGGER TRI_Hire_h_uid;
DROP TRIGGER TRI_Resume_r_uid;
DROP TRIGGER TRI_User_u_uid;



/* Drop Tables */

DROP TABLE Admin CASCADE CONSTRAINTS;
DROP TABLE Application CASCADE CONSTRAINTS;
DROP TABLE testboardfile CASCADE CONSTRAINTS;
DROP TABLE testreply CASCADE CONSTRAINTS;
DROP TABLE testboard CASCADE CONSTRAINTS;
DROP TABLE HireImage CASCADE CONSTRAINTS;
DROP TABLE Hire CASCADE CONSTRAINTS;
DROP TABLE testCompany CASCADE CONSTRAINTS;
DROP TABLE ResumeFile CASCADE CONSTRAINTS;
DROP TABLE Resume CASCADE CONSTRAINTS;
DROP TABLE testUser CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_Admin_a_uid;
DROP SEQUENCE SEQ_Application_A_uid;
DROP SEQUENCE SEQ_board_b_uid;
DROP SEQUENCE SEQ_Company_c_uid;
DROP SEQUENCE SEQ_Hire_h_uid;
DROP SEQUENCE SEQ_Resume_r_uid;
DROP SEQUENCE SEQ_User_u_uid;




/* Create Sequences */

CREATE SEQUENCE SEQ_Admin_a_uid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_Application_A_uid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE board_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE company_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_Hire_h_uid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_Resume_r_uid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE user_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE reply_seq INCREMENT BY 1 START WITH 1;


/* Create Tables */

CREATE TABLE Admin
(
	a_uid number NOT NULL,
	a_id varchar2(20) NOT NULL,
	a_pw varchar2(20) NOT NULL,
	PRIMARY KEY (a_uid)
);


CREATE TABLE Application
(
	a_uid number NOT NULL,
	a_date date DEFAULT SYSDATE NOT NULL,
	-- 0 : 미열람
	-- 1 : 열람
	a_view number NOT NULL,
	u_uid number NOT NULL,
	h_uid number NOT NULL UNIQUE,
	r_uid number NOT NULL,
	PRIMARY KEY (a_uid)
);


CREATE TABLE Testboard
(
	-- 게시글고유번호
	b_uid number NOT NULL,
	-- 글 제목
	b_subject varchar2(200) NOT NULL,
	-- 내용
	b_content clob NOT NULL,
	b_regdate date DEFAULT SYSDATE NOT NULL,
	b_viewcnt number DEFAULT 0 NOT NULL,
	-- 1 : 기업정보 공유
	-- 2 : 합격후기
	-- 3 : 면접후기
	-- 4 : 퇴사후기
	-- 
	b_category varchar2(20) NOT NULL,
	u_uid number NOT NULL,
	c_uid number NOT NULL,
	PRIMARY KEY (b_uid)
);


CREATE TABLE Testboardfile
(
	bf_id number NOT NULL,
	bf_imgsource varchar2(100) NOT NULL,
	bf_imgsource2 varchar2(100) NOT NULL,
	-- 게시글고유번호
	b_uid number NOT NULL,
	PRIMARY KEY (bf_id)
);


CREATE TABLE TestCompany
(
	c_uid number NOT NULL,
	c_id varchar2(20) NOT NULL,
	c_pw varchar2(20) NOT NULL,
	c_name varchar2(20) NOT NULL,
	-- ex) 인천 계양구 도두리로74
	c_address1 varchar2(100) NOT NULL,
	-- ex) 대동아파트 525동 905호
	c_address2 varchar2(100) NOT NULL,
	c_postNum varchar2(5) NOT NULL,
	c_cnum varchar2(20) NOT NULL,
	c_form varchar2(10) NOT NULL,
	c_employees number NOT NULL,
	c_ceoName varchar2(10),
	c_category varchar2(10) NOT NULL,
	PRIMARY KEY (c_uid)
);


CREATE TABLE Hire
(
	h_uid number NOT NULL UNIQUE,
	c_uid number NOT NULL,
	h_career varchar2(20) NOT NULL,
	h_degree varchar2(20) NOT NULL,
	h_workform varchar2(100) NOT NULL,
	h_salary varchar2(50) NOT NULL,
	h_position varchar2(100) NOT NULL,
	h_cnt number DEFAULT default 0 NOT NULL,
	h_part varchar2(10),
	h_upDate varchar2(100) NOT NULL,
	h_regDate varchar2(100) NOT NULL,
	PRIMARY KEY (h_uid)
);


CREATE TABLE HireImage
(
	rf_uid number NOT NULL,
	h_uid number NOT NULL UNIQUE,
	rf_source varchar2(100) NOT NULL,
	rf_file varchar2(100) NOT NULL,
	PRIMARY KEY (rf_uid)
);


CREATE TABLE Testreply
(
	rep_uid number NOT NULL,
	rep_content clob NOT NULL,
	rep_regdate date DEFAULT SYSDATE NOT NULL,
	-- 게시글고유번호
	b_uid number NOT NULL,
	u_uid number NOT NULL,
	PRIMARY KEY (rep_uid)
);


CREATE TABLE Resume
(
	r_uid number NOT NULL,
	u_uid number NOT NULL,
	r_title varchar2(50) NOT NULL,
	r_name varchar2(20) NOT NULL,
	r_dateofbirth varchar2(10) NOT NULL,
	r_email varchar2(50) NOT NULL,
	r_phonenum varchar2(20) NOT NULL,
	r_address1 varchar2(50) NOT NULL,
	r_address2 varchar2(50) NOT NULL,
	r_status varchar2(20) NOT NULL,
	r_education varchar2(20) NOT NULL,
	-- // 최종학력
	-- (초중고)
	r_schoolname varchar2(50) NOT NULL,
	r_area varchar2(20),
	r_period varchar2(20) NOT NULL,
	r_major varchar2(20),
	r_universityyear varchar2(20),
	r_universityname varchar2(30),
	r_universityperiod varchar2(20),
	r_universitymajor varchar2(20),
	r_universitydepartment varchar2(20),
	r_career varchar2(20) NOT NULL,
	r_companyname varchar2(20),
	r_companyperiod varchar2(30),
	r_companyposition varchar2(20),
	r_companyjobtype varchar2(20),
	r_companyincome varchar2(20),
	-- 개월
	-- 
	r_totalcareer number,
	-- 희망근무형태
	-- 
	r_servicetype varchar2(30) NOT NULL,
	r_hopeincome varchar2(30) NOT NULL,
	r_workarea varchar2(30) NOT NULL,
	r_jobtype varchar2(30) NOT NULL,
	r_industry varchar2(20) NOT NULL,
	r_introduction clob,
	r_introdTtitle varchar2(50),
	r_toeic number,
	PRIMARY KEY (r_uid)
);


CREATE TABLE ResumeFile
(
	rf_uid number NOT NULL,
	r_uid number NOT NULL,
	rf_source varchar2(100) NOT NULL,
	rf_file varchar2(100) NOT NULL,
	PRIMARY KEY (rf_uid)
);


CREATE TABLE TestUser
(
	u_uid number NOT NULL,
	u_id varchar2(20) NOT NULL,
	u_pw varchar2(20) NOT NULL,
	u_name varchar2(10) NOT NULL,
	u_jumin varchar2(13) NOT NULL,
	u_email varchar2(50) NOT NULL,
	-- '-' 이거는 빼주세연
	-- 
	u_phoneNum varchar2(11) NOT NULL,
	PRIMARY KEY (u_uid)
);



/* Create Foreign Keys */

ALTER TABLE testboardfile
	ADD FOREIGN KEY (b_uid)
	REFERENCES testboard (b_uid)
;


ALTER TABLE testreply
	ADD FOREIGN KEY (b_uid)
	REFERENCES testboard (b_uid)
;


ALTER TABLE testboard
	ADD FOREIGN KEY (c_uid)
	REFERENCES testCompany (c_uid)
;


ALTER TABLE Hire
	ADD FOREIGN KEY (c_uid)
	REFERENCES Company (c_uid)
;


ALTER TABLE Application
	ADD FOREIGN KEY (h_uid)
	REFERENCES Hire (h_uid)
;


ALTER TABLE HireImage
	ADD FOREIGN KEY (h_uid)
	REFERENCES Hire (h_uid)
;


ALTER TABLE Application
	ADD FOREIGN KEY (r_uid)
	REFERENCES Resume (r_uid)
;


ALTER TABLE ResumeFile
	ADD FOREIGN KEY (r_uid)
	REFERENCES Resume (r_uid)
;


ALTER TABLE Application
	ADD FOREIGN KEY (u_uid)
	REFERENCES User (u_uid)
;


ALTER TABLE TestBoard
	ADD FOREIGN KEY (u_uid)
	REFERENCES testUser (u_uid)
;


ALTER TABLE testreply
	ADD FOREIGN KEY (u_uid)
	REFERENCES testUser (u_uid)
;


ALTER TABLE Resume
	ADD FOREIGN KEY (u_uid)
	REFERENCES User (u_uid)
;


SELECT * FROM testboard;
/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_Admin_a_uid BEFORE INSERT ON Admin
FOR EACH ROW
BEGIN
	SELECT SEQ_Admin_a_uid.nextval
	INTO :new.a_uid
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_Application_A_uid BEFORE INSERT ON Application
FOR EACH ROW
BEGIN
	SELECT SEQ_Application_A_uid.nextval
	INTO :new.A_uid
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_board_b_uid BEFORE INSERT ON board
FOR EACH ROW
BEGIN
	SELECT SEQ_board_b_uid.nextval
	INTO :new.b_uid
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_Company_c_uid BEFORE INSERT ON Company
FOR EACH ROW
BEGIN
	SELECT SEQ_Company_c_uid.nextval
	INTO :new.c_uid
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_Hire_h_uid BEFORE INSERT ON Hire
FOR EACH ROW
BEGIN
	SELECT SEQ_Hire_h_uid.nextval
	INTO :new.h_uid
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_Resume_r_uid BEFORE INSERT ON Resume
FOR EACH ROW
BEGIN
	SELECT SEQ_Resume_r_uid.nextval
	INTO :new.r_uid
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_User_u_uid BEFORE INSERT ON User
FOR EACH ROW
BEGIN
	SELECT SEQ_User_u_uid.nextval
	INTO :new.u_uid
	FROM dual;
END;

/




/* Comments */

COMMENT ON COLUMN Application.a_view IS '0 : 미열람
1 : 열람';
COMMENT ON COLUMN board.b_uid IS '게시글고유번호';
COMMENT ON COLUMN board.b_subject IS '글 제목';
COMMENT ON COLUMN board.b_content IS '내용';
COMMENT ON COLUMN board.b_category IS '1 : 기업정보 공유
2 : 합격후기
3 : 면접후기
4 : 퇴사후기
';
COMMENT ON COLUMN boardfile.b_uid IS '게시글고유번호';
COMMENT ON COLUMN Company.c_address1 IS 'ex) 인천 계양구 도두리로74';
COMMENT ON COLUMN Company.c_address2 IS 'ex) 대동아파트 525동 905호';
COMMENT ON COLUMN reply.b_uid IS '게시글고유번호';
COMMENT ON COLUMN Resume.r_schoolname IS '// 최종학력
(초중고)';
COMMENT ON COLUMN Resume.r_totalcareer IS '개월
';
COMMENT ON COLUMN Resume.r_servicetype IS '희망근무형태
';
COMMENT ON COLUMN User.u_phoneNum IS '''-'' 이거는 빼주세연
';

SELECT * FROM testuser;
SELECT * FROM testboard;
SELECT * FROM testcompany;
SELECT * FROM testreply;
SELECT * FROM testboardfile;

DELETE FROM testcompany WHERE c_uid = 1;


SELECT c_name from testcompany;
SELECT * FROM ( SELECT rownum AS rnum , b.*  FROM (SELECT b.b_uid, b.b_subject, b.b_content, b.b_regdate, b.b_viewcnt, b.b_category FROM testboard b, testuser u WHERE b.u_uid = u.u_uid ORDER BY b_uid DESC) b  
					) 
					WHERE rnum >= 1 AND rnum < 11;
INSERT INTO testuser VALUES (user_seq.nextval, 'suyoung', '1234' , '김진영', '9411111111111', 'kk@naver.com', '01011111111');
INSERT INTO TESTCOMPANY VALUES (company_seq.nextval, 'a5', '1234', 'TOSS' , 'k','k','k','k','k',1,'k','k');
INSERT INTO testboard
    	 (B_UID ,B_SUBJECT ,B_CONTENT ,B_CATEGORY ,U_UID ,C_UID ) 
    	 VALUES 
    	   (board_seq.nextval, 'ㅇ', 'ㅇ', 'ㅇ' ,1, 1);
SELECT * FROM ( SELECT rownum AS rnum , b.*  FROM (SELECT b.b_uid "uid", b.b_subject subject, b.b_content content, b.b_regdate regdate, b.b_viewcnt viewcnt, b.b_category category, u.u_id writeName FROM testboard b, testuser u WHERE b.u_uid = u.u_uid ORDER BY b_uid DESC) b  
					) 
					WHERE rnum >= 1 AND rnum <20;
 
				SELECT * FROM TESTBOARD ;
   	  
 INSERT INTO board
    	 (B_UID ,B_SUBJECT ,B_CONTENT ,B_CATEGORY ,U_UID ) 
    	 VALUES 
    	   (SEQ_board_b_uid.nextval, 'NEXT 공지사항입니다', '공지사항입니다', '공지사항' ,1);  	  
    	  
SELECT * FROM ( SELECT rownum AS rnum , b.*  FROM (SELECT b.b_uid, b.b_subject, b.b_content, b.b_regdate, b.b_viewcnt, b.b_category, u.U_ID, c.C_NAME FROM testboard b, testuser u, testcompany c WHERE b.u_uid = u.u_uid AND b.C_UID = c.c_uid AND b.B_CATEGORY = 'ㅇ' ORDER BY b_uid DESC) b  
					) 
					WHERE rnum >= 1 AND rnum < 20;    	  

SELECT b.b_uid "uid", b.b_subject subject, b.b_content content, b.b_regdate regdate, b.b_viewcnt viewcnt, b.b_category category, u.u_id writeName, c.C_NAME companyName FROM testboard b, testuser u, testcompany c WHERE b.u_uid = u.u_uid AND b.C_UID = c.c_uid AND b.b_category = '정보공유' ORDER BY b_uid DESC

SELECT count(*) FROM testboard b , testuser u, testcompany c WHERE b.u_uid = u.u_uid AND b.C_UID  = c.C_UID  AND ( UPPER( b.b_subject) LIKE  upper('%공지%')  OR  upper (c.C_NAME) LIKE upper('%kakaod%') )

SELECT * FROM TESTCOMPANY WHERE upper(c_name) LIKE '%kakao%'; 

SELECT  b.b_uid "uid", b.b_subject subject, b.b_content content, b.b_regdate regdate, b.b_viewcnt viewcnt, b.b_category category, u.u_id writeName, c.C_NAME companyName FROM testboard b , testuser u, testcompany c WHERE b.u_uid = u.u_uid          AND b.C_UID  = c.C_UID  AND b.b_uid = 26;

SELECT * FROM TESTREPLY ;


SELECT * FROM testboard;
SELECT * FROM testreply;
SELECT * FROM testuser;
INSERT INTO testreply VALUES (reply_seq.nextval, '댓글5', SYSDATE, 26,1);

SELECT  REP_CONTENT content, REP_REGDATE regdate, u_uid
FROM TESTREPLY
WHERE b_uid = 26
ORDER BY regdate;

	  SELECT  REP_CONTENT content, REP_REGDATE regdate, u_uid
		FROM TESTREPLY
		WHERE b_uid = 26
		ORDER BY regdate;
	
	INSERT INTO testreply
			(rep_uid, rep_content, rep_regdate, b_uid, u_uid)
		VALUES
			(reply_seq.nextval,"아이쿠야",sysdate, 26, 1)
			
		
SELECT * FROM TESTREPLY ;
SELECT * FROM TESTREPLY ;
SELECT count(*) FROM testreply WHERE b_uid = 96;

SELECT count(*) FROM testboard b , testuser u, testcompany c WHERE b.u_uid = u.u_uid AND b.C_UID  = c.C_UID  AND ( UPPER( b.b_subject) LIKE  upper('%공지%')  OR  upper (c.C_NAME) LIKE upper('%kakaod%') )
