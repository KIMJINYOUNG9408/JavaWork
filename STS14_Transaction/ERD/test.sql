
/* Drop Tables */

DROP TABLE users CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE users
(
	uids number NOT NULL,
	id varchar2(20) NOT NULL UNIQUE,
	pw varchar2(20) NOT NULL,
	name varchar2(20) NOT NULL,
	email varchar2(50) UNIQUE,
	PRIMARY KEY (uids)
);



CREATE SEQUENCE users_uid_seq;

