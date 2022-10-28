
create table if not exists users (
                                     id bigint not null primary key,
                                     name varchar(255) not null,
                                     password varchar(255) not null
);

create sequence if not exists users_sequence
 start 1
 increment 1;

create or replace view users_login as
select id,name from users order by id
        with local check option;

create  materialized view if not exists users_login_materialized as
select id,name from users order by id;