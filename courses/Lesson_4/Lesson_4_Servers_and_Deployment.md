# Lesson 4: Application Servers, Enterprise Deployment & Thread Synchronization

## Overview
Understanding runtime environments, application server topologies, servlet containers, and server-level thread pool synchronization for scalable execution.

---

## 1. Application Servers vs. Servlet Containers
- **Servlet Containers (e.g., Apache Tomcat)**: Web-profile execution environment managing Servlets, JSP, and WebSocket specs.
- **Full Application Servers (e.g., Eclipse GlassFish, WildFly)**: Complete Jakarta EE enterprise suite including EJB, JMS, and JTA support.

## 2. Server-Level Thread Synchronization & Connection Pooling
- **Connection Pools (HikariCP)**: Reusing database connections to optimize I/O overhead.
- **Executor Thread Pools**: Managing incoming HTTP request threads dynamically under load.

## 3. Deployment Artifacts & Strategies
- **WAR (Web Application Archive) vs. Standalone Executable JAR**:
  - Deployment into external application servers versus self-contained embedded container models.
