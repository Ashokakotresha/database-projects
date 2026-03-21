
## We’ll build one system across all folders, increasing complexity step-by-step.

Core Entities

* Users
* Products
* Orders
* OrderItems

## Final Repo Structure (Clean & Progressive)
```
database-projects/
  Project_OrderManagement.md   <-- MASTER GUIDE (very important)
  01-h2-basics/
  02-h2-crud-app/
  03-java-jdbc-app/
  04-postgres-migration/
  05-springboot-api/
```
---------------------------------------------------------------------------------


# Database Projects (H2 → PostgreSQL → Spring Boot)

This repo demonstrates my database and backend learning journey:

1. H2 basics (SQL, schema, queries)
2. CRUD app using H2
3. Java JDBC integration
4. Migration to PostgreSQL
5. Spring Boot API

Each folder builds on the previous one.

### 01 — H2 BASICS (FOUNDATION)
Goal:  Learn SQL + schema design

#### Structure:
```
01-h2-basics/
  schema.sql
  data.sql
  queries.sql
  README.md
```

#### schema.sql
```
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    price DECIMAL(10,2)
);

CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    product_id INT,
    quantity INT,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
```

#### queries.sql (IMPORTANT)
Include:
* JOIN
* GROUP BY
* Aggregation

```-- total orders per user
SELECT u.name, COUNT(o.id)
FROM users u
LEFT JOIN orders o ON u.id = o.user_id
GROUP BY u.name;
```

### 02 — H2 CRUD APP
Goal:  Move from SQL → application logic mindset

Structure
```
02-h2-crud-app/
  schema.sql
  data.sql
  queries/
    insert.sql
    update.sql
    delete.sql
    select.sql
  README.md
```

Add Real Use Cases

Examples:
* Create user
* Create order
* Add product to order

```
INSERT INTO orders(user_id) VALUES (1);
INSERT INTO order_items(order_id, product_id, quantity)
VALUES (1, 2, 3);
```

Add Constraints (IMPORTANT)
Upgrade schema:
```
  quantity INT CHECK (quantity > 0)
```

### 03 — JAVA JDBC APP
Goal :  Connect Java → DB (this is HUGE for interviews)

Structure
```
03-java-jdbc-app/
  schema.sql
  src/
    model/
      User.java
      Product.java
    dao/
      UserDAO.java
      OrderDAO.java
    service/
      OrderService.java
    Main.java
  README.md
```

JDBC Connection
```
</> Java

  Connection conn = DriverManager.getConnection(
    "jdbc:h2:tcp://localhost/~/test", "sa", ""
  );
```

DAO Example
```
public void createUser(String name, String email) {
    String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setString(1, name);
    stmt.setString(2, email);
    stmt.executeUpdate();
}
```

### 04 — POSTGRES MIGRATION (CRITICAL STEP)
Goal: Show real-world DB understanding

Structure
```
04-postgres-migration/
  schema_postgres.sql
  migration_notes.md
  README.md
```

Key Changes
| H2             | PostgreSQL |
| -------------- | ---------- |
| AUTO_INCREMENT | SERIAL     |
| VARCHAR        | same       |
| TIMESTAMP      | same       |


Example:
```
id SERIAL PRIMARY KEY
```

### 05 — SPRING BOOT API
Goal: Full backend system

```
05-springboot-api/
  src/main/java/
    controller/
    service/
    repository/
    entity/
  application.properties
  README.md
```

Features to Implement
APIs:

* POST /users
* GET /products
* POST /orders
* GET /orders/{id}

Add:

* JPA/Hibernate
* DTOs
* Validation

### FINAL TOUCH (VERY IMPORTANT)
Add THIS to your repo:
📊 ER Diagram (even simple)

Explain relationships:

* User → Orders (1:N)
* Order → Items (1:N)
