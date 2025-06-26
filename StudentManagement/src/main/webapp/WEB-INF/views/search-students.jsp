<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Search Student</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }

        h2 {
            color: #333;
        }

        form {
            margin-bottom: 20px;
        }

        input[type="text"] {
            padding: 8px;
            width: 300px;
        }

        button {
            padding: 8px 16px;
            background-color: #4CAF50;
            border: none;
            color: white;
            cursor: pointer;
        }

        table {
            width: 70%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ccc;
        }

        th {
            background-color: #f2f2f2;
        }

        .actions {
            text-align: center;
        }

        .no-results {
            color: red;
        }

        a {
            display: inline-block;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<h2>Search Student</h2>

<!-- Search form -->
<form action="${pageContext.request.contextPath}/admin/user/search" method="get">
    <label for="keyword">Enter Student Username:</label>
    <input type="text" id="keyword" name="keyword" placeholder="e.g., john123" required />
    <button type="submit">Search</button>
</form>

<!-- Display search results -->
<c:choose>
    <c:when test="${not empty users}">
        <table>
            <tr>
                <th>Username</th>
                <th class="actions">Actions</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.username}</td>
                    <td class="actions">
                        <form action="${pageContext.request.contextPath}/admin/user/delete/${user.userId}" method="post"
                              onsubmit="return confirm('Are you sure you want to delete this student?');">
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <p class="no-results">No students found for your search.</p>
    </c:otherwise>
</c:choose>

<a href="${pageContext.request.contextPath}/admin/users">← Back to Student List</a>

</body>
</html>
