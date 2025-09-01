# Job App REST

## Introduction
**Job App REST** is a Spring Boot RESTful API for managing job applications.  
It provides features for employers to post jobs, users to register/apply, and authentication using JWT tokens for secure access.

---

## Table of Contents
- [Introduction](#introduction)
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Dependencies](#dependencies)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Contributors](#contributors)

---

## Installation

### Prerequisites
- Java 17+ (or compatible version required by Spring Boot)
- Maven 3.x
- MySQL database

### Steps
1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/Job_App_Rest.git
   ```
2. Navigate into the project directory:
   ```bash
   cd Job_App_Rest
   ```
3. Build the project using Maven:
   ```bash
   ./mvnw clean install
   ```
4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## Usage
Once the server is running, the REST API is available at:
```
http://localhost:8080
```

You can test the endpoints using Postman, cURL, or any REST client.

---

## Features
- Employer management (create, update, delete employers)
- Job posting (add, list, delete job posts)
- User registration and management
- JWT-based authentication and authorization
- RESTful API endpoints

---

## Dependencies
This project uses the following dependencies:

- `org.springframework.boot:spring-boot-starter-data-jpa`
- `org.springframework.boot:spring-boot-starter-security`
- `org.springframework.boot:spring-boot-starter-web`
- `com.mysql:mysql-connector-j`
- `org.springframework.boot:spring-boot-starter-test`
- `org.springframework.security:spring-security-test`
- `io.jsonwebtoken:jjwt-api:0.11.5`
- `io.jsonwebtoken:jjwt-impl:0.11.5`
- `io.jsonwebtoken:jjwt-jackson:0.11.5`
- `org.projectlombok:lombok`

---

## Configuration
Edit `src/main/resources/application.properties` to configure your database and security:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/job_app
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
jwt.secret=yourSecretKey
```

---

## API Endpoints

### User Endpoints
- `POST /user/register` – Register a new user
- `POST /user/login` – Authenticate user and return JWT token

### Employer Endpoints
- `POST /employer/add` – Add employer
- `GET /employer/{id}` – Get employer by ID
- `DELETE /employer/{id}` – Delete employer

### Job Post Endpoints
- `POST /job/add` – Add new job post
- `GET /job/all` – List all jobs
- `DELETE /job/{id}` – Delete a job

---

## Testing
Run unit and integration tests with:
```bash
./mvnw test
```

---

## Contributors
- [Your Name](https://github.com/your-username)
