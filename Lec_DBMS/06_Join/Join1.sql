-- JOIN

-- FROM 절에 테이블에도 별칭(alias) 를 줄수 있다.
-- 20 레코드
SELECT s.STUDNO ,s.NAME ,s.DEPTNO1 
FROM T_STUDENT s;

-- 12 레코드
SELECT d.DEPTNO , d.DNAME 
FROM T_DEPARTMENT d; 

-- 카티션곱 (Cartesian Product)
-- 두개의 테이블을 JOIN 하게 되면, 
-- 각 테이블의 레코드들의 모든 조합이 출력된다.
-- WHERE 나 ON 등으로 JOIN 조건이 주어지지 않으면 
-- 모든 카티션곱이 출력된다. 

--오라클방식
SELECT s.STUDNO , s.name , s.DEPTNO1 , d.DEPTNO ,d.DNAME 
FROM T_STUDENT s , T_DEPARTMENT d; 

-- ANSI 방식
SELECT s.STUDNO ,s.NAME, s.DEPTNO1 ,d.DEPTNO ,d.DNAME 
FROM T_STUDENT s
CROSS JOIN T_DEPARTMENT d

-- Equi JOIN ( 등가 Join )
SELECT s.name "학생이름" , s.DEPTNO1 "학과번호", d.DNAME "학과이름"
FROM T_STUDENT s , T_DEPARTMENT d
WHERE s.DEPTNO1 = d.DEPTNO ;

--ANSI 방식
SELECT s.name "학생이름", s.DEPTNO1 "학과번호" , d.DNAME "학과이름"
FROM T_STUDENT s JOIN T_DEPARTMENT d ON s.DEPTNO1 = d.DEPTNO ;

--제2 전공은 ? deptno2
SELECT s.NAME "학생이름" , s.DEPTNO2 "제2학과" ,d.DNAME "제2학과 이름"
FROM T_STUDENT s, T_DEPARTMENT d
WHERE s.DEPTNO2 = d.DEPTNO ;

--#6102) t_student 테이블, t_professor 테이블 을 join하여 ‘학생이름’, ‘지도교수 번호’, ‘지도교수이름’ 을 출력하세요
SELECT * FROM T_STUDENT ;
SELECT * FROM T_PROFESSOR ;
-- Oracle 방식
SELECT s.NAME "학생이름" ,s.PROFNO "지도교수" ,p.NAME "지도교수이름"
FROM T_STUDENT s, T_PROFESSOR p
WHERE s.PROFNO  = p.PROFNO ;
--ANSI 방식
SELECT s.NAME "학생이름" ,s.PROFNO "지도교수" ,p.NAME "지도교수이름"
FROM T_STUDENT s JOIN T_PROFESSOR p ON s.PROFNO = p.PROFNO ;

--#6103) t_student, t_department, t_professor 테이블 을 join 하여  학생의 이름, 학과이름, 지도교수 이름  을 출력하세요
--Oracle 방식
SELECT s.NAME "학생이름", d.DNAME "학과이름", p.NAME "교수이름"
FROM T_STUDENT s , T_DEPARTMENT d , T_PROFESSOR p
WHERE s.deptno1 = d.DEPTNO AND s.PROFNO = p.PROFNO ;
--ANSI방식
SELECT s.NAME "학생이름", d.DNAME "학과이름", p.NAME "교수이름"
FROM T_STUDENT s
       Join T_DEPARTMENT d ON s.DEPTNO1 = d.DEPTNO
       JOIN  T_PROFESSOR p ON s.PROFNO = p.PROFNO ;
--#6104) t_emp2 직원 테이블과 t_post 직급 테이블을 조회하여
-- 사원의 이름과 직급, 현재연봉, 해당직급의 연봉의 하한금액(s_pay)과 상한금액(e_pay)을 출력하세요
SELECT * FROM T_EMP2 ;
SELECT * FROM T_POST ;
--Oracle 방식
SELECT e.NAME, e.POST, e.PAY , p.S_PAY , p.E_PAY 
FROM T_EMP2 e , T_POST p
WHERE e.POST = p.POST;
--ANSI방식
SELECT e.NAME, e.POST, e.PAY , p.S_PAY , p.E_PAY 
FROM T_EMP2 e JOIN T_POST p ON e.post = p.POST ;

--#6105) t_student - t_professor 테이블 join 하여 제1전공(deptno1) 이 101번인 학생들의 학생이름과 지도교수 이름을 출력하세요
--ORACLE방식
SELECT s.name "학생이름", p.name "교수이름" 
FROM T_STUDENT s , T_PROFESSOR p
WHERE s.PROFNO  = p.PROFNO  AND s.DEPTNO1 = 101;
--ANSI방식
SELECT s.name "학생이름", p.name "교수이름" 
FROM T_STUDENT s JOIN T_PROFESSOR p ON s.PROFNO  = p.PROFNO
WHERE s.DEPTNO1  = 101;
