
SELECT company_name, order_id
FROM customers
LEFT JOIN orders ON customers.customer_id = orders.customer_id
WHERE order_id IS NULL;

/*Select quantity by left join*/
SELECT count(*)
FROM employees
LEFT JOIN orders ON employees.employee_id = orders.employee_id
where order_id IS NULL;