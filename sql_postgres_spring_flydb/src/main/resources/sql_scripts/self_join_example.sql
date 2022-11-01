/*Cycle reference foreign key */
/*Self join need if table has cycle reference*/
CREATE TABLE employees (
        id INT PRIMARY KEY NOT NULL,
        first_name VARCHAR (255) NOT NULL,
        last_name VARCHAR (255) NOT NULL,
        manager_id INT,
        FOREIGN KEY (manager_id) REFERENCES employees (id)
    );