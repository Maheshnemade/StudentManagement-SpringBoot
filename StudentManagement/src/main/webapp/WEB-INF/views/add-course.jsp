<!DOCTYPE html>
<html>
<head>
    <title>Add Course</title>
    <style>
        body {
            font-family: Arial;
            width: 400px;
            margin: 60px auto;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        input[type="text"] {
            width: 100%;
            padding: 6px;
        }
        button {
            margin-top: 15px;
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h2>Add Course</h2>
    <form action="/admin/courses/add" method="post">
        <label>Course Name:</label>
        <input type="text" name="courseName" required />
        <button type="submit">Add Course</button>
    </form>
</body>
</html>
