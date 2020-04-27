SELECT * FROM T_PROFESSOR ;

--null 값 과의 연산 결과는 null 이다!
SELECT name,PAY ,BONUS, pay+BONUS 
FROM T_PROFESSOR ;

-- 그룹함수 에서는 동작 , null 은  연산에서 제외되어 동작
SELECT sum(BONUS ) , sum(pay)
FROM T_PROFESSOR; 

-- nvl() 함수  --> NULL value 약자
SELECT name,PAY ,BONUS, pay+bonus , pay + NVL(bonus, 0) 총지급액 
FROM T_PROFESSOR ;

--#4201) Professor 테이블 : 101번 학과 교수들의 이름(name), 급여(pay), 보너스(bonus ), 연봉(pay) 출력하기.  
--연봉은 pay * 12 + bonus 로 계산,  bonus 가 없는 교수는 0 으로 계산
SELECT name,pay ,NVL(bonus, 0) 보너스 , pay*12 + NVL(bonus,0) 연봉
FROM T_PROFESSOR 
WHERE DEPTNO = 101;

--#4202) professor 테이블 :  101번 학과 교수의 이름과 pay, bonus, 연봉 출력,  
--연봉은 pay*12 + bonus 로 계산, bonus 가 없으면 급여를 0 으로 처리 (nvl2 사용)
SELECT name,pay ,NVL2(bonus, bonus,0) 보너스 , pay*12 + NVL2(bonus,BONUS ,0) 연봉
FROM T_PROFESSOR 
WHERE DEPTNO = 101;