# H2 Database – Summary

## 📌 Overview

H2 is a lightweight, open-source relational database written in Java. It is designed for fast performance and can run either as an **in-memory database** or as a **persistent database on disk**. H2 is widely used for **development, testing, and small-scale applications**.

---

## 🚀 Key Features

* **Lightweight & Fast** – Starts in seconds, minimal resource usage
* **In-Memory Mode** – Data stored in RAM for high performance
* **Embedded Database** – Runs inside your Java application
* **SQL Support** – Standard relational database with full SQL support
* **Web Console** – Built-in browser-based UI for querying and debugging
* **Zero Configuration** – No complex setup required
* **JDBC Compatible** – Easily integrates with Java applications

---

## 🧱 Architecture

H2 can operate in multiple modes:

* **Embedded Mode**

  * Runs inside the application process
  * No separate server needed

* **Server Mode**

  * Runs as a standalone database server
  * Multiple clients can connect

* **In-Memory Mode**

  * Data stored in RAM
  * Lost when application stops

---

## 💡 Use Cases

* Backend development (Spring Boot, Java apps)
* Unit and integration testing
* Prototyping applications
* Learning SQL and database concepts
* Lightweight standalone applications

---

## ⚙️ Basic Example

### JDBC URL

```
jdbc:h2:mem:testdb
```

### Sample SQL

```sql
CREATE TABLE users (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT
);

INSERT INTO users VALUES (1, 'Alice', 25);

SELECT * FROM users;
```

---

## 🔌 Integration (Spring Boot Example)

Add dependency:

```xml
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
</dependency>
```

Enable console:

```properties
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
```

Access console:

```
http://localhost:8080/h2-console
```

---

## ⚖️ Pros & Cons

### ✅ Pros

* Very easy to set up and use
* Fast execution (especially in-memory)
* Ideal for testing and development
* No external dependencies

### ❌ Cons

* Not suitable for large-scale production systems
* Limited scalability (single-node)
* Data loss in in-memory mode

---

## 🆚 Comparison

| Feature     | H2 Database     | PostgreSQL/MySQL |
| ----------- | --------------- | ---------------- |
| Setup       | Very Easy       | Moderate         |
| Scale       | Small           | Large            |
| Performance | Very Fast (RAM) | Balanced         |
| Use Case    | Dev/Test        | Production       |

---

## 🧠 Key Takeaways

* H2 is best for **learning, testing, and rapid development**
* It is **not designed for distributed or large-scale systems**
* Commonly used with **Spring Boot** for quick backend setups

---

## 📚 References

* Official Website: https://h2database.com
* GitHub: https://github.com/h2database/h2database

=========================================================================


1. Download the latest H2 Database files
  - Either download JAR file and run it.
  wget https://github.com/h2database/h2database/releases/download/version-2.4.240/h2-2.4.240.jar
  java -jar h2-2.4.240.jar

  OR 

  - Download full build, unzip and run the JAR file.
  wget https://github.com/h2database/h2database/releases/download/version-2.4.240/h2-2.4.240.zip
  unzip h2-2.4.240.zip
  cd h2/bin

2. Run the JAR application or Open the Browser and run below URL:
 - java -jar h2*.jar

 - http://localhost:8082

3. Connect to the running Database:
   Connection settings:

   JDBC URL: jdbc:h2:mem:testdb

   User: sa

   Password: (leave empty)

4. Try these SQL commands

CREATE TABLE users (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT
);

INSERT INTO users VALUES (1, 'Ashoka', 25);
INSERT INTO users VALUES (2, 'John', 30);

SELECT * FROM users;

UPDATE users SET age = 26 WHERE id = 1;

DELETE FROM users WHERE id = 2;

----------------------

5. This gives you:

SQL basics

CRUD operations

Instant feedback
