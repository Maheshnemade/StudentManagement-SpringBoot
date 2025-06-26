<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Student</title>
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
            background-color: #2196F3;
            border: none;
            color: white;
            cursor: pointer;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <h2>Add Student</h2>
    <p>Total courses loaded: ${fn:length(courses)}</p>

    <form action="/admin/students/add" method="post">
        <label>Name:</label>
        <input type="text" name="name" required />

        <label>Email:</label>
        <input type="email" name="email" required />

        <label>Phone Number:</label>
        <input type="text" name="phoneNumber" maxlength="10" required />

        <label>Address:</label>
        <input type="text" name="address" required />

        <label>Age:</label>
        <input type="number" name="age" min="18" required />

        <label>Course:</label>
        <select name="courseId" required>
            <option value="" disabled selected>Select a course</option>
            <c:forEach items="${courses}" var="course">
                <option value="${course.id}">${course.courseName}</option>
            </c:forEach>
        </select>

        <button type="submit">Save Student</button>
    </form>
</body>
</html>
