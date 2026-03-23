# Schema Design Summary
## Order Management System

This document answers the key schema design questions before building the database.

---

## 1. Which domain is the source of truth?

The system has four main domains:

- Users
- Products
- Orders
- Order Items

Each domain owns its own data and acts as the source of truth for that area.

### 1.1 Users domain
**Source of truth for:**
- user identity inside this system
- user name
- user email

**Notes:**
- the `users` table is the authoritative place for user records
- user email should be unique
- if the user name changes, the latest value belongs in `users`

---

### 1.2 Products domain
**Source of truth for:**
- product catalog inside this system
- current product name
- current category
- current price
- current stock

**Notes:**
- the `products` table is the authoritative place for the current product state
- current product price belongs here
- current inventory value belongs here

---

### 1.3 Orders domain
**Source of truth for:**
- order creation
- which user placed an order
- order status
- order-level total amount
- order creation time

**Notes:**
- the `orders` table is the authoritative place for order header information
- one order belongs to exactly one user

---

### 1.4 Order Items domain
**Source of truth for:**
- which products belong to a specific order
- quantity of each product in that order
- price of the product at purchase time

**Notes:**
- the `order_items` table is the authoritative place for order line items
- this table preserves transaction-level product details
- this is also where historical purchase price is stored

---

## 2. What is the consistency boundary?

A consistency boundary is the set of data that must stay correct together during a write.

For this project, the most important consistency boundary is:

### 2.1 Order + Order Items
These must be consistent together.

When an order is created:
- the order row must be created
- the related order item rows must be created
- the total amount must match the sum of all order items

This means:
- `orders` and `order_items` should be written in the same transaction

---

### 2.2 Product stock and order creation
This depends on how strict the system needs to be.

For the beginner version:
- stock can remain simple
- order creation and stock update can be kept basic

For a stricter version later:
- stock validation and stock reduction should be part of the same transaction as order creation

That means later the consistency boundary may include:
- `orders`
- `order_items`
- `products.stock`

---

### 2.3 User data and order data
These do **not** need to be updated in the same transaction in normal flows.

Reason:
- user profile changes are independent from order creation
- orders only need a valid `user_id` reference

So the main transaction boundary is usually:
- order header
- order items
- optionally stock update

---

## 3. What are the hot reads?

Hot reads are the queries expected to happen frequently and therefore should influence schema and indexing.

### 3.1 Product listing
Very common read:
- list all products
- filter by category
- sort by price or name

This is a hot read because users usually browse products often.

**Tables involved:**
- `products`

**Possible indexes:**
- `products.category`
- later possibly `products.price`

---

### 3.2 Product detail lookup
Very common read:
- fetch one product by ID

**Tables involved:**
- `products`

**Already optimized by:**
- primary key on `products.id`

---

### 3.3 Orders by user
Very common read:
- fetch all orders for one user
- sort by newest first

**Tables involved:**
- `orders`

**Important index:**
- `orders.user_id`

---

### 3.4 Order details
Very common read:
- fetch one order and all its items
- show product names, quantities, and purchase prices

**Tables involved:**
- `orders`
- `order_items`
- `products`

**Important indexes:**
- `order_items.order_id`
- `order_items.product_id`

---

### 3.5 Reporting queries
Less frequent than browsing, but still important:
- total orders per user
- total quantity sold per product
- revenue by category

**Tables involved:**
- `orders`
- `order_items`
- `products`
- sometimes `users`

These may become heavy as data grows, so they are good candidates for derived models later.

---

## 4. What history must be preserved?

This is one of the most important schema questions.

### 4.1 Purchase price history must be preserved
A product’s current price may change later.

If only `products.price` is stored, old orders would become inaccurate.

So the system must preserve:
- `order_items.price_at_purchase`

This ensures:
- past orders remain historically correct
- reports reflect actual transaction values at the time of purchase

---

### 4.2 Order composition history must be preserved
The system must preserve:
- which products were in each order
- how many units were ordered

This is why `order_items` exists.

Without it, the system cannot accurately reconstruct past orders.

---

### 4.3 Order status history
In the current simple version, only the latest order status is stored in `orders.status`.

That means:
- current status is preserved
- full status transition history is **not** preserved yet

Later, if needed, a separate table can be added, such as:
- `order_status_history`

For now:
- current status only is enough

---

### 4.4 Product catalog history
In the simple version, the system stores only the current product state.

That means:
- current name
- current category
- current price
- current stock

Historical changes to product metadata are **not** preserved yet.

This is acceptable for the early version because the critical historical value already lives in:
- `order_items.price_at_purchase`

---

### 4.5 User profile history
In the simple version, user profile change history is not preserved.

The system keeps only the latest user state in `users`.

That is acceptable for this project stage.

---

## 5. Where do we need derived models?

Derived models are read-optimized or summary views built from source-of-truth data.

They are useful when:
- reads are frequent
- joins become expensive
- reporting queries are repeated often

In the first version, base tables are enough.
Later, derived models may be useful in the following places.

---

### 5.1 Order summary view
A derived read model can combine:
- order
- user
- total amount
- item count
- created time
- status

This is useful for:
- order listing pages
- admin order dashboards
- simplified order history views

Possible derived model:
- SQL view
- API DTO projection
- materialized summary table later

---

### 5.2 Product sales summary
A derived model can store:
- product ID
- total quantity sold
- total revenue
- last ordered time

This is useful for:
- best-selling products
- reporting dashboards
- analytics

Because this data comes from `order_items`, it is derived rather than primary.

---

### 5.3 Category revenue summary
A derived model can store:
- category
- total revenue
- total quantity sold

This is useful for:
- business reports
- category-level analytics

This is derived from:
- `products`
- `order_items`

---

### 5.4 User spending summary
A derived model can store:
- user ID
- total orders
- total spending
- last order date

This is useful for:
- user order history summaries
- dashboards
- analytics

This is derived from:
- `users`
- `orders`

---

### 5.5 Search-friendly product listing model
If product listing becomes a hot path later, a derived read model may support:
- pre-joined display data
- filter-friendly fields
- sort-friendly fields

For the early project, the `products` table is enough.

---

## 6. Ordered schema decisions

Based on the questions above, the schema should follow this order of decisions.

### Decision 1
Use `users` as the source of truth for user records.

### Decision 2
Use `products` as the source of truth for current product catalog state.

### Decision 3
Use `orders` as the source of truth for order header data.

### Decision 4
Use `order_items` as the source of truth for ordered product lines and purchase-time pricing.

### Decision 5
Treat `orders` + `order_items` as the primary consistency boundary.

### Decision 6
Preserve history that affects transaction correctness, especially:
- ordered products
- ordered quantities
- purchase-time price

### Decision 7
Optimize schema first for hot reads such as:
- product listing
- orders by user
- order details

### Decision 8
Add indexes first on:
- `orders.user_id`
- `order_items.order_id`
- `order_items.product_id`
- `products.category`

### Decision 9
Do not introduce derived models too early.
Start with normalized source-of-truth tables first.

### Decision 10
Introduce derived models later for:
- order summaries
- sales summaries
- revenue reporting
- user spending summaries

---

## 7. Final schema guidance

For this project, the schema should be designed around the following principle:

> keep source-of-truth tables normalized, preserve transaction history where correctness matters, define clear transaction boundaries, and add derived models only when repeated reads or reporting justify them.

That leads to a clean beginner-friendly design that can also evolve in a professional way.
