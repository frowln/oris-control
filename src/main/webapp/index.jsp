<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h1>Welcome to the Platform</h1>
<p>Please login to continue or register for a new account.</p>
<form method="post" action="${pageContext.request.contextPath}/login">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <button type="submit">Login</button>
</form>
<p style="color: red;">${requestScope.errorMessage}</p>
<p>Don't have an account? <a href="${pageContext.request.contextPath}/register.jsp">Register here</a></p>
</body>
</html>
