<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            width: 400px;
            margin: 80px auto;
            padding: 25px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #f9f9f9;
        }
        h2 {
            text-align: center;
        }
        label {
            display: inline-block;
            width: 100px;
        }
        input[type="text"], input[type="password"] {
            width: 80%;
            padding: 6px;
            margin: 5px 0;
        }
        button {
            padding: 8px 16px;
            margin-top: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .message {
            margin-top: 10px;
            text-align: center;
        }
        .error { color: red; }
        .success { color: green; }
    </style>
</head>
<body>
<h2>Login Page</h2>

<form action="/doLogin" method="post">
    <label>Username:</label>
    <input type="text" name="username" required /><br/><br/>

    <label>Password:</label>
    <input type="password" name="password" required /><br/><br/>

    <button type="submit">Login</button>
</form>

<!-- Forgot Password Button -->
<form action="${pageContext.request.contextPath}/password-reset" method="get">
    <button type="submit">Forgot Password?</button>
</form>

<div class="message">
    <c:if test="${param.error == 'true'}">
        <p class="error">Invalid username or password.</p>
    </c:if>

    <c:if test="${param.logout == 'true'}">
        <p class="success">You have been logged out.</p>
    </c:if>
</div>

</body>
</html>
