<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h2 class="mb-4">Welcome, ${student.name}!</h2>

    <table class="table table-bordered">
        <tr>
            <th>ID</th>
            <td>${student.id}</td>
        </tr>
        <tr>
            <th>Email</th>
            <td>${student.email}</td>
        </tr>
        <tr>
            <th>Phone Number</th>
            <td>${student.phoneNumber}</td>
        </tr>
        <tr>
            <th>Address</th>
            <td>${student.address}</td>
        </tr>
        <tr>
            <th>Age</th>
            <td>${student.age}</td>
        </tr>
        <tr>
            <th>Course</th>
            <td>${student.course.courseName}</td>
        </tr>
    </table>

    <a href="/student/edit" class="btn btn-primary">Edit Profile</a>
    <a href="/logout" class="btn btn-danger ms-2">Logout</a>
</body>
</html>
