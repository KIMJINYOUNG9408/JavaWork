
/* Drop Tables */

DROP TABLE board CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE boardee
(
	user_uid number NOT NULL,
	name varchar2(20),
	subject varchar2(100) NOT NULL,
	content clob NOT NULL,
	viewcnt number DEFAULT 0,
	regdate date DEFAULT SYSDATE,
	PRIMARY KEY (user_uid)
	CONSTRAINT viewcnt_less
	CHECK (viewcnt > 0)
);



