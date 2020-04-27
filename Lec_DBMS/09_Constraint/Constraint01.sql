-- 제약조건 (Constraint)
-- 테이블 생성시 동시에 설정하기

-- t_dept2.dcode 참조예정
SELECT * FROM t_dept2; -- dcode : 0001, 1000,1001...1011

--#9001 
--제약조건명을 명시하지 않는 방법
DROP TABLE t_emp3 CASCADE CONSTRAINT;
CREATE TABLE t_emp3 (
    no NUMBER(4) PRIMARY KEY,
    name VARCHAR2(10) NOT NULL,
    jumin VARCHAR2(13) NOT NULL UNIQUE,
    area NUMBER(1) CHECK(area < 5 ), 
    deptno VARCHAR2(6) REFERENCES t_dept2(dcode) 
);

--별도의 항목으로 제약조건 정의 가능 ( NOT NULL 은 별도로 정의 안된다 ) 
DROP TABLE t_emp4 CASCADE CONSTRAINT;
CREATE TABLE t_emp4 (
    no NUMBER(4),
    name VARCHAR2(10) NOT NULL,
    jumin VARCHAR2(13) NOT NULL,
    area NUMBER(1) , 
    deptno VARCHAR2(6) , 
    PRIMARY KEY(no),
    UNIQUE (jumin),
    CHECK(area < 5),
    FOREIGN KEY(deptno) REFERENCES t_dept2(dcode)
);

SELECT * FROM t_emp3;
SELECT * FROM T_EMP4;

-- #9002
-- 제약조건명을 명시하여 정의
DROP TABLE t_emp3 CASCADE CONSTRAINT;
CREATE TABLE t_emp3 (
    no NUMBER(4) CONSTRAINT emp3_no_pk PRIMARY KEY,
    name VARCHAR2(10) CONSTRAINT emp3_name_nn NOT NULL,
    jumin VARCHAR2(13) CONSTRAINT emp3_jumin_nn NOT NULL CONSTRAINT emp3_jumin_uk UNIQUE,
    area NUMBER(1) CONSTRAINT emp3_area_ck CHECK(area < 5 ), 
    deptno VARCHAR2(6) CONSTRAINT emp3_deptno_fk REFERENCES t_dept2(dcode) 
);

DROP TABLE t_emp3 CASCADE CONSTRAINT;
CREATE TABLE t_emp3 (
    no NUMBER(4),
    name VARCHAR2(10) NOT NULL,
    jumin VARCHAR2(13) NOT NULL,
    area NUMBER(1) , 
    deptno VARCHAR2(6) , 
    CONSTRAINT emp3_no_pk PRIMARY KEY(no),
    CONSTRAINT emp3_jumin_uk UNIQUE (jumin),
    CONSTRAINT emp3_area_ck CHECK(area < 5),
    CONSTRAINT emp3_deptno_fk FOREIGN KEY(deptno) REFERENCES t_dept2(dcode)
);

SELECT * FROM t_emp3;
SELECT * FROM T_EMP4;

-- #9003 제약조건 조회하기


SELECT owner, CONSTRAINT_name , CONSTRAINT_type, status
FROM USER_CONSTRAINTS
WHERE TABLE_name = 'T_EMP3'; -- 테이블명은 꼭 대문자로.

SELECT owner, CONSTRAINT_name , CONSTRAINT_type, status
FROM USER_CONSTRAINTS
WHERE TABLE_name = 'T_EMP4';

-- #9005 : 제약조건에 맞는 / 위배되는 DML 시도해보기.
INSERT INTO t_emp3 VALUES(1, '오라클' , '1234561234567', 4 , 1000);
--두번 실행시 오류 SQL Error [1] [23000]: ORA-00001: unique constraint (SCOTT0316.EMP3_NO_PK) violated
INSERT INTO t_emp3 VALUES(2, '오라클' , '1234561234567', 4 , 1000);
--주민 유니크 오류 SQL Error [1] [23000]: ORA-00001: unique constraint (SCOTT0316.EMP3_JUMIN_UK) violated  
INSERT INTO t_emp3 VALUES(2, '오라클' , '22222222222222', 4 , 1000);
--주민번호 13글자넘어감 SQL Error [12899] [72000]: 
--ORA-12899: value too large for column "SCOTT0316"."T_EMP3"."JUMIN" (actual: 14, maximum: 13)
INSERT INTO t_emp3 VALUES(2, '오라클' , '2222222222222', 10 , 1000);
-- Area가 5를 넘어서 오류
--SQL Error [1438] [22003]: ORA-01438: value larger than specified precision allowed for this COLUMN
INSERT INTO t_emp3 VALUES(2, '오라클' , '2222222222222', 3 , 2000);
--FK 오류 : 2000 값이 없음 참조하려는 값에
--SQL Error [2291] [23000]: ORA-02291: integrity constraint (SCOTT0316.EMP3_DEPTNO_FK)
-- violated - parent key not FOUND

INSERT INTO t_emp3 (NO, jumin, area, deptno) VALUES (2, '3333333333333',4,1001)
-- name이 NOTNULL 인데 NAME이 안들어가서 오류
--SQL Error [1400] [23000]: ORA-01400: cannot insert NULL into ("SCOTT0316"."T_EMP3"."NAME")

--INSERT 뿐 아니라 UPDATE / DELETE 에서도 오류 발생 가능
UPDATE t_emp3 SET area = 10 WHERE NO = 1;
-- CHECK 값 오류 AREA가 5를 넘어서 오류
--SQL Error [1438] [22003]: ORA-01438: value larger than specified precision allowed for this COLUMN

SELECT * FROM t_emp3
DELETE FROM t_dept2 WHERE dcode = 1000; 
--SQL Error [933] [42000]: ORA-00933: SQL command not properly ended
-- 자식이 참조하고 있기 때문에 이제 삭제 불가.

--#9005)   ALTER 명령 사용하여 테이블 에 제약조건 추가가능
--위에서 생성한 t_emp4 테이블의 name 컬럼에 UNIQUE 제약조건 추가하기
-- t_emp4 의 name 컬럼 UNIQUE 제약조건 추가.
--ALTER ~ ADD ALTER ~ MODIFY ALTER ~ DROP

ALTER TABLE t_emp4 ADD CONSTRAINT emp4_name_uk UNIQUE(name);

--#9006 t_emp4 테이블의 area 컬럼에 NOT NULL 제약조건 추가해보세요 
ALTER TABLE t_emp4 ADD CONSTRAINT emp4_area_nn NOT NULL(area);
-- 이미 컬럼의 기본값인 NULL 로 되어 있기 때문에 ADD 가 아닌 MODIFY로 해야 한다.
ALTER TABLE t_emp4 MODIFY (area CONSTRAINT emp4_area_nn NOT NULL);

--#9007)    외래키 추가
--t_emp4 테이블의 name 컬럼이 t_emp2 테이블의 name 컬럼의 값을 참조하도록 참조키 제약조건을 설정하세요
ALTER TABLE t_emp4 ADD CONSTRAINT emp4_name_fk FOREIGN KEY(name) REFERENCES t_emp2(name);
-- 참조되는 부모테이블의 컬럼은 PRIMARY KEY 이거나 UNIQUE 해야 한다!

-- 일단 부모 테이블의 name을 UNIQUE로 바꾼 뒤 위의 쿼리를 다시 실행해보세요.
ALTER TABLE t_emp2 ADD CONSTRAINT emp2_name_uk UNIQUE(name);



--#9008
DROP TABLE t_emp3 CASCADE CONSTRAINT;
CREATE TABLE t_emp3 (
    no NUMBER(4) CONSTRAINT emp3_no_pk PRIMARY KEY,
    name VARCHAR2(10) CONSTRAINT emp3_name_nn NOT NULL,
    jumin VARCHAR2(13) CONSTRAINT emp3_jumin_nn NOT NULL CONSTRAINT emp3_jumin_uk UNIQUE,
    area NUMBER(1) CONSTRAINT emp3_area_ck CHECK(area < 5 ), 
    deptno VARCHAR2(6) CONSTRAINT emp3_deptno_fk REFERENCES t_dept2(dcode)
    ON DELETE CASCADE -- 부모 삭제되면 자식도 삭제.
    --ON DELETE SET NULL -- 부모 삭제되면 NULL 값으로.
    
);

-- #9009 t_emp4 테이블의 name 필드의 제약조건을 수정 (ALTER TABLE)
--부모테이블이 삭제되면 NULL 이 되도록 설정하기
ALTER TABLE t_emp4 DROP CONSTRAINT emp4_name_fk; -- 기존 제약조건 삭제.

ALTER TABLE t_emp4 
ADD CONSTRAINT emp4_name_fk FOREIGN KEY(name) REFERENCES t_emp2(name)
ON DELETE SET NULL; -- 부모 삭제되면 자식은 null로 변환

-------------------------------
--DISABLE NOVALIDATE

--#9010
SELECT * FROM t_validate;
SELECT * FROM T_NOVALIDATE ;

SELECT OWNER, CONSTRAINT_name, CONSTRAINT_TYPE , status
FROM user_constraints
WHERE TABLE_name = 'T_VALIDATE';

SELECT OWNER, CONSTRAINT_name, CONSTRAINT_TYPE , status
FROM user_constraints
WHERE TABLE_name = 'T_NOVALIDATE';

INSERT INTO T_NOVALIDATE VALUES(1,'DDD'); -- 프라이머리 키 때문에 실패
INSERT INTO T_NOVALIDATE VALUES(1,'DDD'); -- 프라이머리 키 때문에 실패

ALTER TABLE T_NOVALIDATE 
DISABLE NOVALIDATE CONSTRAINT SYS_C007031; -- 껐씁니니다 기능 ENABLED -> DISABLED
INSERT INTO T_NOVALIDATE VALUES(1,'DDD'); -- 프라이머리 키 기능 끄고 실행하니까 성공

ALTER TABLE T_NOVALIDATE 
ENABLE NOVALIDATE CONSTRAINT SYS_C007031; -- 1이 2개가 들어와있으므로 프라이머리키 못킨다. 데이터를 삭제하고 켜야한다.

DELETE FROM T_NOVALIDATE WHERE NAME = 'DDD';

ALTER TABLE T_NOVALIDATE 
ENABLE NOVALIDATE CONSTRAINT SYS_C007031; -- 삭제한 후에는 가능.