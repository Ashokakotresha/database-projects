# API Design

This document describes the planned API structure for later backend phases.

---

## 1. API Design Goals

The APIs should be:
- clear
- predictable
- resource-oriented
- easy to extend later

---

## 2. Product APIs

### GET /api/products

Purpose:
- list products

Query parameters:
- page
- size
- category
- sort

Example:

    GET /api/products?page=0&size=10&category=Electronics

---

### GET /api/products/{id}

Purpose:
- fetch one product by ID

---

### POST /api/products

Purpose:
- create a product

Example request body:

    {
      "name": "Laptop",
      "category": "Electronics",
      "price": 1200.00,
      "stock": 10
    }

---

### PUT /api/products/{id}

Purpose:
- update a product

---

### DELETE /api/products/{id}

Purpose:
- remove a product if deletion is allowed in later phases

---

## 3. User APIs

### POST /api/auth/register

Purpose:
- register a new user

Example request body:

    {
      "name": "Alice Johnson",
      "email": "alice@example.com",
      "password": "StrongPassword123"
    }

---

### POST /api/auth/login

Purpose:
- log in an existing user

Example request body:

    {
      "email": "alice@example.com",
      "password": "StrongPassword123"
    }

---

### GET /api/users/{id}

Purpose:
- fetch user details

---

## 4. Order APIs

### POST /api/orders

Purpose:
- create an order for a user

Example request body:

    {
      "userId": 1,
      "items": [
        {
          "productId": 1,
          "quantity": 1
        },
        {
          "productId": 2,
          "quantity": 2
        }
      ]
    }

Expected behavior:
- validate the user exists
- validate each product exists
- validate quantity is valid
- calculate total amount
- create order and order items

---

### GET /api/orders/{id}

Purpose:
- fetch one order

---

### GET /api/orders/user/{userId}

Purpose:
- fetch orders for one user

Query parameters planned for later:
- page
- size
- status
- sort

Example:

    GET /api/orders/user/1?page=0&size=10&status=CREATED

---

## 5. Response Design Notes

In later phases, responses should use DTOs instead of exposing database entities directly.

Reasons:
- better API stability
- better control over returned fields
- separation between persistence layer and API layer

Example product response:

    {
      "id": 1,
      "name": "Laptop",
      "category": "Electronics",
      "price": 1200.00,
      "stock": 10
    }

---

## 6. Error Handling

The API should return consistent error responses.

Example:

    {
      "timestamp": "2026-03-22T10:00:00Z",
      "status": 400,
      "error": "Bad Request",
      "message": "Invalid input",
      "path": "/api/products"
    }

Common cases:
- invalid request body
- resource not found
- duplicate email
- unauthorized access
- invalid login credentials

---

## 7. API Design Principles for Later Steps

- use nouns for resources
- keep URLs predictable
- use HTTP methods consistently
- validate request data
- return meaningful status codes
- avoid exposing internal database structure directly


