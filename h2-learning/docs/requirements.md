# Requirements

This document defines what the system should do and what qualities it should maintain.

---

## 1. System Summary

The system is an Order Management System.

It manages:
- users
- products
- orders
- order items

The system begins as a database learning project and later evolves into a backend application.

---

## 2. Functional Requirements

Functional requirements describe what the system must do.

### User Management
- The system should allow a user record to be created.
- The system should store basic user information such as name and email.
- The system should prevent duplicate email registration.

### Product Management
- The system should store products with name, category, price, and stock.
- The system should allow products to be listed.
- The system should allow products to be filtered by category in later phases.
- The system should allow product details to be fetched.

### Order Management
- The system should allow an order to be created for a user.
- The system should allow one order to contain multiple products.
- The system should store quantity for each ordered product.
- The system should store price at purchase time for each order item.
- The system should calculate or store total order amount.
- The system should allow orders to be fetched by order ID.
- The system should allow orders to be fetched for a given user.

### Authentication and Security
- The system should later support user registration and login.
- The system should later hash passwords before storing them.
- The system should later protect selected APIs using authentication.

### Reporting and Querying
- The system should support SQL queries for:
  - joins
  - aggregation
  - filtering
  - sorting
- The system should support listing total orders per user.
- The system should support listing total quantity sold per product.
- The system should support revenue analysis by category.

---

## 3. Non-Functional Requirements

Non-functional requirements describe how the system should behave.

### Correctness
- The system must maintain valid relationships between users, orders, products, and order items.
- Foreign key relationships should preserve referential integrity.

### Maintainability
- The project should be organized in a clean, step-by-step structure.
- Code and documentation should be easy to understand and extend.

### Simplicity
- The early phases should favor clarity over unnecessary complexity.
- The design should remain small enough to understand deeply.

### Performance Awareness
- The design should avoid patterns that do not scale well.
- Read APIs should later support pagination instead of returning everything at once.
- Frequently queried columns should be considered for indexing.

### Security
- Plain text passwords must never be stored in later phases.
- Protected endpoints should require authentication in later phases.

### Portability
- The design should allow the database to move from H2 to PostgreSQL with minimal redesign.

### Consistency
- Order and order item relationships should remain consistent.
- The system should preserve purchase history accurately.

---

## 4. Assumptions

These assumptions keep the first version simple.

- One order belongs to exactly one user.
- One order can contain one or more order items.
- Each order item refers to exactly one product.
- Product price may change over time, so price at purchase time must be stored in order_items.
- Inventory control will remain basic in the first version.
- The first versions will use a single database and a single application instance.

---

## 5. Constraints

- The first database will be H2.
- The first backend will be built with Spring Boot.
- The early phases should not introduce unnecessary distributed-system complexity.
- The design should stay understandable for a beginner.

---

## 6. Out of Scope for Early Phases

The following are intentionally excluded at the beginning:
- microservices
- message queues
- distributed transactions
- event-driven architecture
- complex admin workflows
- advanced inventory reservation logic
- payment gateway integration


