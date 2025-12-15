# Student Management System

This is a full-stack Java web app built using Spring Boot. It helps manage student information with features like registration, login, and role-based access for students and admins.

## Features

### 👨‍🎓 Student
- Register with name, email, phone, age, gender, city, and course
- Login and view personal dashboard
- Update limited personal details

### 🧑‍💼 Admin
- Secure login
- Dashboard with total student count and stats
- Add, edit, delete students and courses
- Filter students by name, city, course, gender

## Tech Stack
- Java 17, Spring Boot (REST APIs, Security)
- MySQL, Hibernate
- JSP, JSTL, HTML, Bootstrap (React frontend coming soon)
- Maven, Embedded Tomcat

## Project Structure
- `controller/` – REST controller classes
- `model/` – Entity classes
- `repository/` – Spring Data JPA repositories
- `service/` – Business logic
- `config/` – Security configuration
- `dto/` – Request/response DTOs for APIs
- `WEB-INF/jsp/` – Legacy JSP views (to be replaced with React)

## Database
- Create a MySQL database named `student_db`
- Update your `application.properties` or `application-local.properties` with DB username and password

## Future Plans
- Integrate React frontend pages for student and admin dashboards
- Remove JSP views once React frontend is ready
- Enhance role-based access and API validation
