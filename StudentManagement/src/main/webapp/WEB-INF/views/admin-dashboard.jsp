<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body { font-family: Arial; margin: 20px; }
        .card { border: 1px solid #ccc; padding: 20px; margin-bottom: 20px; border-radius: 8px; }
        a.button { padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 5px; }
    </style>
</head>
<body>
    <h2>Welcome Admin</h2>
    <div class="card">
        <p>Total Students: ${totalUsers}</p>
        <a class="button" href="/admin/students">Manage Students</a>
        <a class="button" href="/admin/courses">Manage Courses</a>
    </div>
	<a href="/logout" class="btn btn-danger ms-2">Logout</a>
</body>
</html>