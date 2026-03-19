# Database Learning Projects

This repository contains hands-on projects to learn different databases.

## 🎯 Goal
Learn databases from basics → advanced → system design.


# Database System Design Learning Roadmap

This repository is a structured, hands-on journey to master databases for backend engineering and system design interviews.

---

## 📁 Repository Structure

database-projects/
│
├── h2-learning/            # SQL fundamentals (in-memory DB)
├── postgres-learning/      # Production-grade relational DB
├── redis-learning/         # Caching & performance
├── cassandra-learning/     # Distributed NoSQL
├── system-design-notes/    # Concepts, tradeoffs, patterns
└── README.md

---

## 🚀 Phase 1: SQL Fundamentals (H2)

### 📂 h2-learning/

Goal: Learn core database concepts

Topics:

* CRUD operations
* Joins (INNER, LEFT, RIGHT)
* Indexes (basic)
* Transactions (intro)
* Data modeling basics

Project:

* Simple User API (Spring Boot + H2)

Outcome:

* Strong SQL foundation
* Understand relational data modeling

---

## 🧱 Phase 2: Production Database (PostgreSQL)

### 📂 postgres-learning/

Goal: Learn how real systems use databases

Topics:

* Advanced indexing (B-Tree, Hash)
* Query optimization (EXPLAIN ANALYZE)
* Transactions (ACID)
* Isolation levels
* Schema design (normalization vs denormalization)
* Migrations (Flyway/Liquibase)

Project:

* Production-ready REST API
* Pagination, filtering, sorting
* Index optimization

Outcome:

* Ability to design scalable relational systems
* Understand performance tuning

---

## ⚡ Phase 3: Caching Layer (Redis)

### 📂 redis-learning/

Goal: Improve performance and scalability

Topics:

* Cache-aside pattern
* Write-through / write-back
* TTL & eviction policies
* Hot key problem
* Distributed cache basics

Project:

* Add Redis cache to Postgres API

Outcome:

* Reduce DB load
* Improve latency

---

## 🌍 Phase 4: Distributed NoSQL (Cassandra / HBase)

### 📂 cassandra-learning/

Goal: Learn distributed data systems

Topics:

* Partitioning & consistent hashing
* Replication
* CAP theorem
* Eventual consistency
* Data modeling for NoSQL

Project:

* Time-series or event logging system

Outcome:

* Understand large-scale distributed storage

---

## 🧠 Phase 5: System Design Integration

### 📂 system-design-notes/

Topics:

* SQL vs NoSQL tradeoffs
* When to use caching
* Scaling databases (vertical vs horizontal)
* Sharding strategies
* Read replicas
* Data consistency models

---

## 🔄 Learning Progression

H2 → PostgreSQL → Redis → Cassandra

Each phase builds on the previous one:

* Start simple
* Add realism
* Add scale
* Add distribution

---

## 🎯 End Goal

Be able to confidently answer:

* How to design a scalable database system
* When to use SQL vs NoSQL
* How to optimize performance
* How to handle large-scale data

---

## 💼 Interview Value

This roadmap prepares you for:

* Backend engineering roles
* System design interviews
* Real-world distributed systems


