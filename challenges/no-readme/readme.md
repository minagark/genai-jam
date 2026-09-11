## Introduction

This application is a Maven-based Spring application that stores CompactDisc objects and their Track records in a relational database, exposes a JPA-backed REST API with CRUD operations (Swagger-documented), serves static demo pages under src/main/resources/static, and includes example REST snippets (rest/*.rest) and SQL DDL (sql/createTables.sql) for testing and DB setup.

## Simple user flow 
1. Start the app (see Deploy below).  
2. Open the demo UI: http://localhost:8080/ (index and listcds pages).  
3. Create a CD:
   - Use the UI form on the demo pages, or
   - See rest/postcd.rest for an example HTTP request to create a CompactDisc.  
4. List CDs:
   - Use the UI or call the REST list endpoint (see controller or UI JS).  
5. Inspect tracks for a CD via the UI or the appropriate REST endpoint.  
6. Delete a CD:
   - Use the UI or see rest/deletecd.rest for an example delete request.

(Exact endpoint paths and JSON request bodies are provided in the rest/*.rest files in this repo.)

## How to deploy this project
Prerequisites: JDK (11+ recommended), Maven.

From project root (Windows):
- Run from source:
  - Command Prompt:
    - mvn spring-boot:run
  - PowerShell:
    - mvn spring-boot:run
- Build and run jar:
  - mvn clean package
  - java -jar target\*\.jar
- Use the Docker profile (uses application-docker.properties):
  - Windows (Command Prompt):
    - set SPRING_PROFILES_ACTIVE=docker
    - mvn spring-boot:run
  - PowerShell:
    - $env:SPRING_PROFILES_ACTIVE='docker'; mvn spring-boot:run
Notes:
- Database connection and other runtime settings are in src/main/resources/application.properties and application-docker.properties. Override via environment variables or command-line properties (e.g., --spring.datasource.url=...).

## How to monitor and debug
- Logs: configured by src/main/resources/log4j2.properties — application writes to console by default; watch console output where the app runs.
- API docs / exploration: Swagger is configured (see SwaggerConfig). Common URL: http://localhost:8080/swagger-ui.html or http://localhost:8080/swagger-ui/index.html (check startup logs for exact path).
- Static UI: exercise the API via the pages under http://localhost:8080/ to confirm behavior.
- Database: use the SQL in sql/createTables.sql to create or inspect schema; connect with any DB client to check persisted rows.
- Process/port checks: use standard OS tools (Windows: netstat /tasklist, Resource Monitor).
- (Optional) Add Spring Boot Actuator for richer health/metrics endpoints (/actuator/health, /actuator/metrics) — this requires adding the dependency and exposing endpoints (not included in this repo).

## Useful files to inspect
- rest/postcd.rest, rest/deletecd.rest — example HTTP requests for create/delete operations.  
- src/main/resources/static/ — demo HTML/JS that exercises the REST API.  
- src/main/java/.../rest/CompactDiscController.java — controller endpoints.  
- src/main/java/.../entities/CompactDisc.java and Track.java — data model.  
- sql/createTables.sql — DB schema DDL.  
- application.properties / application-docker.properties — runtime configuration.

## Troubleshooting
- If the app fails to start, check console logs for DB connection errors; ensure DB credentials and URL in application.properties match a running DB.
- If the UI shows no data, verify the REST endpoints with the .rest snippets or via Swagger.

