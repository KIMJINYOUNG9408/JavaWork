
/* Drop Tables */

DROP TABLE a_test CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE a_test
(
	a_uid number NOT NULL,
	user_id varchar2(30),
	user_pw varchar2(30),
	user_name varchar2(20),
	user_age number,
	user_gender char,
	user_jumin varchar2(13),
	user_authorize number,
	user_pathid varchar2(30),
	PRIMARY KEY (a_uid)
);