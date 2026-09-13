# Lesson 3: Containerization, DevOps & Kubernetes Orchestration

## Overview
Modern cloud-native JEE applications require robust packaging, containerization, and orchestration paradigms to guarantee consistent deployment across staging and production environments.

---

## 1. Containerization with Docker
- **Dockerfiles**: Multi-stage builds to optimize image footprint (e.g., compile with Maven/JDK, run with JRE).
- **Docker Compose**: Orchestrating multi-container local environments (App Server + Database + Cache).

## 2. Container Orchestration with Kubernetes (K8s)
- **Pods**: Smallest deployable units containing one or more containers.
- **Deployments & ReplicaSets**: Declarative application state management and automated scaling.
- **Services**: Network abstraction enabling load balancing and service discovery (`ClusterIP`, `NodePort`, `LoadBalancer`).
- **ConfigMaps & Secrets**: Externalizing application configuration and sensitive credentials.
