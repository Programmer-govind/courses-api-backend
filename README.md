# 🚀 Courses API Backend

Welcome to the **Courses API** – a robust Spring Boot backend for managing courses and their instances, built for the IIT Bombay ASC Internship Assignment.

---

## ✨ Features

- **RESTful API** for managing courses and course instances
- **PostgreSQL** database integration
- **Robust validation** and error handling
- **CORS enabled** for seamless frontend integration
- **Dockerized** for easy deployment
- **Ready for production** with DockerHub & Compose support

---

## 📦 API Endpoints

- `GET /api/courses` – List all courses
- `POST /api/courses` – Add a new course
- `GET /api/courses/{id}` – Get course details
- `PUT /api/courses/{id}` – Update a course
- `DELETE /api/courses/{id}` – Delete a course
- `GET /api/instances` – List all course instances
- `POST /api/instances` – Add a new course instance
- ...and more!

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3**
- **PostgreSQL 15**
- **Maven**
- **Docker & Docker Compose**

---

## 🚀 Quick Start

### 1. Clone the Repository

```sh
git clone <your-repo-url>
cd courses-api
```

### 2. Run with Docker Compose

**Recommended:** Use the root `docker-compose.yaml` to start the entire stack (backend, frontend, and database):

```sh
docker-compose up --build
```

Or, to run only the backend and database:

```sh
docker-compose up --build
```

### 3. API Access

- API available at: `http://localhost:8080/api/courses`

---

## 🐳 Docker Usage

### Build & Push Image

```sh
docker build -t <your-dockerhub-username>/courses-api:latest .
docker push <your-dockerhub-username>/courses-api:latest
```

### Run with Docker Compose

```sh
docker-compose up --build
```

---

## 📝 Assignment Justification

- **All endpoints** and **validation** implemented as per requirements
- **CORS** globally enabled for frontend integration
- **Dockerized** for easy deployment and grading
- **Documentation** provided for quick setup and usage

---

## 🤝 Author & Credits

- **Author:** Gautam Govind
- **For:** IIT Bombay ASC Internship Assignment 2025

---

## 📄 License

MIT License
