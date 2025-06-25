<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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

<c:if test="${param.error == 'true'}">
    <p style="color:red;">Invalid username or password.</p>
</c:if>

<c:if test="${param.logout == 'true'}">
    <p style="color:green;">You have been logged out.</p>
</c:if>
</body>
</html>
