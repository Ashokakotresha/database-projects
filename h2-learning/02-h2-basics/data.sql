INSERT INTO users (name, email) VALUES
('Alice Johnson', 'alice@example.com'),
('Bob Smith', 'bob@example.com'),
('Charlie Brown', 'charlie@example.com'),
('Diana Evans', 'diana@example.com');

INSERT INTO products (name, category, price, stock) VALUES
('Laptop', 'Electronics', 1200.00, 10),
('Mouse', 'Electronics', 25.00, 100),
('Keyboard', 'Electronics', 75.00, 50),
('Monitor', 'Electronics', 250.00, 20),
('Notebook', 'Stationery', 5.00, 200),
('Pen Pack', 'Stationery', 10.00, 150),
('Desk Lamp', 'Home', 45.00, 30);

INSERT INTO orders (user_id, status, total_amount) VALUES
(1, 'CREATED', 1250.00),
(2, 'CREATED', 85.00),
(1, 'SHIPPED', 15.00),
(3, 'DELIVERED', 295.00),
(4, 'CREATED', 55.00);

INSERT INTO order_items (order_id, product_id, quantity, price_at_purchase) VALUES
(1, 1, 1, 1200.00),
(1, 2, 2, 25.00),
(2, 3, 1, 75.00),
(2, 5, 2, 5.00),
(3, 6, 1, 10.00),
(3, 5, 1, 5.00),
(4, 4, 1, 250.00),
(4, 2, 1, 25.00),
(4, 6, 2, 10.00),
(5, 7, 1, 45.00),
(5, 5, 2, 5.00);


