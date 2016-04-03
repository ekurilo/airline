create database aircompany;

create table if not exists user_roles (
role_id int not null,
role varchar(20) not null unique,
primary key(role_id)
);

insert into user_roles values (0, 'admin');
insert into user_roles values (1, 'dispatcher');

create table if not exists users (
user_id int not null auto_increment,
login varchar(20) not null unique,
password varchar(20) not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
email varchar(45) not null,
role_id int not null,
primary key(user_id),
foreign key (role_id) references user_roles(role_id) on delete cascade on update restrict

);
insert into users(login, password, first_name, last_name, email, role_id) values('admin', 'admin', 'Ivan', 'Ivanov','ivanov@mail.com',0);
insert into users(login, password, first_name, last_name, email, role_id) values('user1', 'user1', 'Petr', 'Petrov','petrov@mail.com',1);



create table if not exists professions(
profession_id int not null,
profession varchar(20) not null unique,
primary key (profession_id)
);

insert into professions values(0, 'pilot');
insert into professions values(1, 'navigator');
insert into professions values(2, 'radioman');
insert into professions values(3, 'stewardress');

create table if not exists employees(
id int not null auto_increment,
first_name varchar(20) not null,
last_name varchar(20) not null,
profession_id int not null,
primary key(id),
foreign key(profession_id) references professions(profession_id)
);


create table if not exists brigades (
brigade_id int not null auto_increment,
name varchar(40),
user_id int not null,
primary key(brigade_id),
foreign key(user_id) references users(user_id) on delete cascade on update restrict


);


create table if not exists flight_statuses(
id int not null,
status varchar(20) not null unique,
primary key(id)
); 

insert into flight_statuses values (0, 'opened');
insert into flight_statuses values (1, 'done');
insert into flight_statuses values (2, 'canceled');

create table if not exists flights(
flight_id int not null auto_increment,
name varchar(60) not null,
from_port varchar(40) not null,
to_port varchar(40) not null,
date_flight date not null,
time_flight time not null,
brigade_id int not null,
status_id int not null,
primary key(flight_id),
foreign key(status_id) references flight_statuses(id) on delete cascade on update restrict,
foreign key(brigade_id) references brigades(brigade_id) on delete cascade on update restrict
);

create table if not exists application_statuses(
id int not null,
status varchar(20) not null unique,
primary key(id)
);

insert into application_statuses (id, status) values(0, 'opened');
insert into application_statuses (id, status) values(1, 'done');
insert into application_statuses (id, status) values(2, 'rejected');


create table if not exists applications(
id int not null auto_increment,
title varchar(100) not null,
brigade_id int not null,
status_id int not null,
primary key(id),
foreign key(status_id) references application_statuses(id) on delete cascade on update restrict,
foreign key(brigade_id) references brigades(brigade_id) on delete cascade on update restrict

);


create table if not exists brigades_employees(
brigade_id int not null,
employee_id int not null,
foreign key(brigade_id) references brigades(brigade_id) on delete cascade on update restrict,
foreign key(employee_id) references employees(id) on delete cascade on update restrict
);