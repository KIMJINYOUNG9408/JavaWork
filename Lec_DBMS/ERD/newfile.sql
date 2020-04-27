
/* Drop Tables */

DROP TABLE friends CASCADE CONSTRAINTS;
DROP TABLE friends_list CASCADE CONSTRAINTS;
DROP TABLE Likes CASCADE CONSTRAINTS;
DROP TABLE NEW_TABLE CASCADE CONSTRAINTS;
DROP TABLE NEW_TABLE CASCADE CONSTRAINTS;
DROP TABLE posts CASCADE CONSTRAINTS;
DROP TABLE MEMBER CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE friends
(
	id varchar2(15) NOT NULL,
	id2 varchar2(15) NOT NULL,
	status varchar2(1) DEFAULT 'X' NOT NULL,
	date date NOT NULL
);


CREATE TABLE friends_list
(
	id varchar2(15) NOT NULL,
	id2 varchar2(15) NOT NULL,
	level varchar2(10) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE Likes
(
	postno number NOT NULL,
	id varchar2(15) NOT NULL,
	date date DEFAULT SYSDATE
);


CREATE TABLE MEMBER
(
	id varchar2(15) NOT NULL,
	passwd varchar2(20) NOT NULL,
	Jumin varchar2(14) UNIQUE,
	phonenum varchar2(20),
	address clob,
	BirthDate varchar2(20),
	PRIMARY KEY (id)
);


CREATE TABLE NEW_TABLE
(
	postno number NOT NULL,
	id varchar2(15) NOT NULL
);


CREATE TABLE NEW_TABLE
(
	postno number NOT NULL,
	id varchar2(15) NOT NULL
);


CREATE TABLE posts
(
	postno number NOT NULL,
	id varchar2(15) NOT NULL,
	regDate date NOT NULL,
	Openlevel varchar2(10) NOT NULL,
	PRIMARY KEY (postno)
);



/* Create Foreign Keys */

ALTER TABLE friends
	ADD FOREIGN KEY (id2)
	REFERENCES MEMBER (id)
;


ALTER TABLE friends
	ADD FOREIGN KEY (id)
	REFERENCES MEMBER (id)
;


ALTER TABLE friends_list
	ADD FOREIGN KEY (id)
	REFERENCES MEMBER (id)
;


ALTER TABLE friends_list
	ADD FOREIGN KEY (id2)
	REFERENCES MEMBER (id)
;


ALTER TABLE Likes
	ADD FOREIGN KEY (id)
	REFERENCES MEMBER (id)
;


ALTER TABLE NEW_TABLE
	ADD FOREIGN KEY (id)
	REFERENCES MEMBER (id)
;


ALTER TABLE NEW_TABLE
	ADD FOREIGN KEY (id)
	REFERENCES MEMBER (id)
;


ALTER TABLE posts
	ADD FOREIGN KEY (id)
	REFERENCES MEMBER (id)
;


ALTER TABLE Likes
	ADD FOREIGN KEY (postno)
	REFERENCES posts (postno)
;


ALTER TABLE NEW_TABLE
	ADD FOREIGN KEY (postno)
	REFERENCES posts (postno)
;


ALTER TABLE NEW_TABLE
	ADD FOREIGN KEY (postno)
	REFERENCES posts (postno)
;



