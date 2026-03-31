# 03-springboot-h2

## Goal

Build a small backend service on top of your H2 learning project so you move from:

- writing SQL manually
- understanding schema design

to:

- building REST APIs
- structuring backend code
- mapping database tables to application objects
- implementing business logic in a professional way

This step is the bridge from **database learning** to **backend system design**.

---

## What you will build

A small **Order Management Service** with these core APIs:

- `GET /products`
- `GET /products/{id}`
- `POST /products`
- `GET /orders/{id}`
- `POST /orders`
- `GET /users/{id}/orders`

Domain:

- `users`
- `products`
- `orders`
- `order_items`

---

## Why this step matters

At this point, SQL alone is not enough.

As a backend/system design engineer, you must learn:

- how to expose data through APIs
- how to separate controller, service, and repository layers
- how to handle parent-child relationships like `orders` and `order_items`
- how to keep business rules outside controllers
- how to return stable API responses using DTOs

This project teaches those fundamentals.

---

## Learning outcomes

By the end of this step, you should understand:

- what Spring Boot does in a backend project
- how to structure a layered application
- how tables map to entities
- why DTOs are useful
- how to implement basic CRUD APIs
- how to implement a transactional flow like order creation
- how to connect Spring Boot to H2

---

## Project structure

Recommended structure:

```text
03-springboot-h2/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/example/h2learning/
    │   │   ├── H2LearningApplication.java
    │   │   ├── controller/
    │   │   │   ├── ProductController.java
    │   │   │   ├── OrderController.java
    │   │   │   └── UserController.java
    │   │   ├── service/
    │   │   │   ├── ProductService.java
    │   │   │   ├── OrderService.java
    │   │   │   └── UserService.java
    │   │   ├── repository/
    │   │   │   ├── ProductRepository.java
    │   │   │   ├── UserRepository.java
    │   │   │   ├── OrderRepository.java
    │   │   │   └── OrderItemRepository.java
    │   │   ├── entity/
    │   │   │   ├── Product.java
    │   │   │   ├── User.java
    │   │   │   ├── Order.java
    │   │   │   └── OrderItem.java
    │   │   ├── dto/
    │   │   │   ├── CreateProductRequest.java
    │   │   │   ├── CreateOrderRequest.java
    │   │   │   ├── OrderItemRequest.java
    │   │   │   ├── ProductResponse.java
    │   │   │   └── OrderResponse.java
    │   │   └── exception/
    │   │       └── ResourceNotFoundException.java
    │   └── resources/
    │       ├── application.yml
    │       ├── schema.sql
    │       └── data.sql
    └── test/



