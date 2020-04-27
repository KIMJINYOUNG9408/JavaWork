
/* Drop Tables */

DROP TABLE phonebook CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE phonebook
(
	pb_uid number NOT NULL,
	pb_name varchar2(40) NOT NULL,
	pb_phonenum varchar2(40),
	pb_memo clob,
	pb_regdate date DEFAULT SYSDATE,
	PRIMARY KEY (pb_uid)
	
	
);

--시퀀스 객체도 생성ㅎ자
DROP SEQUENCE phonebook_seq;
CREATE SEQUENCE phonebook_seq;

SELECT * FROM PHONEBOOK;

SELECT count(*) cnt FROM TEST_MEMBER ; -- 테이블의 모든 레코드 개수
SELECT MAX(MB_NO) "max" FROM TEST_MEMBER ; -- mb_no 의 최댓값
SELECT MIN(mb_no) "min" FROM TEST_MEMBER ; -- mb_no 의 최솟값

SELECT count(*) cnt FROM PHONEBOOK ;
SELECT MAX(pb_uid) FROM phonebook;

SELECT * FROM PHONEBOOK ORDER BY PB_UID DESC;








