insert into roles (id, name)
values (1, 'ADMIN'),
       (2, 'DOCTOR'),
       (3, 'USER');

insert into users (id, login, password)
values (1, 'admin', '$2a$12$TiA3ypXM.dVuKGUBHolev.ldRXNQx7yvaBV7ad.C4sxURgjO9nxnK'),
       (2, 'doctor', '$2a$12$uBgyvoDOqbQYqzsom8nbru2qhkEN1tHVBOglBZagvNwfqmhswmAx.'),
       (3, 'user', '$2a$12$qkjeKiQUs00.g8n360JBDesVI58dL5l/skKvYl3sYD/dZZhR1lWz.');

insert into users_roles(user_id,role_id)
values (1,1),
       (2,2),
       (3,3);


