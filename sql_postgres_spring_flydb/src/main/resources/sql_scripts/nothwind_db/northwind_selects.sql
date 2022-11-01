/*Select three columns from products*/
SELECT product_id, product_name, unit_price
FROM products;

/*Select three columns and all costs(by multiple)*/
SELECT product_id, product_name, unit_price * units_in_stock as all_price
FROM products;

/*Select only unique data from column by DISTINCT*/
/*Select only unique cities from column city in employees*/
SELECT DISTINCT city
FROM employees;

/*Select unique data from two colomns city (unique row), country by DISTINCT*/
SELECT DISTINCT city, country
FROM employees;

/*Select count unique countries from column country in employees*/
SELECT count(DISTINCT country)
from employees;

/*Select count (non unique countries) of count*/
SELECT count(country)
from employees;

/*Select customers only from usa*/
SELECT company_name, contact_name, phone
FROM customers
WHERE country = 'USA';

/*SELECT products*/
SELECT count(*)
FROM products
WHERE unit_price < 20;

/*SELECT * WHERE order_date >= 1998-03-01*/
SELECT * FROM orders
WHERE order_date >= '1998-03-01';

/*Select with where */
SELECT *
FROM products
WHERE unit_price > 25 AND units_in_stock > 40;

SELECT *
FROM customers
WHERE city = 'Toulouse' OR city = 'Helsinki' OR city = 'Madrid';

/*BETWEEN 20 AND 40 the same as >= and <= (including boundaries)*/
SELECT count(*)
FROM orders
WHERE freight between 20 and 40;

/*Select with order by DESC (reverse order) ASC (front order)*/
SELECT DISTINCT country, city
FROM customers
ORDER BY country DESC, city ASC;

