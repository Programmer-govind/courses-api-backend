# Courses API Backend

This is a Spring Boot REST API for managing courses and course delivery instances, developed for the IIT Bombay ASC internship assignment.

## Features

- Create, list, and delete courses with prerequisite validation
- Prevent deletion of courses that are prerequisites for others
- Create, list, and delete course delivery instances
- PostgreSQL database support (via Docker)
- Proper error handling and HTTP status codes

## API Endpoints

- `POST /api/courses` — Create a new course (validates prerequisites)
- `GET /api/courses` — List all courses (with prerequisites)
- `GET /api/courses/{id}` — Get a single course (with prerequisites)
- `DELETE /api/courses/{id}` — Delete a course (blocked if it is a prerequisite for another)
- `POST /api/instances` — Create a new course instance
- `GET /api/instances/{year}/{semester}` — List course instances for a year/semester
- `GET /api/instances/{year}/{semester}/{courseId}` — Get details of a course instance
- `DELETE /api/instances/{year}/{semester}/{courseId}` — Delete a course instance

## Running Locally

1. Build the project:
   ```sh
   mvn clean install
   ```
2. Run the application:
   ```sh
   mvn spring-boot:run
   ```
3. The API will be available at `http://localhost:8080/api/`

## Running with Docker

1. Build the Docker image:
   ```sh
   docker build -t your-dockerhub-username/courses-api .
   ```
2. Run with Docker Compose (see root `docker-compose.yaml`):
   ```sh
   docker-compose up --build
   ```

## Running with Docker Compose

1. Make sure you have Docker and Docker Compose installed.
2. Clone this repository (and the frontend repository if separate).
3. Ensure your `docker-compose.yaml` file is present in the project root (or as provided).
4. Run the following command from the directory containing `docker-compose.yaml`:
   ```sh
   docker-compose up --build
   ```
5. This will start the database, backend, and frontend containers. The backend API will be available at `http://localhost:8080/api/` and the frontend at `http://localhost:3000/`.
6. To stop the services, press `Ctrl+C` and then run:
   ```sh
   docker-compose down
   ```

## Design & Implementation

- **Spring Boot** is used for rapid REST API development and easy integration with PostgreSQL.
- **JPA/Hibernate** manages entity relationships, including many-to-many for prerequisites.
- **Validation** ensures prerequisites exist before course creation and blocks deletion if dependencies exist.
- **Error handling** uses appropriate HTTP status codes and clear messages.

## Justification

- Chose Spring Boot for its robustness, community support, and ease of REST API development.
- Used Docker for reproducible, portable deployment and easy local testing.
- Designed the API to be clear, RESTful, and easy to consume from any frontend.
