<!-- Content: OOP Principles, Polymorphism, Collections Framework, Streams, Exception Handling, Multithreading & Synchronization. -->

# Lesson 1: Java Core Architecture, Collections & Concurrency

## Overview
This lesson covers fundamental Object-Oriented Programming (OOP) paradigms, Collection frameworks, Functional Programming, and Concurrency mechanisms necessary for building enterprise Java applications.

---

## 1. Object-Oriented Programming (OOP) Deep Dive
- **Interfaces vs. Abstract Classes**:
  - Abstract classes enable state inheritance and shared behavior across related objects.
  - Interfaces define strictly decoupled capability contracts (e.g., `Serializable`, `Cloneable`, or service interfaces).
- **Polymorphism & Dynamic Binding**:
  - Runtime resolution of overridden methods enables loose coupling in layered architectures.

## 2. Collections Framework & Stream API
- **Data Structures**:
  - `List`: Ordered collections (`ArrayList`, `LinkedList`).
  - `Set`: Unique element enforcement (`HashSet`, `TreeSet`).
  - `Map`: Key-value pair associations (`HashMap`, `ConcurrentHashMap`).
- **Stream API & Functional Interfaces**:
  - Declarative filtering, mapping, and reduction using Lambda expressions (`Predicate<T>`, `Function<T, R>`, `Consumer<T>`).

## 3. Exception Handling Best Practices
- **Checked vs. Unchecked Exceptions**:
  - Checked exceptions (`IOException`, `SQLException`) for recoverable application conditions.
  - Runtime exceptions (`IllegalArgumentException`, custom domain exceptions) for contract violations.
- **Global Exception Handling Pattern**:
  - Encapsulating domain-specific failure modes into standard response structures.

## 4. Concurrency & Thread Synchronization
- **Multithreading**:
  - Managing parallel tasks via `ExecutorService` and `CompletableFuture`.
- **Thread Safety**:
  - `synchronized` blocks, reentrant locks (`ReentrantLock`), and thread-safe collections (`ConcurrentHashMap`).
