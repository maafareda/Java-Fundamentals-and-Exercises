# Lesson 2: Enterprise Data Persistence, Web Tier & Spring Ecosystem

## Overview
This lesson details data access abstraction from low-level JDBC to modern Object-Relational Mapping (ORM) with JPA/Hibernate, web components (Servlets/JSP), RESTful web services, and the Spring Ecosystem.

---

## 1. Low-Level Database Connectivity (JDBC)
- **DriverManager & DataSource**: Establishing connections to relational databases.
- **Statement vs. PreparedStatement**:
  - PreparedStatement prevents SQL Injection attacks via pre-compiled SQL queries and parameterized inputs.

## 2. Servlets, JSP & Session Management
- **Servlet Lifecycle**: `init()`, `service()`, `destroy()`.
- **State Management**:
  - HttpSessions, Cookies, and URL rewriting for user session tracking.
- **MVC Architecture**:
  - Model (POJOs/Entities), View (JSP/Thymeleaf), Controller (Servlets/Spring MVC Controllers).

## 3. ORM, JPA & Hibernate
- **Object-Relational Mapping (ORM)**: Mapping relational tables to Java objects.
- **Jakarta Persistence API (JPA)**: Standard specification defining object persistence.
- **Hibernate**: De facto implementation providing caching, lazy loading, and HQL/JPQL execution.

## 4. Web Services & Spring Ecosystem
- **REST & JAX-RS**: Stateless API endpoints communicating over HTTP/JSON.
- **Spring Framework**:
  - **Spring Core (IoC/DI)**: Dependency Injection for modular component management.
  - **Spring Data JPA**: Repository pattern abstraction reducing boilerplate CRUD code.
  - **Spring Security**: Authentication (JWT, Session) and authorization (RBAC).
