-- Create customers table
CREATE TABLE customers
(
    customer_id SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL
);

-- Create orders table
CREATE TABLE orders
(
    order_id       SERIAL PRIMARY KEY,
    customer_id    INTEGER,
    amount         DECIMAL(10, 2),
    status         VARCHAR(50),
    order_datetime TIMESTAMP NOT NULL, -- New column for order date and time
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
);

-- Insert sample data into customers table
INSERT INTO customers (name)
VALUES ('John Doe'),
       ('Jane Smith'),
       ('Alice Johnson'),
       ('Bob Lee');

-- Insert sample data into orders table with order_datetime
INSERT INTO orders (customer_id, amount, status, order_datetime)
VALUES (1, 1500.50, 'completed', '2024-01-01 10:00:00'),
       (2, 500.00, 'pending', '2024-01-02 14:30:00'),
       (1, 750.00, 'completed', '2024-01-03 09:15:00'),
       (3, 1200.00, 'completed', '2024-01-04 11:45:00'),
       (4, 3000.00, 'completed', '2024-01-05 16:00:00'),
       (2, 250.00, 'completed', '2024-01-06 18:20:00');
