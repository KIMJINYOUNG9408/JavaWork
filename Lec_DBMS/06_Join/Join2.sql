--비등가 조인 (Non -Equl Join)
SELECT * FROM T_CUSTOMER ;
SELECT * FROM T_GIFT ;
-- #6201) t_customer 테이블, t_gift 테이블을 join 하여  고객의 마일리지 포인트별로 받을수 있는 상품을 조회하여 
--고객의 '이름(c_name)'과 포인트(c_point) 상품명(g_name)을 출력하세요
--** BETWEEN ~ AND ~ 사용
-- ORACLE
SELECT c.C_NAME "고객명", c.C_POINT "Point", g.G_NAME "상품명"
FROM T_CUSTOMER c , T_GIFT g
WHERE c.C_POINT BETWEEN g.G_START AND g.G_END ;
--ANSI
SELECT c.C_NAME "고객명", c.C_POINT "Point", g.G_NAME "상품명"
FROM T_CUSTOMER c JOIN T_GIFT g ON c.C_POINT BETWEEN g.G_START AND g.G_END ;

--#6202) 앞 예제에서 조회한 상품의 이름(g_name)과  필요수량이 몇개인지 조회하세요
--ORACLE
SELECT g.G_NAME "상품명" ,COUNT(*) "필요수량"
FROM T_CUSTOMER c , T_GIFT g
WHERE c.C_POINT BETWEEN g.G_START AND g.G_END 
GROUP BY g.G_NAME;
--ANSI
SELECT g.G_NAME "상품명" ,COUNT(g.G_NAME) "필요수량"
FROM T_CUSTOMER c JOIN T_GIFT g ON c.C_POINT BETWEEN g.G_START AND g.G_END 
GROUP BY g.G_NAME;

-- #6203)  t_student 테이블과 t_exam01 시험성적 테이블,
-- t_credit 학점 테이블을 조회하여  학생들의 이름과 점수와 학점을 출력하세요
SELECT * FROM T_STUDENT ;
SELECT * FROM T_EXAM01 ;
SELECT * FROM T_CREDIT ;
--Oracle
SELECT s.NAME ,e.TOTAL , c.GRADE 
FROM T_STUDENT s , T_EXAM01 e, T_CREDIT c
WHERE s.STUDNO = e.STUDNO AND e.TOTAL BETWEEN c.MIN_POINT AND c.MAX_POINT 
ORDER BY c.GRADE ASC;
--ANSI
SELECT s.NAME ,e.TOTAL , c.GRADE 
FROM T_STUDENT s
JOIN T_EXAM01 e ON s.STUDNO = e.STUDNO
JOIN T_CREDIT c ON e.TOTAL BETWEEN c.MIN_POINT AND c.MAX_POINT 
ORDER BY c.GRADE ASC;

--#6204) t_customer 와 t_gift 테이블 join : 자기 포인트(c_point) 보다 낮은 포인트의 상품 중 한가지를 선택할수 있다고 할때 '
--산악용자전거'를 선택할 수 있는 고객명(c_name)과 포인트(c_point), 상품명(g_name)을 출력하세요
SELECT * FROM T_GIFT ;
SELECT * FROM T_CUSTOMER ;
--ORACLE
SELECT c.C_NAME ,c.C_POINT ,g.G_NAME 
FROM T_CUSTOMER c , T_GIFT g
WHERE  c.C_POINT  > g.G_START AND g.G_NAME = '산악용자전거';   
--ANSI
SELECT c.C_NAME , c.C_POINT, g.G_NAME
FROM T_CUSTOMER c JOIN T_GIFT g ON c.C_POINT  > g.G_START AND g.G_NAME = '산악용자전거';   

--#6205) t_emp2, t_post 테이블 사용하여 사원들의 이름(name)과 나이, 현재직급(post),  ‘예상직급’을 출력하세요. 
--‘예상직급’은 나이로 계산하며 해당 나이가 받아야 하는 직급을 의미합니다. 나이는 오늘(SYSDATE)을 기준으로 하되 소수점 이하는 절삭하여 계산하세요
--(t_emp2 의 직급(post) 은 null 허용함에 주의) ** 나이계산은 어떻게?  :    (현재연도 - 생년월일연도) + 1, SYSDATE, TO_CHAR() 사용
SELECT * FROM T_EMP2 ;
SELECT * FROM T_POST ;
--ORACLE
SELECT e.NAME "이름" , (TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(e.BIRTHDAY, 'YYYY') + 1 ) "현재나이", nvl(e.POST,' ') "현재직급" , p.POST "예상직급" 
FROM T_EMP2 e , T_POST p
WHERE (TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(e.BIRTHDAY, 'YYYY') + 1 ) BETWEEN p.S_AGE AND p.E_AGE ;
--ANSI
SELECT e.NAME "이름" , (TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(e.BIRTHDAY, 'YYYY') + 1 ) "현재나이", nvl(e.POST,' ') "현재직급" , p.POST "예상직급" 
FROM T_EMP2 e JOIN T_POST p
ON (TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(e.BIRTHDAY, 'YYYY') + 1 ) BETWEEN p.S_AGE AND p.E_AGE ;

--OUTER JOIN
-- #6206) t_student 테이블과 t_professor 테이블 Join 하여 학생이름과 지도교수 이름을 출력하세요. 단! 지도교수가 결정되지 않은 학생의 명단도 함께 출력하세요
--** 기존의 INNER Join 방식으로 먼저 만들어 보고 OUTER Join 을 해보고 비교해보자
--기존의 JOIN -- ANSI
SELECT s.name "학생이름", p.NAME "교수이름"
FROM T_STUDENT s JOIN T_PROFESSOR p 
ON s.PROFNO = p.PROFNO ;
--OUTER JOIN 으로 변경
SELECT s.name "학생이름", p.NAME "교수이름"
FROM T_STUDENT s LEFT OUTER JOIN T_PROFESSOR p 
ON s.PROFNO = p.PROFNO ;

--#6207) t_student, t_professor 테이블 join :  학생이름과 지도교수 이름을 출력, 
--단! 지도 학생이 결정되지 않은 교수 명단도 출력
SELECT s.name "학생이름", p.NAME "교수이름"
FROM T_STUDENT s RIGHT OUTER JOIN T_PROFESSOR p 
ON s.PROFNO = p.PROFNO ;

--#6208) t_student, t_professor 테이블 join :  
--학생이름과 지도교수 이름을 출력, 
--단! 지도 학생이 결정되지 않은 교수 명단, 지도교수가 결정되지 않은 학생명단 모두 출력
SELECT s.name "학생이름", p.NAME "교수이름"
FROM T_STUDENT s FULL OUTER JOIN T_PROFESSOR p 
ON s.PROFNO = p.PROFNO ;


--#6209) t_dept2 테이블에서 부서명 과 그 부서의 상위부서명을 출력하세요
--ORACLE
SELECT d1.DNAME "부서명" , d2.DNAME "상위부서명" 
FROM t_dept2 d1 , t_dept2 d2
WHERE d1.pdept = d2.dcode;
--ANSI
SELECT d1.DNAME "부서명" , d2.DNAME "상위부서명" 
FROM t_dept2 d1 JOIN t_dept2 d2 ON d1.pdept = d2.dcode;

--난이도 높음
--#6210) t_professor 테이블 : 교수번호, 교수이름, 입사일, 그리고 자신보다 입사일 빠른 사람의 인원수를 출력하세요, 
--단 자신보다 입사일이 빠른 사람수를 오름차순으로 출력하세요 hint: left outer 사용     / 그룹함수 사용
SELECT *FROM T_PROFESSOR ;

--
SELECT a.PROFNO "교수번호" , a.NAME "교수명" , a.HIREDATE "입사일",
        count(b.HIREDATE) "빠른입사일"
FROM T_PROFESSOR a LEFT OUTER JOIN T_PROFESSOR b
ON b.HIREDATE < a.HIREDATE 
GROUP BY a.PROFNO ,a.NAME ,a.HIREDATE 
ORDER BY 4;





