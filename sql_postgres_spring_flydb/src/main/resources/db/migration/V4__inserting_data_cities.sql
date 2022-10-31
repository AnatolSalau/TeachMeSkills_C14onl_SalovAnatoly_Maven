/*Inserting data in partition table cities*/

insert into cities (id, name, region, password)
values  (nextval('cities_id_sequence'), 'archangelsk', 'north','password'),
        (nextval('cities_id_sequence'), 'minsk', 'west','password'),
        (nextval('cities_id_sequence'), 'vladivostok', 'east','password'),
        (nextval('cities_id_sequence'), 'krasmoyarsk', 'south','password');
