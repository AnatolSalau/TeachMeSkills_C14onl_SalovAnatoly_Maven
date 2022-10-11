insert into roles (id, name)
values (1, 'ADMIN'),
       (2, 'DOCTOR'),
       (3, 'USER');

insert into users (id, login, password)
values (1, 'admin', 'admin'),
       (2, 'doctor', 'doctor'),
       (3, 'user', 'user ');

insert into users_roles(user_id,role_id)
values (1,1),
       (2,2),
       (3,3);


