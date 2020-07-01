
/* Drop Tables */

DROP TABLE test_card CASCADE CONSTRAINTS;
DROP TABLE test_ticket CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE test_card
(
	user_id varchar2(20) NOT NULL,
	buy_amount number DEFAULT 1
);


CREATE TABLE test_ticket
(
	user_id varchar2(20) NOT NULL,
	ticket_count number NOT NULL,
	CONSTRAINT ticket_buy_limit
	CHECK (ticket_count BETWEEN 1 AND 5)

);

-- 티켓 2장을 카드로 결제하는 경우 --
INSERT INTO TEST_CARD VALUES ('aaa',2);
INSERT INTO TEST_TICKET VALUES ('aaa',2);

-- 티켓 5장을 카드로 결제하는 경우 --
INSERT INTO TEST_CARD VALUES ('aaa',5);
INSERT INTO TEST_TICKET VALUES ('aaa',5);

-- 티켓 6장을 카드로 결제하는 경우 --
INSERT INTO TEST_CARD VALUES ('aaa',6); -- OK
INSERT INTO TEST_TICKET VALUES ('aaa',6); -- ERROR
CREATE SEQUENCE users_uid_seq;


SELECT * FROM TEST_CARD ; 
SELECT * FROM TEST_TICKET ;

CREATE TABLE boardee
(
	user_uid number NOT NULL,
	name varchar2(20),
	subject varchar2(100) NOT NULL,
	content clob NOT NULL,
	viewcnt number DEFAULT 0,
	regdate date DEFAULT SYSDATE,
	PRIMARY KEY (user_uid),
	
	CHECK (viewcnt >= 0)
);

INSERT INTO boardee VALUES(1,'d','dd','dd', 4, sysdate)
