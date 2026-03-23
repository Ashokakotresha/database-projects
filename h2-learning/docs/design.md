# Design Document

---

## 1. Problem Overview

The system manages products, users, and orders.

A user can browse products and place orders.
An order can contain multiple products.
Each ordered product must be stored with quantity and price at the time of purchase.

This project is designed to teach database design first and then evolve into a backend system.

---

## 2. Design Goals

The initial design should:
- be easy to understand
- model the business domain correctly
- preserve relational integrity
- support useful queries
- remain clean enough to extend later

---

## 3. Core Entities

### Users
Represents a person using the system.

Suggested attributes:
- id
- name
- email
- created_at

### Products
Represents an item available for purchase.

Suggested attributes:
- id
- name
- category
- price
- stock
- created_at

### Orders
Represents a purchase transaction created by a user.

Suggested attributes:
- id
- user_id
- status
- total_amount
- created_at

### Order Items
Represents one product inside one order.

Suggested attributes:
- id
- order_id
- product_id
- quantity
- price_at_purchase

---

## 4. Relationship Design

### User to Orders
One user can create many orders.
Each order belongs to one user.

Relationship:
- one-to-many

### Order to Order Items
One order can contain many order items.
Each order item belongs to one order.

Relationship:
- one-to-many

### Product to Order Items
One product can appear in many order items.
Each order item points to one product.

Relationship:
- one-to-many

### Orders to Products
An order can contain many products.
A product can appear in many orders.

This is a many-to-many relationship.

It is resolved through the `order_items` table.

---

## 5. Why `order_items` Exists

This is a key design decision.

If products were stored directly in the `orders` table, the schema would become incorrect for orders containing multiple products.

The `order_items` table solves this by storing:
- order reference
- product reference
- quantity
- price at purchase time

This design gives:
- correct many-to-many modeling
- better normalization
- accurate historical pricing
- flexible reporting queries

---

## 6. Normalization Notes

The early schema should be normalized.

Why:
- reduce duplication
- improve consistency
- keep entity responsibilities clear

Examples:
- user data belongs in `users`
- product data belongs in `products`
- transaction header data belongs in `orders`
- line-item data belongs in `order_items`

At this stage, denormalization is not needed.

---

## 7. Data Integrity Rules

The design should enforce:
- unique email per user
- valid foreign key from orders to users
- valid foreign key from order_items to orders
- valid foreign key from order_items to products
- non-null required business fields where appropriate

---

## 8. Initial Query Patterns

The schema should support:
- list all products
- fetch orders for a user
- fetch all items in an order
- calculate total spent by user
- calculate quantity sold per product
- calculate revenue by category

These query patterns guide schema and index choices.

---

## 9. Initial Indexing Strategy

Indexes are added mainly for frequently joined or filtered columns.

Suggested indexes:
- orders.user_id
- order_items.order_id
- order_items.product_id
- products.category

Why:
- improve join performance
- improve filter performance
- support common access patterns

Trade-off:
- indexes speed up reads
- indexes add storage cost
- indexes can slightly slow inserts and updates

---

## 10. Future Design Evolution

Later phases may introduce:
- authentication fields for users
- order status transitions
- pagination support in APIs
- PostgreSQL-specific configuration
- scaling considerations such as caching and replicas

The first version should remain simple and correct before adding these improvements.


