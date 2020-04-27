-- SELECT 결과물중 맨 위의 5개만 출력해보고 싶으면 어케 해야 하나?
--   ex) 게시판.. 페이징

-- DBMS 마다 구현 방법 다름
--	MYSQL : LIMIT
-- 	MS SQL server : TOP
-- 	ORACLE : ROWNUM 

SELECT empno , ename , sal FROM T_EMP ;

--자동적으로 오라클에서 붙여주는 행번호 객체 ( ROWNUM )
SELECT ROWNUM, empno , ename , sal FROM T_EMP ;

SELECT ROWNUM , empno , ename , sal FROM T_EMP ORDER BY EMPNO DESC ;

--상위 5개만 뽑기
SELECT ROWNUM , empno , ename , sal FROM T_EMP WHERE ROWNUM <= 5 ORDER BY EMPNO DESC ;

--SELECT에 ROWNUM 없어도 동작
SELECT empno , ename , sal FROM T_EMP WHERE ROWNUM <= 5 ORDER BY EMPNO DESC ;

--ROWNUM > 5 ?? 동작안함..  ROWNUM 범위가 1이 포함 안되면 동작안함.
SELECT ROWNUM , empno , ename , sal FROM T_EMP WHERE ROWNUM > 5 ORDER BY EMPNO DESC ;

-- 상위 5개 출력
-- ROW1 ~ ROW5 (1 page)
SELECT ROWNUM, empno , ename, sal FROM T_EMP 
WHERE ROWNUM >= 1 AND ROWNUM < 11 + 5
ORDER BY empno DESC; -- 안나온다.

-- phonebook 뻥튀기
INSERT INTO PHONEBOOK (SELECT * FROM phonebook); -- 에러 Primary Key 중복 ! 

INSERT INTO PHONEBOOK 
     (SELECT phonebook_seq.nextval,PB_NAME ,PB_PHONENUM ,PB_MEMO ,SYSDATE FROM PHONEBOOK );

SELECT * FROM phonebook;

-- ROWNUM rev.
SELECT pb_uid, pb_name, pb_phonenum
FROM PHONEBOOK 
ORDER BY pb_uid DESC;
--
SELECT T.*
FROM(SELECT pb_uid, pb_name, pb_phonenum FROM PHONEBOOK ORDER BY pb_uid DESC) T;
--
SELECT ROWNUM AS RNUM, T.*
FROM(SELECT pb_uid, pb_name, pb_phonenum FROM PHONEBOOK ORDER BY pb_uid DESC) T;
-- 한 페이지당 5개 데이터 출력시
-- 2번째 페이지
SELECT * FROM
(
SELECT ROWNUM AS RNUM, T.*
FROM(SELECT pb_uid, pb_name, pb_phonenum FROM PHONEBOOK ORDER BY pb_uid DESC) T
) 
WHERE rnum >= 6 AND RNUM < 6+5;
--3번쨰 페이지
SELECT * FROM
(
SELECT ROWNUM AS RNUM, T.*
FROM(SELECT pb_uid, pb_name, pb_phonenum FROM PHONEBOOK ORDER BY pb_uid DESC) T
) 
WHERE rnum >= 11 AND RNUM < 11+5;
--한페이지당 열개 하려면 ?
SELECT * FROM
(
SELECT ROWNUM AS RNUM, T.*
FROM(SELECT pb_uid, pb_name, pb_phonenum FROM PHONEBOOK ORDER BY pb_uid DESC) T
) 
WHERE rnum >= 11 AND RNUM < 11+10;