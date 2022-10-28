/*Check automatic update data in view and materialized view */
/*materialized update dont update automatically, for update we need to use
refresh materialized view*/
insert into users (id, name, password)
values (nextval('users_sequence'), 'testLogin', 'testPassword');

insert into users (id, name, password)
values (nextval('users_sequence'), 'testLogin2', 'testPassword2');

refresh materialized view users_login_materialized;
