# Customer Support Ticket System

A Spring Boot REST API for managing customer support tickets, assigning agents, and tracking ticket status.

## Tech Stack

- Java 17
- Spring Boot 3.4.x
- Spring Web
- Spring Data JPA
- Spring Validation
- H2 Database
- Maven

## Project Setup

1. Install Java 17 or newer.
2. Install Maven 3.9+.
3. Run the application with `mvn spring-boot:run`.
4. Open the API in your REST client of choice.

## API Endpoints

### Agents

- `POST /api/agents`
- `GET /api/agents`
- `GET /api/agents/{id}`

### Tickets

- `POST /api/tickets`
- `GET /api/tickets`
- `GET /api/tickets/{id}`
- `POST /api/tickets/{id}/assign`
- `PATCH /api/tickets/{id}/status`

## Database

The app uses an in-memory H2 database for local development.

## API Documentation

Swagger/OpenAPI UI is available (when running) at: `/swagger-ui.html` and the raw OpenAPI JSON at `/v3/api-docs`.
