# Chirp Backend

Learning project for practicing modern backend development with Kotlin and Spring Boot.

## Overview

Chirp is a backend application that implements common features of a modern messaging platform.

The project was created as a learning project to deepen practical knowledge of backend architecture, authentication, real-time communication, messaging, caching, and external service integrations.

## Tech Stack

* Kotlin
* Spring Boot
* Spring Security
* Spring Data JPA
* PostgreSQL
* JWT Authentication
* Redis
* RabbitMQ
* WebSockets
* Firebase Cloud Messaging
* Supabase Storage
* Mailgun
* Gradle
* Docker
* GitHub Actions

## Features

* User registration and authentication
* Access and refresh token authentication
* Email verification
* Password reset
* User profile management
* Chat creation and management
* Real-time messaging with WebSockets
* Message persistence
* Redis caching
* RabbitMQ-based event processing
* Push notifications
* File and media storage
* Rate limiting
* Centralized exception handling
* Request validation
* Production and development configuration
* CI/CD workflow

## Architecture

The application follows a layered and modular architecture with clear separation of responsibilities.

```
Client
  |
  v
Controller
  |
  v
Service
  |
  v
Repository
  |
  v
PostgreSQL
```

Additional infrastructure components are integrated depending on the use case:

```text
                    +----------------+
                    |     Client     |
                    +--------+-------+
                             |
                             v
                    +----------------+
                    |   Controller   |
                    +--------+-------+
                             |
                             v
                    +----------------+
                    |    Service     |
                    +---+--------+---+
                        |        |
             +----------+        +----------+
             |                              |
             v                              v
      +-------------+                +-------------+
      | Repository  |                | RabbitMQ /  |
      | PostgreSQL  |                |   Redis     |
      +-------------+                +-------------+
                                            |
                                            v
                                    +---------------+
                                    | Notifications |
                                    | / WebSockets  |
                                    +---------------+
```

The project is divided into multiple modules to keep responsibilities separated and improve maintainability.

Examples include:

* Application configuration
* User management
* Authentication and security
* Chat and messaging
* Notifications
* Shared/common components

## Key Learnings

While working on this project, I practiced and improved my understanding of several backend concepts.

### Authentication and Security

I learned how access and refresh tokens work together, how Spring Security integrates with JWT authentication, and why sensitive tokens should not be stored in plain text.

### Layered Backend Architecture

I gained practical experience separating responsibilities between controllers, services, repositories, DTOs, and persistence models.

### Database Persistence

I practiced working with Spring Data JPA and PostgreSQL, including entity relationships, repositories, database queries, transactions, and indexing.

### Real-Time Communication

I learned how WebSockets can be used for real-time messaging and how active user sessions and chat connections can be managed on the backend.

### Event-Driven Architecture

Using RabbitMQ helped me understand the producer-consumer model and how asynchronous events can decouple application components.

### Caching

I practiced using Redis to reduce unnecessary database access and improve response times for frequently accessed data.

### External Integrations

The project provided practical experience integrating external services for push notifications, email delivery, and file storage.

### Production Configuration

I learned how development and production configurations can be separated and how environment variables and external secrets are used instead of hard-coded credentials.

### CI/CD

I gained experience with automated build and deployment workflows using GitHub Actions.

## Purpose

This repository is a **learning project** used to practice and understand real-world backend concepts such as:

* REST API design
* Authentication and authorization
* Database persistence
* Token lifecycle management
* Event-driven communication
* Caching
* Real-time communication
* Application security
* Modular backend architecture
* Deployment and production configuration

The focus of the project is on learning, experimenting with, and applying backend development concepts in practice.
