
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


insert into users(id, login, password) values (1, 'user' , '$2a$12$sU2TvDP.zj7P5vYooJNNwubl1CavXS0sTJycyrA2SgHCCWCdO3mwu' ) ,
                                             (2, 'doctor' ,'$2a$12$Hrm75q6NsauybkERr3Kz9eKX1JzIm2FaGMpRmg9t7HizfnPoqVG/2' ) ,
                                            (3, 'admin' , '$2a$12$O56yvyUr4weCFGPcgiKhKu6wFu//fEjbzdRnM1ZQp3HkEkGfg9Q5.' ) ;

insert into roles(id, role, user_id ) values (1, 'USER', 1 ) ,
                                    (2, 'DOCTOR', 1 ) ,
                                    (3, 'ADMIN', 1 ) ;
