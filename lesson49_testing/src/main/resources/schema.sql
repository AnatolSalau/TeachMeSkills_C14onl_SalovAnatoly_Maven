drop table if exists users;
create table users
(
    id          bigint not null primary key,
    login       varchar(255),
    password    varchar(255)
);
/*drop sequence if exists user_sequence;
create sequence user_sequence
    start 1
    increment 1;*/