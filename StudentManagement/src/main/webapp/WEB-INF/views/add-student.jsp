<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
    <h2>Add Student</h2>

    <p>Total courses loaded: ${fn:length(courses)}</p>

    <form action="/admin/students/add" method="post">
        <label>Name:</label>
        <input type="text" name="name" required /><br/>

        <label>Email:</label>
        <input type="email" name="email" required /><br/>

        <label>Phone Number:</label>
        <input type="text" name="phoneNumber" maxlength="10" required /><br/>

        <label>Address:</label>
        <input type="text" name="address" required /><br/>

        <label>Age:</label>
        <input type="number" name="age" min="18" required /><br/>

        <label>Course:</label>
        <select name="courseId" required>
            <option value="" disabled selected>Select a course</option>
            <c:forEach items="${courses}" var="course">
                <option value="${course.id}">${course.courseName}</option>
            </c:forEach>
        </select><br/>

        <button type="submit">Save Student</button>
    </form>
</body>
</html>
