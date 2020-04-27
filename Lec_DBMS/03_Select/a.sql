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

