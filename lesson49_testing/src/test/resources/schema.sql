drop table IF EXISTS users;
create table users(
                        id bigint not null primary key,
                        login varchar not null ,
                        password varchar not null
);