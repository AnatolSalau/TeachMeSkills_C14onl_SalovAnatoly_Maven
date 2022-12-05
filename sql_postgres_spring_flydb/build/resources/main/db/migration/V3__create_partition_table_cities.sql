drop table if exists cities;
create table if not exists cities (
                                     id bigint not null,
                                     name varchar(255) not null,
                                     region varchar(255) not null,
                                     password varchar(255) not null,
                                     PRIMARY KEY (id, region)
                                  )
 partition by list (region);

drop table if exists cities_id_sequence;
create sequence if not exists cities_id_sequence
start 1
increment 1;

create table cities_particion_north partition of cities for values in ('north');
create table cities_particion_south partition of cities for values in ('south');
create table cities_particion_west partition of cities for values in ('west');
create table cities_particion_east partition of cities for values in ('east');


