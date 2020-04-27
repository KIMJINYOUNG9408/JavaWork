--VIEW
CREATE OR REPLACE VIEW v_prof -- OR REPLACE는 기존에 있던거 지우고 새로만들어라
AS
SELECT profno, name , email , hpage
FROM T_PROFESSOR 
;

SELECT * FROM v_prof;

SELECT tname FROM tab; -- VIEW 확인 가능

--VIEW 생성시 별도의 컬럼 이름을 지정해줄수있따.
CREATE OR REPLACE VIEW v_prof(phno, nm , em , hp) -- OR REPLACE는 기존에 있던거 지우고 새로만들어라
AS
SELECT profno, name , email , hpage
FROM T_PROFESSOR;


SELECT * FROM v_prof;

--#8102) t_professor, t_department 테이블을 join 하여 교수번호와 교수이름과 소속학과 이름을 조회하는 view 를 생성하세요.  
--(이름: v_prof_dept)
CREATE OR REPLACE VIEW v_prof_deft("교수번호", "교수명", "소속학과명")
AS
SELECT p.PROFNO , p.NAME , t.DNAME 
FROM T_PROFESSOR p , T_DEPARTMENT t
WHERE p.DEPTNO  = t.DEPTNO ;

SELECT * FROM v_prof_deft

--#8103) t_student, t_department 테이블 : 학과별로 학생들의 최대키와 최대몸무게, 학과 이름을 출력하세요
SELECT 
	d.dname "학과명", 
	s.max_height "최대키", 
	s.max_weight "최대몸무게"
FROM 
	( SELECT deptno1, MAX(height) max_height, MAX(weight) max_weight
	FROM t_student
	GROUP BY deptno1 ) s , 
	t_department d
WHERE 
	s.deptno1 = d.deptno;
--#8104) t_student, t_department 테이블 : 학과별(deptno1)로 가장 키가 큰 학생들의 이름과 키, 
--학과이름을 인라인뷰 를 사용하여 다음과 같이 출력하세요
SELECT d.DNAME  "학과명", a.max_height "최대키" , s.NAME "학생이름", s.HEIGHT "키"
FROM (SELECT deptno1, MAX(HEIGHT) max_height FROM t_student GROUP BY deptno1) a , 
      T_STUDENT s , t_department d
WHERE s.DEPTNO1  = a.deptno1 AND s.HEIGHT  = a.max_height
        AND s.DEPTNO1  = d.DEPTNO 

--#8105) t_student 테이블 : 학생의 키가 동일 학년의 평균 키보다 큰 학생들의 학년과 이름과 키,
--해당 학년의 평균키를 출력하되, inline view 를 사용해서 아래와 같이 출력하세요. 단 학년 칼럼은 오름 차순으로 정렬.

 
SELECT s.grade , s.NAME ,s.HEIGHT , a.avg_height
FROM (SELECT grade, avg(height) avg_height FROM T_STUDENT GROUP BY grade ) a , t_student s
WHERE a.grade = s.GRADE AND s.HEIGHT > a.avg_height
ORDER BY 1;
