--dual 은 ROW 1개 짜리 dummy TABLE;
SELECT 'abcde' FROM dual;
SELECT '안녕하세요' FROM dual;
SELECT 100 FROM dual;
-- * : '모든컬럼'
SELECT *FROM t_emp;
-- 원하는 컬럼만 조회
SELECT EMPNO ,ENAME
FROM t_emp;

--
SELECT * FROM  t_professor;

SELECT BONUS FROM  t_professor;

SELECT '안녕하세요' FROM t_professor;

SELECT name, '교수님 싸랑해요' FROM  t_professor;

-- 컬럼 별명(alias) 사용한 출력 3가지.
SELECT STUDNO 학번 ,NAME 이름 
FROM t_student;

SELECT STUDNO "학번", NAME AS 이름
FROM T_STUDENT; 

--SELECT STUDNO 학생 학번 ,NAME 이름   >> 에러가 발생한다 띄어쓰기 때문에 
--FROM t_student;

SELECT STUDNO "학생 학번" ,NAME 이름   -- >> 쌍따옴표로 띄어쓰기 문제 해결 
FROM t_student;

SELECT EMPNO 사원번호 , ENAME 사원명, JOB  직업
FROM T_EMP;

SELECT DEPTNO "부서#", DNAME "부서명", LOC "위치"
FROM T_DEPT; 

--DISTINCT 중보값 제거하고 출력
SELECT DEPTNO FROM t_emp;
SELECT DISTINCT DEPTNO FROM t_emp;

SELECT DISTINCT DEPTNO1 
FROM T_STUDENT;

SELECT DISTINCT JOB 	
FROM T_EMP;

-- || : 필드 , 문자열 연결 연산
SELECT name, POSITION
FROM T_PROFESSOR 

SELECT name || '-' || POSITION "교수님 명단"
FROM T_PROFESSOR;

SELECT *FROM T_STUDENT;
SELECT name || '의 키는' || HEIGHT || 'cm, 몸무게는' || WEIGHT || 'kg 입니다' "학생의 키와 몸무게"
FROM T_STUDENT; 

SELECT BONUS FROM T_PROFESSOR ;
