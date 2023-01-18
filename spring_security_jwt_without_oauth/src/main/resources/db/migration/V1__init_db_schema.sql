create table if not exists roles
( id bigint not null primary key,
  name varchar (255) not null
);
create table if not exists users
(   id bigint not null primary key,
    login varchar (255) not null,
    password varchar(255) not null
);

insert into roles (id, name)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_DOCTOR'),
       (3, 'ROLE_USER');
insert into users (id, login, password)
values (1, 'admin',
        '$2a$12$MWSgG3DAUk5zmWCKuZ6ohuItYk4z73t4wpu.m9yIC2JiTum0xY9bK'),
       (2, 'doctor',
        '$2a$12$t3btj.mOyPWOlSsxvG4Ajev/y9nDunjwUa7Pp1CnIkDHN41ZwGbdW'),
       (3, 'user',
        '$2a$12$14MAJqLY1kmV7aLpaXaHwu1ILzl5hQHRuX6M5IA2dzB.PGQRmLwH6');