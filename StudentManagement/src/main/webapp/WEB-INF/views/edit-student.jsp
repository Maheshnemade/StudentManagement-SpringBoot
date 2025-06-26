<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Student</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: Arial;
            width: 500px;
            margin: 40px auto;
        }
        label {
            display: block;
            margin-top: 10px;
        }
        input, select {
            width: 100%;
            padding: 6px;
        }
        button {
            margin-top: 15px;
            padding: 8px 16px;
            background-color: #4CAF50;
            border: none;
            color: white;
            cursor: pointer;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <h2>Edit Student</h2>

    <c:choose>
        <c:when test="${isAdmin}">
            <form action="/admin/students/edit/${student.id}" method="post">
        </c:when>
        <c:otherwise>
            <form action="/student/edit" method="post">
        </c:otherwise>
    </c:choose>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <label>Name:</label>
        <input type="text" name="name" value="${student.name}" required />

        <label>Email:</label>
        <input type="email" name="email" value="${student.email}" required />

        <label>Phone Number:</label>
        <input type="text" name="phoneNumber" value="${student.phoneNumber}" required />

        <label>Address:</label>
        <input type="text" name="address" value="${student.address}" required />

        <label>Age:</label>
        <input type="number" name="age" value="${student.age}" required />

        <c:if test="${isAdmin}">
            <label>Course:</label>
            <select name="courseId" required>
                <c:forEach items="${courses}" var="course">
                    <option value="${course.id}" <c:if test="${course.id == student.course.id}">selected</c:if>>${course.courseName}</option>
                </c:forEach>
            </select>
        </c:if>

        <button type="submit">Update Student</button>
    </form>
</body>
</html>
