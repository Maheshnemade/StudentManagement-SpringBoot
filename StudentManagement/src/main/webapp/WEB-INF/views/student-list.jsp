<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Student List</title></head>
<body>
<h2>Students</h2>
<a href="/admin/students/add">Add New Student</a>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Age</th>
        <th>Course</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.email}</td>
            <td>${student.phoneNumber}</td>
            <td>${student.age}</td>
            <td>${student.course.courseName}</td>
            <td>
                <a href="/admin/students/edit/${student.id}">Edit</a>
                <a href="/admin/students/delete/${student.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>