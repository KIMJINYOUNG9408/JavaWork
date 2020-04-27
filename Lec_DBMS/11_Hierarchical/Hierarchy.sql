-- 계층형 쿼리 ( Hierarchical Query )

SELECT * FROM t_Dept2; -- 서로의 계층관계 주목!

SELECT LPAD(dname, 10, '*') 부서명 FROM t_dept2;

/* 해설
 * LEVEL 은 오라클에서 계속 사용할 수 있는 것으로
 * 해당 데이터가 몇번째 단계 이냐를 의미하는 것.
 * 
 * CONNECT BY PRIOR  :  각 row 들이 어떻게 연결되어야 하는지 조건 지정
 * PRIOR를 어느쪽에 주느냐가 중요!
 */

--LEVEL
SELECT dname, LEVEL 
FROM T_DEPT2 
CONNECT BY PRIOR dcode = pdept
START WITH dcode =0001;

--LEVEL PRIOR 의 위치를 바꾼다면 ?
SELECT dname, LEVEL  
FROM T_DEPT2 
CONNECT BY dcode = PRIOR pdept
START WITH dcode =0001;

SELECT dcode, dname, PDEPT, LEVEL  
FROM T_DEPT2 
CONNECT BY dcode = PRIOR pdept
START WITH dcode =1005;

SELECT dcode, dname, PDEPT, LEVEL  
FROM T_DEPT2 
CONNECT BY dcode = PRIOR pdept
START WITH dcode =1011;

SELECT LPAD(DNAME , LEVEL * 6, '*')부서명 
FROM T_DEPT2 
CONNECT BY PRIOR DCODE = PDEPT 
START WITH DNAME = '사장실';


--------
SELECT * FROM T_EMP2 ;


SELECT LPAD(e.NAME || ' '  || d.dname || ' ' || NVL(e.post,'사원'), LEVEL*22, '-') "이름과 직급" 
FROM T_EMP2 e, (SELECT dname, dcode, pdept FROM t_dept2) d
WHERE e.DEPTNO = d.dcode
CONNECT BY PRIOR e.empno = e.PEMPNO 
START WITH e.empno = 20000101
;

---------------------
-- 0부터 뽑기
SELECT LEVEL-1 HR
FROM DUAL 
CONNECT BY LEVEL <= 24;
------------------------


