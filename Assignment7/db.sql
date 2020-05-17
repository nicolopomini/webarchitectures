drop database if exists webarch7;
create database webarch7;
use webarch7;

create table professor(
	id int unsigned auto_increment primary key,
	name varchar(200) not null,
	surname varchar(200) not null
);
create table student(
	id int unsigned auto_increment primary key,
	name varchar(200) not null,
	surname varchar(200) not null,
	matricola varchar(10) not null unique
);
create table course(
	id int unsigned auto_increment primary key,
	name varchar(100) not null,
	professor int unsigned references professor(id) on update cascade on delete restrict
);
create table takes_course(
	student int unsigned references student(id) on update cascade on delete restrict,
	course int unsigned references course(id) on update cascade on delete restrict,
	primary key(student, course)
);
create table exam(
	id int unsigned auto_increment primary key,
	course int unsigned references course(id) on update cascade on delete restrict,
	date varchar(20) not null
);
create table enrolled(
	student int unsigned references student(id) on update cascade on delete restrict,
	exam int unsigned references exam(id) on update cascade on delete restrict,
	mark varchar(10),
	primary key(student, exam)
);