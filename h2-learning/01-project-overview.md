# Step 1 — Project Overview  
## Order Management System Learning Project

---

## 1. Purpose of This Project

This project is a structured learning journey to understand **backend engineering** and **system design** by building a realistic **Order Management System** from scratch.

The purpose is not only to learn syntax or framework usage, but to learn how a software engineer thinks while building a system:

- how to model real-world data
- how to organize code professionally
- how to design APIs
- how to make engineering trade-offs
- how to move from a simple local system to a more production-like system
- how to document decisions clearly

This project is intentionally designed so that it can be shown to:

- recruiters
- interviewers
- hiring managers
- mentors
- other engineers

It is both a **learning project** and a **portfolio project**.

---

## 2. Project Goal

The main goal is to build an **Order Management System** in multiple stages, starting from database fundamentals and gradually evolving into a more complete backend system.

At the end of the full project, the system should demonstrate:

- relational database design
- SQL query skills
- backend REST API development
- layered application architecture
- authentication and authorization
- pagination and filtering
- indexing and performance thinking
- migration from H2 to PostgreSQL
- deployment and scaling awareness
- professional technical documentation

This project is not meant to be “big” for the sake of being big.  
It is meant to be **clear, correct, structured, and explainable**.

---

## 3. Problem Statement

Build an **Order Management System** where:

- users can browse products
- users can place orders
- users can view their orders
- the system stores product, user, and order information correctly
- the backend exposes APIs for managing this data
- the design can later scale toward a production-style architecture

This is a good learning problem because it contains important system design patterns:

- entity relationships
- many-to-many modeling
- transactional data
- query design
- user-facing read flows
- write operations with business meaning

---

## 4. Why I Chose This Project

I chose an Order Management System because it is a strong learning project for backend and system design.

It teaches:

### Database design
This project requires modeling related data across multiple tables such as:
- users
- products
- orders
- order items

### API design
This project naturally leads to backend endpoints such as:
- list products
- create orders
- view order history
- register and log in users

### Real engineering thinking
This project introduces concepts such as:
- normalization
- foreign keys
- bridge tables
- pagination
- indexing
- performance trade-offs
- database migration
- scaling notes

### Recruiter value
This is stronger than a random CRUD app because it includes:
- data modeling
- design documentation
- architectural reasoning
- progressive system evolution

---

## 5. Project Scope

This project is intentionally scoped to start small and grow gradually.

### Initial scope
The initial scope is to:
- model the database
- write SQL scripts
- understand table relationships
- run queries in H2

### Intermediate scope
Then the project will grow into:
- a Spring Boot backend
- CRUD APIs
- validation
- exception handling
- layered architecture

### Advanced scope
Then it will expand into:
- JWT-based authentication
- pagination and filtering
- indexing strategy
- PostgreSQL migration
- deployment and scaling notes

This means the project will evolve in phases rather than trying to build everything at once.

---

## 6. What This Project Will Eventually Include

The complete project will include the following major parts.

### Part 1 — H2 Database Basics
Learn and build:
- schema design
- sample data
- joins
- aggregation
- filtering
- sorting
- indexing basics

### Part 2 — Spring Boot Backend
Learn and build:
- entities
- repositories
- services
- controllers
- clean package structure
- API endpoints

### Part 3 — Authentication
Learn and build:
- user registration
- login
- password hashing
- JWT tokens
- protected endpoints

### Part 4 — Pagination and Filtering
Learn and build:
- paginated product APIs
- sorting support
- category filters
- search-ready endpoint design

### Part 5 — Query Optimization
Learn and build:
- indexes
- query performance notes
- trade-off explanations

### Part 6 — PostgreSQL Migration
Learn and build:
- configuration changes
- local PostgreSQL setup
- profile-based application config
- production-style DB setup thinking

### Part 7 — Deployment and Scaling
Learn and document:
- deployment architecture
- stateless services
- load balancing
- caching ideas
- future scaling considerations

---

## 7. Learning Objectives

This project is designed to teach both implementation and engineering reasoning.

### Technical learning objectives

#### Database
- understand tables, rows, and columns
- understand primary keys and foreign keys
- understand one-to-many and many-to-many relationships
- understand normalization
- write SQL queries using joins and aggregation

#### Backend development
- build REST APIs
- understand controller/service/repository layers
- handle input validation
- structure code professionally

#### Security
- understand authentication vs authorization
- hash passwords safely
- use JWT for stateless auth

#### Performance
- understand why full-table reads do not scale
- use pagination and filtering
- understand when indexes help
- understand read/write trade-offs

#### System design
- think in terms of requirements
- identify entities and flows
- document trade-offs
- understand how a simple system can evolve

---

## 8. Portfolio / Recruiter Objective

This project is also designed to communicate engineering maturity.

A recruiter should be able to look at this repository and understand:

- I learn in a structured way
- I can organize a technical project clearly
- I do more than just write code
- I can explain design decisions
- I understand backend and database fundamentals
- I can evolve a system from simple to more advanced stages

The repository should show:
- implementation
- explanation
- engineering thought process
- professional documentation

That is the reason this project includes both code and design notes.

---

## 9. High-Level Product Description

The system being built is an **Order Management System**.

At a high level:

- a user exists in the system
- products exist in the system
- a user can place an order
- an order can contain one or more products
- each ordered product is stored as an order item
- the backend provides ways to query and manage this information

This system is simple enough for learning, but realistic enough to teach important concepts.

---

## 10. High-Level Functional Requirements

These are the major functions the complete system should eventually support.

### User-related
- register a user
- log in a user
- fetch user details
- fetch a user’s order history

### Product-related
- create products
- list products
- filter products by category
- sort products by selected fields
- view product details

### Order-related
- create an order
- add products to an order
- calculate total order amount
- fetch an order by ID
- fetch all orders for a given user

### Admin-related future scope
- manage products
- update stock
- view simple reports

---

## 11. High-Level Non-Functional Requirements

These are qualities the system should aim for.

### Correctness
Data should be stored accurately.
Orders should correctly reference users and products.

### Maintainability
The project should be easy to understand and extend.
This is why the code and documentation will be organized clearly.

### Simplicity
The first versions should remain simple.
Complexity should be added only when needed.

### Performance awareness
Even in a learning project, APIs and queries should be designed with growth in mind.

### Security
Passwords must not be stored in plain text.
Protected endpoints should require authentication.

### Portability
The project should be able to move from H2 to PostgreSQL without a complete redesign.

---

## 12. High-Level Design Overview

This section gives the initial design view of the system.

### Core entities
The main business entities are:

- User
- Product
- Order
- OrderItem

### Relationship summary

#### User → Order
One user can place many orders.  
Each order belongs to one user.

This is a **one-to-many** relationship.

#### Order → OrderItem
One order can contain many order items.  
Each order item belongs to one order.

This is a **one-to-many** relationship.

#### Product → OrderItem
One product can appear in many order items.  
Each order item refers to one product.

This is also a **one-to-many** relationship.

#### Order ↔ Product
An order can contain many products, and a product can appear in many orders.

This is a **many-to-many** relationship.

In relational design, this is handled using an intermediate table:
`order_items`

---

## 13. Why `order_items` Is an Important Design Choice

This is one of the most important database design ideas in the project.

If an order can contain multiple products, and the same product can appear in many different orders, we should not store product data directly inside the `orders` table.

Instead, we use a separate table, commonly called a junction table or bridge table.

### `order_items` stores:
- the order ID
- the product ID
- quantity
- price at purchase time

### Why this design is correct
It allows:
- multiple products in one order
- the same product in many different orders
- accurate historical pricing
- cleaner normalization
- easier reporting queries

This is an example of professional relational modeling.

---

## 14. Initial Architecture Vision

The project will begin as a database-first learning project and then evolve into a layered backend application.

### Stage 1
Only SQL and H2 database scripts

### Stage 2
Spring Boot backend with layered structure:
- controller
- service
- repository
- entity

### Stage 3
JWT authentication and protected APIs

### Stage 4
Production-oriented improvements:
- pagination
- filtering
- indexing
- PostgreSQL
- deployment notes

This staged evolution is intentional.  
Real systems are usually not built all at once.  
They are designed and improved step by step.

---

## 15. Proposed Technical Stack

The project will use the following technology stack.

### Database learning phase
- H2 Database

### Backend phase
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Validation
- Maven

### Security phase
- Spring Security
- JWT

### Production-style DB phase
- PostgreSQL
- Docker

### Documentation
- Markdown
- GitHub

This stack is appropriate for learning backend engineering and system design in a practical way.

---

## 16. Proposed Project Phases

The project will be executed in the following order.

### Step 1 — Project Overview
Define:
- goals
- scope
- design direction
- project structure
- learning plan

### Step 2 — H2 Database Basics
Build:
- schema
- seed data
- query practice
- database README

### Step 3 — Backend with Spring Boot
Build:
- project structure
- basic entities
- repositories
- services
- controllers

### Step 4 — CRUD + Validation + Exception Handling
Improve:
- request validation
- API consistency
- error handling

### Step 5 — Authentication with JWT
Add:
- register
- login
- protected endpoints

### Step 6 — Pagination + Filtering + Sorting
Add:
- efficient listing APIs
- better endpoint design

### Step 7 — Indexing + Query Optimization
Document and apply:
- indexes
- performance decisions
- trade-offs

### Step 8 — Migrate H2 to PostgreSQL
Switch:
- local DB config
- runtime profile
- production-style setup

### Step 9 — Deployment and Scaling Notes
Document:
- system architecture
- load balancing
- cache ideas
- database bottlenecks
- future improvements

---

## 17. Repository Structure Plan

The repository should remain clean and progressive.

```text
database-projects/
└── h2-learning/
    ├── 01-project-overview.md
    ├── 02-h2-basics/
    │   ├── schema.sql
    │   ├── data.sql
    │   ├── queries.sql
    │   └── README.md
    │
    ├── 03-springboot-h2/
    │   ├── pom.xml
    │   ├── src/
    │   └── README.md
    │
    ├── 04-auth-jwt/
    │   └── README.md
    │
    ├── 05-pagination-filtering/
    │   └── README.md
    │
    ├── 06-postgresql-migration/
    │   └── README.md
    │
    └── docs/
        ├── design.md
        ├── roadmap.md
        ├── api-design.md
        └── scaling-notes.md


