/*create table if not exists roles
( id bigint not null primary key,
  name varchar (255) not null
);

CREATE SEQUENCE IF NOT EXISTS roles_id_seq
start 1
increment 1;

create table if not exists users
(   id bigint not null primary key,
    login varchar (255) not null,
    password varchar(255) not null
);

CREATE SEQUENCE IF NOT EXISTS users_id_seq
start 1
increment 1;*/
CREATE SEQUENCE IF NOT EXISTS roles_id_seq
start 1
increment 1;

CREATE SEQUENCE IF NOT EXISTS users_id_seq
start 1
increment 1;


create table if not exists users
(
    id bigint not null primary key,
    login varchar(255) unique not null,
    password varchar(255) not null
);

create table if not exists roles
(
    id bigint not null primary key,
    role varchar(255) not null,
    user_id  bigint  references users
);

