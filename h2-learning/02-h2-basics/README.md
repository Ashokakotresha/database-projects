# Step 2 — H2 Database Basics

This step focuses on learning the foundations of relational databases using H2.

The goal is to understand:
- schema design
- sample data
- joins
- aggregation
- filtering
- sorting
- indexing basics

This step is intentionally database-first.
Before building backend APIs, it is important to understand how the data is structured and why the schema is designed in a certain way.

---

## 1. Learning Goals

At the end of this step, the system should demonstrate:

- a correct relational schema
- proper primary key and foreign key usage
- sample data for testing
- SQL queries that join multiple tables
- SQL queries using aggregation functions
- filtering and sorting queries
- basic indexes for common access patterns

---

## 2. Why Start with H2

H2 is useful for learning because it is:
- lightweight
- easy to run locally
- good for SQL practice
- convenient for early backend development

The goal in this step is not database scale.
The goal is to understand relational design clearly.

---

## 3. Data Model for This Step

This step uses four main tables:

- `users`
- `products`
- `orders`
- `order_items`

### `users`
Stores user information.

### `products`
Stores products available for purchase.

### `orders`
Stores order-level information such as which user placed the order and current order status.

### `order_items`
Stores each product inside an order.

---

## 4. Relationship Design

### Users → Orders
One user can have many orders.
Each order belongs to one user.

### Orders → Order Items
One order can contain many order items.
Each order item belongs to one order.

### Products → Order Items
One product can appear in many order items.
Each order item points to one product.

### Orders ↔ Products
Orders and products have a many-to-many relationship.
This is modeled through the `order_items` table.

---

## 5. Why `order_items` Matters

This table is important because:
- one order can contain multiple products
- the same product can appear in many different orders
- quantity must be stored per ordered product
- price at purchase time must be stored separately from current product price

This design preserves purchase history correctly.

---

## 6. Files in This Step

### `schema.sql`
Defines tables, constraints, and indexes.

### `data.sql`
Adds sample records for testing and query practice.

### `queries.sql`
Contains learning queries for joins, aggregation, filtering, and sorting.

---

## 7. Schema Design Notes

The schema follows these principles:

- each table has a primary key
- foreign keys preserve relationships
- email is unique for users
- required fields use `NOT NULL`
- order history is preserved through `price_at_purchase`
- indexes are added only on common join and filter columns

This keeps the schema normalized and clear.

---

## 8. Query Categories in This Step

The queries are grouped around these concepts:

### Basic reads
- list users
- list products
- list orders

### Joins
- show orders with user names
- show order items with product names
- show full order details

### Aggregation
- total spent by each user
- total quantity sold per product
- revenue by category
- count of orders per user

### Filtering
- products by category
- low-stock products
- orders by status
- orders for a specific user

### Sorting
- products by price
- users by spending
- orders by creation time

---

## 9. Indexing Basics

Indexes are added for columns commonly used in:
- joins
- filtering
- common lookup paths

Indexes included in this step:
- `orders.user_id`
- `order_items.order_id`
- `order_items.product_id`
- `products.category`

### Why these indexes
These columns are used often when:
- fetching a user’s orders
- fetching items in an order
- linking order items to products
- filtering products by category

### Trade-off
Indexes improve reads, but:
- consume extra storage
- can slightly slow inserts and updates

At this stage, only a few useful indexes are added.

---

## 10. How to Run This Step in H2

Start the H2 server:

```bash
java -cp h2-*.jar org.h2.tools.Server -tcp -tcpAllowOthers -web -webAllowOthers

Open the H2 shell:

```bash
java -cp h2-*.jar org.h2.tools.Shell


Use connection values like:
URL      jdbc:h2:~/h2-db-learning
Driver   org.h2.Driver
User     sa
Password


Run schema:

</> SQL

RUNSCRIPT FROM '/full/path/to/h2-learning/02-h2-basics/schema.sql';


Run sample data:

</> sql
RUNSCRIPT FROM '/full/path/to/h2-learning/02-h2-basics/data.sql';


Run practice queries:

</> sql
RUNSCRIPT FROM '/full/path/to/h2-learning/02-h2-basics/queries.sql';


Summary:

Thisintroduces:

schema design
sample data
SQL joins
aggregation queries
filtering
sorting
indexing basics

------------------------------------------

### Use this order:

A. Run the schema

This helps you understand table structure first.

B. Load sample data

This gives you realistic rows for testing.

C. Run the basic queries

Start with:

all users
all products
all orders
all order items
D. Run filtering and sorting queries

This helps you learn:

WHERE
ORDER BY
E. Run join queries

This is where relational design becomes meaningful.

F. Run aggregation queries

This helps you learn:

COUNT
SUM
AVG
GROUP BY
HAVING
G. Review indexes

Understand why indexes were added and which queries benefit from them.

### What each concept means
Schema design

The schema defines:

what tables exist
what columns they contain
how tables are related
Sample data

Sample data helps test queries and understand how records connect.

Joins

Joins combine rows from related tables.

Examples in this step:

orders joined with users
order_items joined with products
full order details across multiple tables
Aggregation

Aggregation summarizes data.

Examples:

total orders per user
total quantity sold per product
revenue by category
Filtering

Filtering uses WHERE to return only matching rows.

Sorting

Sorting uses ORDER BY to control result order.

Indexing basics

Indexes help speed up common read queries, especially joins and filters.


