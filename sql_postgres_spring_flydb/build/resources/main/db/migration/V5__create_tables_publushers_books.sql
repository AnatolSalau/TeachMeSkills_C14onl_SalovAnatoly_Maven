CREATE TABLE IF NOT EXISTS publishers (
    id bigint PRIMARY KEY NOT NULL,
    name varchar(256) NOT NULL,
    adress text NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS publishers_sequence
    START 1
    INCREMENT 1;

/*Create table books without foreign column*/
/*CREATE TABLE IF NOT EXISTS books (
    id bigint PRIMARY KEY NOT NULL,
    title text NOT NULL,
    isbn varchar(256) NOT NULL
);*/

/*Create table books with foreign key*/
CREATE TABLE IF NOT EXISTS books (
 id bigint PRIMARY KEY NOT NULL,
 title text NOT NULL,
 isbn varchar(256) NOT NULL,
 publisher_id bigint REFERENCES publishers (id) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS books_sequence
    START 1
    INCREMENT 1;

/*Create foreign key column*/
/*ALTER TABLE books
    ADD COLUMN publishers_id BIGINT NOT NULL ;

ALTER TABLE books
    ADD CONSTRAINT fk_publishers_id
        FOREIGN KEY (publishers_id)
            REFERENCES publishers(id);*/