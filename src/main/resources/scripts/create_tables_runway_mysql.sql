/* drop the tables and sequences */

USE RUNWAYDB;
SET AUTOCOMMIT = 0;


drop table if exists `EM_SEQUENCE_NUMBER_TABLE`;


drop table if exists `authorities`;
drop table if exists `users`;
drop table if exists `groups`;
drop table if exists `group_authorities`;
drop table if exists `group_members`;

drop table if exists `EM_USERS`;
drop table if exists `EM_USER_PROFILE`;
drop table if exists `EM_RESET_PASSWORD`;

drop table if exists `EMPLOYEES`;


CREATE TABLE EM_SEQUENCE_NUMBER_TABLE(
    ID varchar(255) not null primary key,
    SEQ_NO int
);



/**  Tables related to ACEGI security **/
CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    enabled boolean NOT NULL
);
            
CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL
);
                      
ALTER TABLE authorities ADD CONSTRAINT fk_authorities_users foreign key (username) REFERENCES users(username);

create table groups (
  ID bigint unsigned not null auto_increment primary key, 
  group_name varchar(50) not null);

create table group_authorities (
  group_id bigint not null, 
  authority varchar(50) not null 
  /*constraint fk_group_authorities_group foreign key(group_id) references groups(id)*/
);

create table group_members (
  ID bigint unsigned not null auto_increment primary key, 
  username varchar(50) not null, 
  group_id bigint not null
  /*constraint fk_group_members_group foreign key(group_id) references groups(id) */
);

insert into users values ( 'admin', 'admin123', true );
insert into authorities values ('admin', 'ROLE_ADMIN' );
/** END:  Tables related to ACEGI security **/

/** User Tables **/
CREATE TABLE EM_USERS(
    ID varchar(255) not null primary key,
    EMAIL varchar(255) not null,
    EMAIL_STATUS tinyint,
    PASSWORD varchar(255),
    FIRSTNAME varchar(255),
    LASTNAME varchar(255),
    STATUS tinyint,
    LAST_LOGIN timestamp,
    EM_TIME timestamp
);
CREATE INDEX EM_USERS_EMAIL_IDX ON EM_USERS( EMAIL);
INSERT INTO EM_USERS VALUES ('admin','admin@emarket.net', 0, 'admin123','administrator','emarket', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO EM_USERS VALUES('test', 'test@emarket.net',0, 'test123','test','user', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO EM_USERS VALUES('test1', 'snambi_75@yahoo.com',0, 'test123','test','user', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);



insert into EM_SEQUENCE_NUMBER_TABLE values('EM_RESET_PASSWORD_ID_SEQ', 1000000);
CREATE TABLE EM_RESET_PASSWORD(
	ID int primary key,
	USER_ID varchar(255) not null,
	EMAIL varchar(255) not null,
	CREATE_TIME timestamp,
	EXPIRE_TIME timestamp,
	HOST_NAME varchar(255),
	STATUS tinyint,
	EM_TIME timestamp
);
CREATE INDEX EM_RESET_PASSWORD_USER_EMAIL_IDX on EM_RESET_PASSWORD(USER_ID, EMAIL);

CREATE TABLE EM_USER_PROFILE(
	ID varchar(30) not null,
	KEY1 varchar(255) not null,
	VALUE varchar(255),
	EM_TIME timestamp
);
CREATE UNIQUE INDEX EM_USER_PROFILE_IDX ON EM_USER_PROFILE(ID, KEY1);



/* Tables related to EMPLOYEE */
insert into EM_SEQUENCE_NUMBER_TABLE values('EMPLOYEE_ID_SEQ', 1000000);
CREATE TABLE EMPLOYEES(
	ID int not null,
	NAME varchar(255),
	AGE int,
	ADDRESS varchar(255),
	CITY varchar(200),
	STATE varchar(200),
	ZIP_CODE varchar(25),
	EM_TIME timestamp
);
CREATE INDEX EMPLOYEE_IDX on EMPLOYEES( NAME, AGE, CITY, STATE, ZIP_CODE );


COMMIT;
