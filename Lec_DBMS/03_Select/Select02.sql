-- 산술연산자

SELECT ENAME , SAL ,sal*1.1
FROM T_EMP; 

SELECT ENAME, SAL, COMM , SAL+COMM  --NULL 값과의 산술연산은 무.조.건. NULL!
FROM T_EMP;

--WHERE 조건절

SELECT *FROM T_EMP;
SELECT *FROM T_EMP WHERE JOB ='SALESMAN';

-- p13
SELECT ename, sal, deptno
FROM T_EMP
WHERE DEPTNO =10;
--직원 테이블에서 급여가 2000보다 큰사람의 이름과 급여를 출력
SELECT ENAME ,SAL 
FROM T_EMP 
WHERE sal > 2000;

SELECT ENAME ,EMPNO ,SAL 
FROM T_EMP 
WHERE ENAME = 'SCOTT';

--연습
--학생테이블(t_student) 에서
--2,3학년(grade) 학생의 이름(name), 학년(grade) 출력
SELECT *FROM T_STUDENT;

SELECT name, grade 
FROM T_STUDENT WHERE grade = 2 OR grade = 3;

SELECT name, grade 
FROM T_STUDENT WHERE grade IN (2,3);

SELECT name, grade 
FROM T_STUDENT WHERE grade > 1 AND grade < 4; 

SELECT name, grade 
FROM T_STUDENT WHERE grade NOT IN(1,4);

SELECT name, grade 
FROM T_STUDENT WHERE grade BETWEEN 2 AND 3;

SELECT name, grade 
FROM T_STUDENT WHERE grade LIKE '2%' OR grade LIKE '3%';

--교수님 (t_professor) 중에서 급여 (pay) 가 300 보다 크고,
--직급 (position) 이 '정교수' 인 분들의
--이름(name), 급여(pay), 직급(position) 을 출력하세요
SELECT name, pay, position
FROM T_PROFESSOR 
WHERE POSITION = '정교수';
--보너스(bonus)를 못받고 있는 
--교수님의 이름(name)과 직급(position)를 출력하세요
SELECT NAME , POSITION 
FROM T_PROFESSOR 
WHERE BONUS IS NULL 

-- LIKE
-- 교수님 중에서 김씨 성을 가진 교수님의 이름만 출력 (LIKE 사용)
SELECT NAME 
FROM T_PROFESSOR 
WHERE NAME LIKE '김%';

SELECT ENAME 
FROM T_EMP 
WHERE ENAME LIKE '%NE%';

SELECT ENAME 
FROM T_EMP 
WHERE ENAME LIKE '_A%';

-- ORDER BY
-- 직원중 이름에 L이 들어간 사람의 이름을 사전 오름차순으로 출력하기
SELECT ENAME 
FROM T_EMP
WHERE ENAME LIKE '%L%'
ORDER BY ENAME ASC ;

-- 이름 직책 급여 뽑고
--직책 사전 내림차순 급여 오름차순으로 출력하기 
SELECT ENAME , JOB ,SAL 
FROM T_EMP 
ORDER BY JOB DESC ,SAL ASC ; -- ASC는 안붙여도 된다

--학생 테이블(t_student) 에서  학생의 이름(name)과 학년(grade)와 키(height)를 출력하세요,
-- 단 학년은 1학년부터 출력하고,  키는 큰사람부터  출력하세요
SELECT NAME ,GRADE ,HEIGHT 
FROM T_STUDENT 
ORDER BY GRADE ASC, HEIGHT DESC;

SELECT * FROM T_STUDENT ;

SELECT * FROM T_DEPARTMENT ;

SELECT * FROM T_DEPT ;
SELECT * FROM T_DEPT2 ;
SELECT * FROM T_PRODUCT ;
SELECT * FROM T_POST ;
SELECT * FROM T_CREDIT ;
SELECT * FROM T_DML_ERR ;
SELECT * FROM T_EMP ;
SELECT * FROM T_ENABLE ;
SELECT * FROM T_M01 ;
SELECT * FROM t_