--날짜 함수

-- 오늘 날짜
SELECT SYSDATE FROM DUAL ;  

-- 기본적인 날짜 연산
SELECT SYSDATE "오늘",
SYSDATE + 1 "내일(24h 뒤)",
SYSDATE - 2 "그저께",
SYSDATE + 1/24 "한시간 뒤"
FROM DUAL ;

--일자 차이 계산
SELECT SYSDATE "오늘",
TO_DATE('2020-03-16 09:00:00', 'YYYY-MM-DD hh:mi:ss') "강의 시작일",
SYSDATE - TO_DATE('2020-03-16 09:00:00', 'YYYY-MM-DD hh:mi:ss') "경과 시간"
FROM DUAL ;

--MONTHS_BETWEEN : 날짜 사이의 개월 수
SELECT 
--규칙1 : 두 날짜 중 큰 날짜를 먼저 써야 양수값으로 나옴
MONTHS_BETWEEN('2012-03-01', '2012-01-01') 양수값, 
MONTHS_BETWEEN('2012-01-01', '2012-03-01') 음수값,
--규칙2: 두 날짜가 같은 달에 속해 있으면 특정 규칙으로 계산된 값이 나온다.
MONTHS_BETWEEN('2012-02-29', '2012-02-01') 개월차,
MONTHS_BETWEEN('2012-04-30', '2012-04-01') 개월차,
MONTHS_BETWEEN('2012-05-31', '2012-05-01') 개월차
FROM DUAL ;

--#4501) t_professor 테이블에서 오늘(SYSDATE)을 기준으로 
--근속연수, 근속개월,  근속일를 계산해서 출력
--날짜양식은 YYYY-MM-DD 로,  근속개월, 근속일은 반올림 하여 소수점 1자리까지 표현
SELECT 
NAME , SYSDATE "오늘", HIREDATE "입사일",
TO_CHAR(SYSDATE, 'YYYY' ) - TO_CHAR(HIREDATE , 'YYYY') -1 "근속연수",
ROUND(MONTHS_BETWEEN (SYSDATE,HIREDATE),1) "근속개월",
ROUND(SYSDATE-hiredate, 1) "근속일" 
FROM T_PROFESSOR ;

--ADD_MONTH() 개월 추가
SELECT SYSDATE, ADD_MONTHS(SYSDATE,3 ) "3개월 뒤" FROM DUAL;

SELECT 
SYSDATE,
LAST_DAY(SYSDATE) "이번달 마지막날",   -- 이번달 마지막날
NEXT_DAY(SYSDATE, '수') "다음 수요일"  -- 다음 특정 요일의 날짜
FROM DUAL ;

-- 날짜의 ROUND() 함수  ,  하루의 반은 정오 12:00:00 이다. 이를 넘어서면 다음 날짜
-- 날짜의 TRUNC() 함수,  무조건 당일 출력.
-- 원서 접수나 상품 주문 등에서 오전까지 접수된 건은 당일 접수 처리. 오후접수는 익일 처리 등에서 사용.
SELECT SYSDATE,ROUND(SYSDATE),TRUNC(SYSDATE)  
FROM DUAL ;