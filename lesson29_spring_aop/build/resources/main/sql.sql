create  table if not exists users (
    id bigint primary key,
    login varchar,
    first_name varchar);

create sequence if not exists user_id_sequence
start 1
increment 1;

insert into users (id, login, first_name)
values (nextval('user_id_sequence'), ?, ?);

select * from users where login = ?;

delete from users where login = ?;

SELECT COUNT(*) FROM users WHERE login = 'anatoly2';