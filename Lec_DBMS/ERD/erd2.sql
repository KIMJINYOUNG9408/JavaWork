
/* Drop Tables */

DROP TABLE register CASCADE CONSTRAINTS;
DROP TABLE subject CASCADE CONSTRAINTS;
DROP TABLE professor CASCADE CONSTRAINTS;
DROP TABLE student CASCADE CONSTRAINTS;
DROP TABLE soffice CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE professor
(
	profno number NOT NULL,
	jumin varchar2(14) NOT NULL,
	addr clob,
	name varchar2(10) NOT NULL,
	positon varchar2(10) NOT NULL,
	phonenum varchar2(13),
	hiredate_year number,
	classno number NOT NULL,
	PRIMARY KEY (profno)
);


CREATE TABLE register
(
	studno number NOT NULL,
	subno number NOT NULL
);


CREATE TABLE soffice
(
	classno number NOT NULL,
	callnum varchar2(13) NOT NULL,
	officezone varchar2(30) NOT NULL,
	name varchar2(10) NOT NULL,
	PRIMARY KEY (classno)
);


CREATE TABLE student
(
	studno number NOT NULL,
	jumin varchar2(14) NOT NULL,
	name varchar2(10) NOT NULL,
	grade number,
	phonenum varchar2(13),
	address clob,
	classno number NOT NULL,
	PRIMARY KEY (studno)
);


CREATE TABLE subject
(
	subno number NOT NULL,
	name varchar2(10) NOT NULL,
	hourcnt number NOT NULL,
	learner number NOT NULL,
	place varchar2(5),
	start_year number,
	profno number NOT NULL,
	PRIMARY KEY (subno)
);



/* Create Foreign Keys */

ALTER TABLE subject
	ADD FOREIGN KEY (profno)
	REFERENCES professor (profno)
;


ALTER TABLE professor
	ADD FOREIGN KEY (classno)
	REFERENCES soffice (classno)
;


ALTER TABLE student
	ADD FOREIGN KEY (classno)
	REFERENCES soffice (classno)
;


ALTER TABLE register
	ADD FOREIGN KEY (studno)
	REFERENCES student (studno)
;


ALTER TABLE register
	ADD FOREIGN KEY (subno)
	REFERENCES subject (subno)
;



