<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Student Registration</title>
    <style>
        body { font-family: Arial; background-color: #f0f0f0; padding: 40px; }
        .container { background: white; padding: 20px; border-radius: 10px; max-width: 600px; margin: auto; }
        label { display: block; margin-top: 10px; }
        input, select { width: 100%; padding: 8px; margin-top: 5px; }
        button { margin-top: 20px; padding: 10px 20px; }
        .error { color: red; }
        .success { color: green; }
    </style>
</head>
<body>
<div class="container">
    <h2>Student Registration</h2>
    <form action="/register" method="post">

        <!-- User info -->
        <label>Username:</label>
        <input type="text" name="username" required />

        <label>Password:</label>
        <input type="password" name="password" required />

        <!-- Student info -->
        <label>Name:</label>
        <input type="text" name="name" required />

        <label>Email:</label>
        <input type="email" name="email" required />

        <label>Phone Number:</label>
        <input type="text" name="phoneNumber" required maxlength="10" />

        <label>Address:</label>
        <input type="text" name="address" required />

        <label>Age:</label>
        <input type="number" name="age" min="18" required />

        <!-- Course selection -->
        <label>Course:</label>
        <select name="courseId" required>
            <option value="">-- Select Course --</option>
            <c:forEach items="${courses}" var="course">
                <option value="${course.id}">${course.courseName}</option>
            </c:forEach>
        </select>

        <button type="submit">Register</button>
    </form>

    <c:if test="${param.error == 'exists'}">
        <p class="error">Username already exists!</p>
    </c:if>
    <c:if test="${param.success == 'true'}">
        <p class="success">Registration successful!</p>
    </c:if>
</div>
</body>
</html>
