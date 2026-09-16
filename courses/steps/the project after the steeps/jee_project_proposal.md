# Comprehensive Project Proposal: Enterprise Inventory & Logistics Management System (EILMS)

**Course:** Advanced Java Enterprise Engineering  
**Instructor:** Prof. Architectural & Software Systems  
**Academic Year:** 2026-2027  

---

## 1. Executive Summary & Project Description
The **Enterprise Inventory & Logistics Management System (EILMS)** is a cloud-native Java Enterprise application designed to process, manage, and audit real-time supply chain operations across distributed warehouses.

Students will build a production-ready application leveraging:
- Core Java concurrency for asynchronous stock processing.
- Spring Boot, JPA/Hibernate, and PostgreSQL for transaction-safe persistence and API exposure.
- Docker and Kubernetes for microservice packaging and orchestration.
- Tomcat and GlassFish target deployment testing.

---

## 2. Architecture & Tech Stack Mapping

| Layer | Covered Concepts from Curriculum |
|---|---|
| **Core Layer** | Interfaces, Abstract Classes, Polymorphism, Custom Exceptions, Lambda Expressions, Stream API, Multithreading |
| **Persistence & Web** | JDBC, Servlets, MVC, JPA/Hibernate, REST Services, Spring Core, Spring Data JPA, Spring Security |
| **DevOps & Containerization** | Docker, Multi-Stage Builds, Kubernetes Deployments, Services, ConfigMaps |
| **Deployment & Servers** | Apache Tomcat, GlassFish, Thread Pool Synchronization |

---

## 3. Step-by-Step Implementation Guide

### Step 1: Core Domain Modeling & Concurrency Engine (Part 1 - Java Core)
- **Objective:** Establish the foundational OOP model and an in-memory asynchronous order processing engine.
- **Instructions:**
  1. Define domain interfaces (`InventoryItem`, `OrderProcessor`) and abstract base classes (`AbstractWarehouseItem`).
  2. Implement functional streams to process batch stock items, filter low-stock alerts, and format audit logs.
  3. Construct a multithreaded order fulfillment simulator using `ExecutorService` and synchronized thread-safe queues.

### Step 2: Database Persistence & ORM Mapping (Part 2 - Persistence)
- **Objective:** Transition from in-memory processing to enterprise relational data persistence.
- **Instructions:**
  1. Write native JDBC scripts with `PreparedStatement` to verify direct connection pool metrics.
  2. Configure JPA/Hibernate entities with relational mappings (`@OneToMany`, `@ManyToOne`) for Warehouses, Items, and Orders.
  3. Implement Spring Data JPA Repositories with custom JPQL queries.

### Step 3: Web Tier, REST APIs & Security (Part 2 - Web Java)
- **Objective:** Build an MVC administrative panel and secure RESTful endpoints.
- **Instructions:**
  1. Implement a legacy Servlet/JSP session-based authentication module to understand web container basics.
  2. Transition to Spring MVC and Spring REST controllers exposing OpenAPI-compliant endpoints.
  3. Secure endpoints using Spring Security with Role-Based Access Control (RBAC) and JWT token validation.

### Step 4: Containerization & Local Orchestration (Part 3 - DevOps)
- **Objective:** Containerize the application and database into production-ready images.
- **Instructions:**
  1. Write a multi-stage `Dockerfile` optimizing JVM memory flags and layer caching.
  2. Create a `docker-compose.yml` orchestrating the Spring application, PostgreSQL database, and adminer instance.

### Step 5: Kubernetes Deployment & Enterprise Server Profiling (Part 3 & 4)
- **Objective:** Deploy to Kubernetes and test compatibility on Tomcat vs GlassFish.
- **Instructions:**
  1. Generate Kubernetes deployment manifests (`deployment.yaml`, `service.yaml`, `configmap.yaml`).
  2. Configure horizontal pod autoscaling based on simulated transaction concurrency.
  3. Package the application into a WAR file, deploy onto standalone GlassFish and Apache Tomcat instances, and evaluate thread synchronization performance.

---

## 4. Evaluation Rubric
- **Code Quality & OOP Structure (20%):** Clean abstraction, effective polymorphism, domain modeling.
- **Data Persistence & Integrity (25%):** Correct JPA annotations, transactional boundaries, thread-safe data access.
- **API & Security Implementation (20%):** RESTful principles, Spring Security setup, robust error handling.
- **DevOps & Containerization (20%):** Docker optimization, valid K8s manifests, zero-downtime deployment.
- **Documentation & Defense (15%):** Clear architectural rationale and live technical defense.
