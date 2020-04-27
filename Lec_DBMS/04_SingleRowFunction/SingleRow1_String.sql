-- INITCAP() 함수

-- #4101  첫 문자 대문자로 바꾸어 출력
SELECT INITCAP('pretty girl')
FROM DUAL ;

-- #4102  학생 테이블(t_student)  에서 제1전공(deptno1) 이 201번인 학생들의 id를 첫 글자만 대문자로 출력 / initcap() 사용
SELECT INITCAP(id) 
FROM T_STUDENT 
WHERE DEPTNO1 = 201;

-- LOWER(), UPPER() 
--#4103 t_student 테이블 : 1전공(deptno1) 이 201번인 학생들의 id 를 이름과 함께 소문자, 대문자로 출력 / upper(), lower() 사용
SELECT NAME , id ,LOWER(id) "소문자", UPPER(id) "대문자" 
FROM T_STUDENT 
WHERE DEPTNO1 = 201;

--#4104) t_student 테이블 : ID가 9글자 이상인 학생들의 이름과 ID 와 글자수 출력 / length() 사용
SELECT NAME,id, LENGTH(ID) "글자수"
FROM T_STUDENT 
WHERE LENGTH(ID) >= 9 -- 함수 where 조건절에서 사용가능!

--#4105)t_student 테이블 : 1전공이 201번인 학생들의 이름과 이름의 글자수, 이름의 바이트 수를 출력하세요 / LENGTH(), LENGTHB() 사용 
SELECT NAME, LENGTH (NAME) 길이, LENGTHB(NAME ) 바이트
FROM T_STUDENT 
WHERE DEPTNO1 = 201;

-- CONCAT() 함수
--#4106) t_professor 테이블 :  101번 학과 (deptno) 의 교수들의 이름(name)과 직급(position)을 하나의 컬럼으로 출력하세요.  
--컬럼명은 "교수님명단" / concat() 사용 
SELECT CONCAT(NAME ,POSITION ) 교수님명단 
FROM T_PROFESSOR 
WHERE DEPTNO = 101;

--SUBSTR() 함수 > 음수 지원 맨 끝 = -1 
--#4107)t_student 테이블 : jumin 칼럼을 사용해서 1전공(deptno1)이 101번인 학생의 이름과 생년월일 출력 / substr() 사용
SELECT name, SUBSTR(JUMIN ,1,6) 생년월일
FROM T_STUDENT 
WHERE DEPTNO1 = 101;

--#4108) t_student 테이블 : jumin 칼럼을 사용해서  태어난 달이 8월인 사람의 이름과 생년월일을 출력하세요 / substr() 사용
SELECT NAME, SUBSTR(JUMIN ,1,6) 생년월일 
FROM T_STUDENT 
WHERE SUBSTR(JUMIN,4,1) = '8';

--#4109) t_student 테이블 : 4학년 학생들중 ‘여학생’ 들의 이름과 주민번호 출력 / substr() 사용
SELECT NAME ,JUMIN 
FROM T_STUDENT 
WHERE SUBSTR(JUMIN ,7,1) = '2' AND GRADE =4; 

--INSTR() 함수
SELECT INSTR('A*B*C*', '*', 1,1) FROM dual;  -- 2
SELECT INSTR('A*B*C*', '*', 1,2) FROM dual;  -- 4
SELECT INSTR('A*B*C*', '*', 3,2) FROM dual;  -- 6
SELECT INSTR('A*B*C*', '*', -4,1) FROM dual;  -- 2 음수인덱스의 경우 검색도 음의 방향으로 진행
SELECT INSTR('A*B*C*', '*', -4,2) FROM dual;  -- 0 없으면 0 리턴
SELECT INSTR('A*B*C*', '*', -2,2) FROM dual;  -- 2

-- #4110) t_student 테이블 : tel 칼럼을 사용하여 101번 학과(deptno1) 의 학생의 이름과 전화번호, ‘)’ 가 나오는 위치 출력  / instr() 사용
SELECT NAME ,TEL , INSTR(TEL ,')',1,1) 위치 -- INSTR(TEL , ')') 해도 가능 1,1 은 default 값
FROM T_STUDENT
WHERE DEPTNO1  = 101;

--#4111) t_student 테이블 : 1전공이 101 인 학생의 이름과 전화번호, 지역번호를 출력하세요. 지역번호는 숫자만!  / substr(), instr() 사용
SELECT NAME , TEL ,SUBSTR(TEL ,1, INSTR(TEL ,')')-1) 지역번호
FROM T_STUDENT 
WHERE DEPTNO1  = 101;

--LTRIM() , RTRIM(), TRIM()
SELECT 
LTRIM('슈퍼슈퍼슈가맨','슈퍼') LTRIM, 
LTRIM('슈퍼슈퍼슈가맨','슈') LTRIM, 
LTRIM('     슈퍼슈가맨') LTRIM,  -- 디폴트로 공백 제거
RTRIM('우측 공백 제거                      ') RTRIM,
TRIM('                슈퍼맨                 ') TRIM 
FROM  dual;

--#4116) t_dept2 테이블에서 부서명(dname) 을 출력하되 왼쪽에 ‘영’ 이란 글자를 모두 제거하고 출력하세요. 
SELECT LTRIM(DNAME ,'영') "LTRIM 예제"  
FROM T_DEPT2; 

--#4117) t_dept2 테이블에서 부서명(dname) 을 출력하되 오른쪽 끝에 ‘부’ 라는 글자는 제거하고 출력하세요.
SELECT RTRIM(DNAME , '부') "RTRIM 예제" 
FROM T_DEPT2; 

--REPLACE 함수
SELECT REPLACE ('슈퍼맨 슈퍼걸', '슈퍼' , '파워') REPLACE예제
FROM DUAL ;

--#4118) t_student 테이블에서 102번 학과(deptno1) 의 학생들의 이름을 출력하되 성 부분은 ‘#’  으로 표시되게 출력하세요 / replace() , substr()사용 
SELECT REPLACE (name, SUBSTR(NAME ,1,1), '#') 학생 
FROM T_STUDENT 
WHERE DEPTNO1 = 102;

--#4119) t_student 테이블에서 101번 학과(deptno1) 의 학생들의 이름을 출력하되 가운데 글자만 ‘#’  으로 표시되게 출력하세요 / replace() , substr()사용 
SELECT REPLACE (name , SUBSTR(name,2,1), '#' ) 학생
FROM T_STUDENT 
WHERE DEPTNO1 = 101;

--#4120) t_student 테이블에서 1전공(deptno1) 이 101번인 학생들의 이름과 주민등록번호를 출력하되 주민등록번호의 뒤 7자리는 ‘*’ 로 표시되게 출력 
SELECT NAME , REPLACE (JUMIN ,substr(jumin,7,7), '*******' ) 주민번호
FROM T_STUDENT 
WHERE DEPTNO1  = 101;

--#4121) Student 테이블에서 다음 과 같이 1전공(deptno1) 이 102번인 학생들의 이름(name) 과 전화번호(tel), 전화번호에서 국번 부분만 ‘#’ 처리하여 출력하세요.  
--단 모든 국번은 3자리로 간주합니다.
SELECT name ,tel, REPLACE(TEL ,SUBSTR(TEL,INSTR(TEL ,')')+1,3 ), '###' )전화번호 
FROM T_STUDENT 
WHERE DEPTNO1 = 102;