--계정--------------------------------------------------------------------------------------
-- 접속 : sqlplus system /1234;
-- 접속유저 확인 : show USER
-- 접속종료 quit
-- 새로운 사용자 생성 : CREATE USER 아이디 : IDENTIFIED BY 비밀번호;
-- 권한부여 : GRANT connect, resource, create view, create procedure TO 아이디;
-- 사용자에게 부여된 권한 확인 SELECT GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTEE = '아이디(대문자)';
-- 사용자에게 부여된 권한 확인 SELECT PRIVILEGE FROM DBA_ROLE_PRIVS WHERE GRANTEE = '아이디(대문자)';
-- 권한뺏기 : REVOKE 권한들 FROM 아이디;
-- 사용자 비밀번호 변경 : ALTER USER 아이디 IDENTIFIED BY 새로운비밀번호;
-- 사용자 삭제 : DROP USER 사용자아이디 CASCADE
-- 타계정으로 사용자 접속 : CONN 아이디/비밀번호;
-- 오라클 DMBS 내 사용자 계정 목록 : SELECT username FROM DBA_USERS;
-- 오라클 DBMS 내 사용자 계정 목록 : SELECT username FROM ALL_USERS;

--테이블------------------------------------------------------------------------------------
--테이블 생성 : CREATE TABLE 테이블명 (컬럼이름 컬럼타입 제약조건 );
--테이블 구조 확인 (테이블 스키마): Desc 테이블명;
--테이블 구조 변경(컬럼 수정)  ALTER TABLE 테이블명 MODIFY (컬럼이름 컬럼타입,...);
--테이블 구조 변경 (컬럼 추가) ALTER TABLE 테이블명 ADD (컬럼이름 컬럼타입,...); 
--테이블 구조 변경 (컬럼 삭제) ALTER TABLE 테이블명 DROP (컬럼이름);
--테이블 목록 확인 : SELECT tname From tab;
--테이블 삭제 : DROP TABLE 테이블명 CASCADE CONSTRAINT (PURGE);

--DML-------------------------------------------------------------------------------------
--테이블에 레코드 추가 : INSERT INTO 테이블이름  VALUES (값1,값2.....);
--테이블에 레코드 추가 : INSERT INTO (컬럼1,컬럼2....) VALUES (값1,값2....);
--테이블에서 레코드 조회 : SELECT * FROM 테이블이름; 
--시퀀스 : CREATE SEQUENCE 이름_seq;
--시퀀스를 사용한 INSERT 이름_seq.nextval 넣어주면 된다. 값에.
-- COMMIT; 파일로 데이터 저장
-- ROLLBACK; 가장 최근에 COMMIT 한 이후 적용된 명령 취소하고 복귀
--테이블 레코드 수정 : UPDATE 테이블이름 SET 수정할컬럼 = '수정할값' WHERE 조건
--테이블 레코드 삭제: DELETE FROM 테이블이름 WHERE 조건식
--중복값 제거하고 출력 : SELECT DISTINCT 컬럼명 FROM 테이블명;
--필드나 문자열 연결 : ||
--%:글자수 제한 없고 어떤 글자가 와도 된다. _ : 글자수는 한글자 어떤글자가 와도 좋음
--WHERE 조건절에 쓰는 것 . BETWEEN A AND B , IN(값,값,...); LIKE 'A%';
 
--단일행 함수---------------------------------------------------------------------------------
--INITCAP(문자열)

SELECT name ,tel, REPLACE(TEL ,substr(tel ,instr(tel,')',1,1)+1,3), '###')
FROM T_STUDENT 
WHERE DEPTNO1 = 102;

-- nvl(컬럼,값)

SELECT MGR, count(MGR)
FROM T_EMP
GROUP BY MGR 
HAVING COUNT(MGR) > 0;	


CREATE TABLE users
(
	user_uid number NOT NULL,
	user_id varchar2(20) NOT NULL,
	user_pw varchar2(20) NOT NULL,
	user_name varchar2(10) NOT NULL,
	user_gender varchar2(10),
	user_jumin varchar2(13) NOT NULL,
	user_age number NOT NULL,
	user_authorize number NOT NULL,
	user_email varchar2(30) NOT NULL,
	user_cardnum varchar2(20),
	PRIMARY KEY (user_uid)
);


CREATE TABLE attraction
(
	attr_id number NOT NULL,
	att_max number NOT NULL,
	attr_time number NOT NULL,
	attr_price number NOT NULL,
	attr_content clob,
	attr_name varchar2(20) NOT NULL,
	attr_location varchar2(5) NOT NULL,
	attr_min_age number,
	attr_max_age number,
	attr_min_height number,
	attr_max_height number,
	attr_authorize number NOT NULL,
	attr_regDate date NOT NULL,
	attr_img varchar2(30),
	attr_cardimg varchar2(30),
	PRIMARY KEY (attr_id)
);


CREATE TABLE board
(
	board_id number NOT NULL,
	board_regdate date NOT NULL,
	board_subject varchar2(30) NOT NULL,
	board_content clob,
	board_img varchar2(30),
	board_writeuid number NOT NULL,
	board_viewcnt number NOT NULL,
	board_authorize number NOT NULL,
	board_category varchar2(20) NOT NULL,
	PRIMARY KEY (board_id)
);


CREATE TABLE likes
(
	user_uid number NOT NULL,
	attr_id number NOT NULL,
	PRIMARY KEY (user_uid, attr_id)
);


CREATE TABLE reply
(
	reply_id number NOT NULL,
	reply_boarderid number NOT NULL,
	reply_useruid number NOT NULL,
	reply_comment clob,
	reply_regdate date NOT NULL,
	PRIMARY KEY (reply_id)
);


CREATE TABLE ticket
(
	ticket_id number NOT NULL,
	ticket_name varchar2(20) NOT NULL,
	ticket_price number NOT NULL,
	ticket_img varchar2(30) NOT NULL,
	PRIMARY KEY (ticket_id)
);


CREATE TABLE ticketbuy
(
	ticketbuy_id number NOT NULL,
	user_uid number NOT NULL,
	ticket_id number NOT NULL,
	PRIMARY KEY (ticketbuy_id)
);






/* Create Foreign Keys */

ALTER TABLE likes
	ADD FOREIGN KEY (attr_id)
	REFERENCES attraction (attr_id)
;


ALTER TABLE reply
	ADD FOREIGN KEY (reply_boarderid)
	REFERENCES board (board_id)
;


ALTER TABLE ticketbuy
	ADD FOREIGN KEY (ticket_id)
	REFERENCES ticket (ticket_id)
;


ALTER TABLE board
	ADD FOREIGN KEY (board_writeuid)
	REFERENCES users (user_uid)
;


ALTER TABLE likes
	ADD FOREIGN KEY (user_uid)
	REFERENCES users (user_uid)
;


ALTER TABLE reply
	ADD FOREIGN KEY (reply_useruid)
	REFERENCES users (user_uid)
;


ALTER TABLE ticketbuy
	ADD FOREIGN KEY (user_uid)
	REFERENCES users (user_uid)
;

CREATE SEQUENCE attraction_SEQ;
CREATE SEQUENCE user_SEQ;
CREATE SEQUENCE reply_SEQ;
CREATE SEQUENCE board_SEQ;
CREATE SEQUENCE ticketbuy_SEQ;



SELECT * FROM USERS ;

INSERT INTO USERS VALUES
(user_SEQ.nextval, '관리자','1234','관리자','','1111111111111',100,3000,'관리자@관리자.com','-');
