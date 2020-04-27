SELECT * FROM T_PROFESSOR ;
SELECT COUNT(*), COUNT(HPAGE) FROM T_PROFESSOR ; -- 그룹함수에서는 NULL 값은 계산에서 제외

SELECT COUNT(bonus), sum(bonus), avg(BONUS) FROM T_PROFESSOR ; 
SELECT max(hiredate), min(HIREDATE) FROM T_EMP ;

--null 허용 컬럼의 그룹함수 적용시
--nv1, nv12 사용해야함
SELECT avg(bonus),avg(nvl(bonus,0)) 
FROM T_PROFESSOR; 

--예) t_professor 테이블에서 ‘학과별’로 교수들의 평균 보너스를 출력하세요
--그룹함수는 그룹함수끼리만 .  오류난다.
--SELECT deptno, AVG(bonus) FROM t_professor;  ← 에러
SELECT DEPTNO, ROUND(AVG(nvl(BONUS,0)),1) "보너스 평균"
FROM T_PROFESSOR 
GROUP BY DEPTNO ;

--#5101) t_professor 테이블 : 학과별(deptno) 그리고 직급별(position)로 교수들의 평균 급여를 계산하여 출력하세요
SELECT DEPTNO, POSITION, avg(pay)
FROM T_PROFESSOR 
GROUP BY DEPTNO,POSITION -- 1. 학과별 그룹핑 -> 직책별 그룹핑
ORDER BY deptno ASC, POSITION ASC ;

--부서별 평균급여를 출력하되, 평균급여가 450 보다 많은 부서만 출력
SELECT DEPTNO, POSITION, avg(pay)
FROM T_PROFESSOR 
GROUP BY DEPTNO,POSITION -- 1. 학과별 그룹핑 -> 직책별 그룹핑
HAVING avg(pay) >= 450 -- 그룹함수에서는 WHERE을 사용 못하고 Having 조건절을 사용해줘야 한다.
ORDER BY deptno ASC, POSITION ASC; --  정렬

--#5102) t_emp 테이블: 매니저별(MGR)로 관리하는 직원들의 ‘매니저’, ‘직원수’와 ‘급여총액’과 ‘급여평균’과 ‘교통비 (COMM) 평균’ 지급액 을 출력하세요.
-- 단 사장님은 (job = president)제외 
SELECT * FROM T_EMP ;
SELECT MGR "매니저",COUNT(MGR) "직원수" ,
sum(SAL) "급여총액" , ROUND(avg(SAL),1) "급여평균",
AVG(NVL(COMM ,0)) 
FROM T_EMP  
GROUP BY MGR 
HAVING count(MGR) > 0;

--#5103) t_professor 테이블 :  직위가 정교수 혹은 조교수 인 분들 중에서 ‘과별(deptno)’로  과번호, 
--소속교수 총수, 근속일 평균, 급여평균, 보너스 평균을 출력해보세요
SELECT DEPTNO , COUNT(DEPTNO) "총인원" ,round(avg(sysdate-hiredate),1) 근속평균,
avg(pay) "급여평균" , avg(NVL(bonus,0)) "보너스 평균"
FROM T_PROFESSOR 
WHERE POSITION = '정교수' OR POSITION = '조교수'
GROUP BY DEPTNO; 

--#5104)t_student 테이블 : 학과별(deptno1) 로,  학과번호, 최대몸무게 - 최소몸무게 차이 값을 출력해보세요
SELECT DEPTNO1 "학과", max(WEIGHT)-min(WEIGHT) "최대최소몸무게차" 
FROM T_STUDENT 
GROUP BY DEPTNO1

--#5104) t_student 테이블 : 학과별(deptno1) 로,  학과번호, 최대몸무게 - 최소몸무게 차이 값 이 30이상인거만 출력
SELECT DEPTNO1 "학과", max(WEIGHT)-min(WEIGHT) "최대최소몸무게차" 
FROM T_STUDENT 
GROUP BY DEPTNO1
HAVING max(WEIGHT)-min(WEIGHT) >= 30;