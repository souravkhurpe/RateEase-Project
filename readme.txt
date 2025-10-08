Project Title: RateEase (Microservices-Based Architecture)
Overview

The Hotel Rating System is a microservices-based web application designed to manage and display hotel ratings and reviews provided by users. The system allows users to register, log in, and view their ratings, along with corresponding hotel details. It demonstrates a scalable, modular, and secure architecture built using Spring Boot and Spring Cloud, showcasing best practices in microservice communication, service discovery, configuration management, and API security.

System Architecture

The system follows a Microservices Architecture and consists of the following core components:

User Service

Handles user-related operations such as registration, login, and profile management.

Provides CRUD functionality for user entities.

Communicates with the Rating Service to fetch ratings based on user ID.

Secured with JWT and Role-Based Access Control (RBAC).

Rating Service

Manages user ratings for hotels.

Performs CRUD operations on rating data.

Fetches hotel details from the Hotel Service using Feign Client based on hotelId.

Acts as a bridge between the User and Hotel services.

Hotel Service

Manages hotel information such as name, location, and description.

Supports CRUD operations on hotel data.

Provides hotel details to the Rating Service when requested.

Eureka Server

Acts as a Service Registry, where all microservices (User, Rating, Hotel, Config) are registered.

Enables service discovery and dynamic communication without hardcoding service URLs.

Config Server

Manages centralized configurations for all microservices.

Connected to a GitHub repository where the shared configuration files are stored.

Each service fetches its configuration from this server at startup.

API Gateway

Serves as a single entry point for all client requests.

Handles routing to appropriate microservices based on endpoints.

Integrates JWT Authentication for secure access and Role-Based Authorization for controlling endpoints.

Security

Implemented JWT (JSON Web Token) for secure authentication and session management.

Configured Role-Based Access Control (RBAC) to restrict access to specific endpoints (e.g., admin vs user roles).

The API Gateway validates JWT tokens before routing requests to backend services.

Communication Flow

A new user signs up via the User Service and then signs in to receive a JWT Token.

Authenticated requests are routed through the API Gateway, which validates the JWT.

When a user fetches their details, the User Service communicates with the Rating Service (via Feign Client) to get all ratings by that user.

The Rating Service then calls the Hotel Service (via Feign Client) using the hotelId field to fetch hotel details for each rating.

Combined data (User → Ratings → Hotels) is returned to the user in a consolidated response.

Databases

Each microservice maintains its own dedicated database to ensure loose coupling and service-level data independence:

UserDB – Stores user details and credentials

RatingDB – Stores user-hotel rating data

HotelDB – Stores hotel information

Tech Stack

Backend: Java, Spring Boot

Microservices: Spring Cloud (Eureka, Config Server, API Gateway)

Communication: REST APIs, Feign Client

Security: Spring Security, JWT, RBAC

Database: MySQL

Version Control: Git, GitHub

Build Tool: Maven

Configuration Management: Git-based Spring Cloud Config Server

Key Features

✅ Microservices architecture with independent deployable services
✅ Centralized configuration management via Config Server
✅ Service discovery and dynamic routing using Eureka and API Gateway
✅ Secure authentication and authorization using JWT & RBAC
✅ Inter-service communication with Feign Client
✅ Separate databases for data isolation and scalability
✅ Complete CRUD functionality across all services

Project Flow Summary

User → API Gateway → User Service → Rating Service → Hotel Service → Response to User
