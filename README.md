# Event Booking System (Java & Spring Boot Microservices)

This **Event Booking System** is a fully containerized, microservices-based platform developed using **Java** and **Spring Boot**, designed to manage **event scheduling, room booking, and user management** in a secure and scalable way.

---

## Key Features

###  Microservices Architecture
- Fully decoupled services for **Booking**, **Approval**, **Event**, **User**, **Room**, and **API Gateway**.  
- Ensures **modularity**, **scalability**, and **independent deployment** of each service.

### API Gateway & Secure Routing
- Integrated **API Gateway** for centralized routing of all microservice endpoints.
- **Secured with Keycloak** for user **authentication** and **role-based authorization (RBAC)**.

### Service-to-Service Communication
- Uses **RESTful communication** between services for efficient execution of booking workflows.

### API Documentation
- **Swagger/OpenAPI** integrated into all services for **interactive API documentation and testing**.

### Postman Collections Included
- Pre-built **Postman collections** for each microservice to simplify testing and showcase available endpoints:
  - **Booking Service**
  - **Room Service**
  - **User Service**
  - **Approval Service**
  - **Event Service**
  - **API Gateway**

---

## Tech Stack

- **Java**, **Spring Boot**, **Spring Security**
- **Keycloak** for Authentication & Authorization
- **Docker & Docker Compose** for full containerized deployment and orchestration
- **Postman Collections** for API testing
- **Swagger/OpenAPI** for service documentation

---
