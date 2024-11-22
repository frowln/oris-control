<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Добро пожаловать</h1>
<p>Войдите или зарегистрируйтесь</p>
<form method="post" action="/login">
    <label for="username">Имя:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <button type="submit">Войти</button>
</form>
<p style="color: red;">${requestScope.errorMessage}</p>
<p>Нет аккаунта? <a href="/registration">Зарегистрируйтесь</a></p>
</body>
</html>
