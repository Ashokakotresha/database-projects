# Scaling Notes

This document explains how the system might evolve as data and traffic grow.

---

## 1. Current Shape of the System

The early version assumes:
- one application instance
- one relational database
- no cache
- local development setup

This is acceptable for learning and for an early system version.

---

## 2. Main Workloads

The system will mainly have two workload types:

### Read-heavy workloads
Examples:
- listing products
- viewing product details
- viewing user order history

### Write-heavy workloads
Examples:
- creating orders
- creating users
- updating product stock
- adding products

Early versions will be small, but it is useful to identify workload patterns from the beginning.

---

## 3. First Bottlenecks to Expect

As the system grows, the first problems will likely be:

### Large product listings
If the API returns all products at once, response size and query cost will grow.

### Increasing order history reads
Fetching user orders repeatedly may become slower as order volume grows.

### More joins and aggregation queries
Reports and analytics queries may become heavier as data volume increases.

### Database concentration
If all reads and writes go to one database instance, the database becomes the main bottleneck.

---

## 4. First Improvements

### Pagination
Add pagination to product and order listing APIs.

### Filtering and sorting
Allow the API to return narrower and more useful result sets.

### Indexing
Add indexes for frequently joined and filtered columns.

### Query tuning
Review slow queries and reduce unnecessary data access.

These are usually the first practical improvements before introducing bigger architecture changes.

---

## 5. Later Scaling Options

### Horizontal application scaling
Run multiple stateless application instances behind a load balancer.

### Database improvements
- optimize queries
- add indexes
- add read replicas for read-heavy workloads

### Caching
Cache frequently accessed product data when needed.

### Background processing
Move heavy or asynchronous tasks to background workers in later phases.

Examples:
- report generation
- notification sending
- audit logging

---

## 6. Data Consistency Considerations

As the system evolves, writes must still remain consistent.

Examples:
- an order should not exist without a valid user
- order items should not exist without a parent order
- product references in order items must remain valid
- total amount should match the sum of order item values

Scaling should not weaken correctness.

---

## 7. Design Principle

Do not introduce distributed-system complexity too early.

First:
- make the schema correct
- make the APIs clean
- make the queries reasonable

Then:
- improve performance
- improve scale
- evolve architecture only when there is a clear reason



