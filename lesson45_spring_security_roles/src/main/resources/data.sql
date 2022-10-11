insert into roles (id, name)
values (1, 'ADMIN'),
       (2, 'DOCTOR'),
       (3, 'USER');

insert into users (id, login, password)
values (1, 'admin', 'admin'),
       (2, 'doctor', 'doctor'),
       (3, 'user', '$2a$12$W0Nl7weXLTWsXsz5xt09O.z6i.lViKTBszvhNCZcAKag751vr0duq');

insert into users_roles(user_id,role_id)
values (1,1),
       (2,2),
       (3,3);


