<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }

        h2 {
            margin-bottom: 20px;
        }

        a {
            margin-right: 10px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 8px 12px;
        }

        th {
            background-color: #f2f2f2;
        }

        .search-bar {
            margin-bottom: 10px;
        }

        input[type="text"] {
            padding: 6px;
            width: 250px;
        }

        button {
            padding: 6px 12px;
        }
    </style>
</head>
<body>

<h2>Students</h2>

<!-- Add and Search Section -->
<a href="/admin/students/add">Add New Student</a>

<!-- Search Bar -->
<form action="${pageContext.request.contextPath}/admin/students/search" method="get" class="search-bar">
    <input type="text" name="keyword" placeholder="Search by name or email..." required />
    <button type="submit">Search</button>
</form>

<!-- Students Table -->
<table>
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
                <a href="/admin/students/delete/${student.id}" 
                   onclick="return confirm('Are you sure you want to delete this student?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
