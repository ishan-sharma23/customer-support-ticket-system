# Project Requirements

## Runtime Requirements

- Java 17
- Maven 3.9 or later
- In-memory H2 database for local development

## Libraries Used

- Spring Boot 3.4.4
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- H2 Database
- spring-boot-starter-test

## What Each Library Does

- Spring Web: exposes the REST API.
- Spring Data JPA: handles persistence for agents and tickets.
- Spring Validation: validates request payloads.
- H2 Database: stores data locally during development.
- Spring Boot Test: supports application and controller tests.
 - springdoc-openapi-starter-webmvc-ui: provides Swagger/OpenAPI UI for API exploration.

## Developer convenience

- The project includes a Maven Wrapper (`mvnw`, `mvnw.cmd`, `.mvn/`) so contributors can build and run without installing Maven system-wide. Use `./mvnw.cmd` on Windows.
- A GitHub Actions workflow (`.github/workflows/ci.yml`) runs `./mvnw -B test` on pushes and PRs.
