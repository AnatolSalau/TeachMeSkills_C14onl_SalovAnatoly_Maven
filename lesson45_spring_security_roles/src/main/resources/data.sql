insert into roles (id, name)
values (1, 'ADMIN'),
       (2, 'DOCTOR'),
       (3, 'USER');

insert into users (id, login, password)
values (1, 'admin', '$2a$12$MWSgG3DAUk5zmWCKuZ6ohuItYk4z73t4wpu.m9yIC2JiTum0xY9bK'),
       (2, 'doctor', '$2a$12$t3btj.mOyPWOlSsxvG4Ajev/y9nDunjwUa7Pp1CnIkDHN41ZwGbdW'),
       (3, 'user', '$2a$12$14MAJqLY1kmV7aLpaXaHwu1ILzl5hQHRuX6M5IA2dzB.PGQRmLwH6');

insert into users_roles(user_id,role_id)
values (1,1),
       (2,2),
       (3,3);


