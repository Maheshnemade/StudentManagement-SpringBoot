<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Student Search Results</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 30px;
        }
        .results-container {
            max-width: 900px;
            margin: auto;
        }
        h2 {
            margin-bottom: 20px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }
        .action-btn {
            text-decoration: none;
            color: blue;
            margin-right: 10px;
        }
        .no-results {
            color: red;
        }
        .back-btn {
            margin-top: 20px;
            display: inline-block;
            text-decoration: none;
            color: #333;
        }
    </style>
</head>
<body>

<div class="results-container">
    <h2><i class="fas fa-search"></i> Student Search Results</h2>

    <c:choose>
        <c:when test="${not empty students}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Age</th>
                    <th>Course</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.email}</td>
                        <td>${student.phoneNumber}</td>
                        <td>${student.age}</td>
                        <td>${student.course.courseName}</td>
                        <td>
                            <a href="/admin/students/edit/${student.id}" class="action-btn">
                                <i class="fas fa-edit"></i> Edit
                            </a>
                            <a href="/admin/students/delete/${student.id}" class="action-btn"
                               onclick="return confirm('Are you sure you want to delete this student?');">
                                <i class="fas fa-trash"></i> Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p class="no-results">No students found for your search. <i class="fas fa-user-slash"></i></p>
        </c:otherwise>
    </c:choose>

    <a href="/admin/students" class="back-btn">
        <i class="fas fa-arrow-left"></i> Back to Student List
    </a>
</div>

</body>
</html>
