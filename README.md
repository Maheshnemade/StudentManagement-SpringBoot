# Student Management System

This is a full-stack Java web application built using Spring Boot. It helps manage student information with features like registration, login, and role-based access for students and admins.

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
- Java 17, Spring Boot (MVC, Security)
- JSP, JSTL, HTML, Bootstrap
- MySQL
- Maven, Embedded Tomcat

## Project Structure

- `controller/` – All controller classes  
- `model/` – Entity classes  
- `repository/` – Spring Data JPA repositories  
- `service/` – Business logic  
- `config/` – Security configuration  
- `WEB-INF/jsp/` – JSP views (login, dashboard, list, update)

## Database

Make sure you have a MySQL database named `student_db`.  
Update your `application.properties` with your DB username and password.

