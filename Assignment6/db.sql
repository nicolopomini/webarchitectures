drop database if exists webarch6;
create database webarch6;
use webarch6;

create table writer(
	id int unsigned auto_increment primary key,
	name varchar(200) not null,
	surname varchar(200) not null
);
create table book(
	id int unsigned auto_increment primary key,
	title varchar(200) not null,
	author int unsigned default null references writer(id) on update cascade on delete restrict,
	publicationYear int unsigned not null
);