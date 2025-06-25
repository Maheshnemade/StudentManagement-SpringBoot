<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Courses</title>
</head>
<body>
<h2>Courses</h2>
<a href="/admin/courses/add">Add New Course</a>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${courses}" var="course">
        <tr>
            <td>${course.id}</td>
            <td>${course.courseName}</td>
            <td>
                <a href="/admin/courses/delete/${course.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>