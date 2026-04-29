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
3. You can run the application either using the project Maven wrapper (recommended) or a system Maven installation.

Using the Maven wrapper (no Maven install required):

```powershell
./mvnw.cmd test
./mvnw.cmd spring-boot:run
```

Using a system Maven installation:

```powershell
mvn test
mvn spring-boot:run
```

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

Example usage (create agent + ticket):

```bash
# create agent
curl -s -X POST http://localhost:8081/api/agents -H "Content-Type: application/json" -d '{"name":"Alice","email":"alice@example.com"}'

# create ticket
curl -s -X POST http://localhost:8081/api/tickets -H "Content-Type: application/json" -d '{"subject":"Login issue","description":"Cannot login","requesterEmail":"cust@example.com"}'

# list tickets (page 0)
curl -s http://localhost:8081/api/tickets?page=0&size=10
```

## Database

The app uses an in-memory H2 database for local development.

## API Documentation

Swagger/OpenAPI UI is available (when running) at: `/swagger-ui/index.html` and the raw OpenAPI JSON at `/v3/api-docs`.

H2 Console (in-memory DB) is available at `/h2-console` when the app is running. JDBC URL: `jdbc:h2:mem:support_ticket_db`.

Tips
- If you run into `mvn` not recognized errors, prefer the wrapper `mvnw.cmd` on Windows.
- To stop the app started with `spring-boot:run` press `Ctrl+C` in the terminal running it.
