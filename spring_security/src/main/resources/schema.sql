drop table  IF EXISTS  my_user;

create table my_user(
        id bigint not null primary key,
        login varchar not null ,
        password varchar not null,
        position  varchar not null,
        role varchar not null
);