<!DOCTYPE html>
<html>
<head><title>Add Course</title></head>
<body>
<h2>Add Course</h2>
<form action="/admin/courses/add" method="post">
    <label>Course Name:</label>
    <input type="text" name="courseName" required />
    <button type="submit">Add Course</button>
</form>
</body>
</html>