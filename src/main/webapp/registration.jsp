<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h2>Регистрация</h2>
<form method="post" action="/registration">
    <label for="username">Имя:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <button type="submit">Зарегистрироваться</button>
</form>
<p style="color: red;">${error}</p>
<p>Уже есть аккаунт? <a href="/login">Войдите</a></p>
</body>
</html>
