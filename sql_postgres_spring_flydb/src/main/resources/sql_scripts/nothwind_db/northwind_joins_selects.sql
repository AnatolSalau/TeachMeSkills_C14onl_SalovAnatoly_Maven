/*INNER JOIN from products and suppliers tables by supplier_id*/
SELECT product_name, company_name, products.units_in_stock
FROM products
INNER JOIN suppliers ON products.supplier_id = suppliers.supplier_id
ORDER BY units_in_stock DESC ;

/*Select category_name from categories and units_in_stock from products*/
SELECT category_name, units_in_stock
FROM products
INNER JOIN categories c on products.category_id = c.category_id;

/*Select category_name with group by*/
SELECT category_name, units_in_stock
FROM products
INNER JOIN categories c on products.category_id = c.category_id
GROUP BY category_name, units_in_stock
ORDER BY units_in_stock DESC
LIMIT 5;

SELECT category_name, SUM(units_in_stock)
FROM products
INNER JOIN categories c on c.category_id = products.category_id
GROUP BY category_name
ORDER BY sum(units_in_stock) DESC
LIMIT 5;