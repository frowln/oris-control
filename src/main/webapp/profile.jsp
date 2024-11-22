<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <title>Profile</title>
</head>
<body>
<h1>Добро пожаловать!</h1>
<form method="post" action="/profile">
  <label for="city">Введите ваш город:</label>
  <input type="text" id="city" name="city" required>
  <br>
  <button type="submit">Получить погоду</button>
</form>

<c:if test="${not empty weatherInfo}">
  <h2>В городе ${weatherInfo.city}:</h2>
  <p>Температура: ${weatherInfo.temperature}°C</p>
  <p>Погода: ${weatherInfo.condition}</p>
</c:if>

<c:if test="${not empty error}">
  <p style="color: red;">${error}</p>
</c:if>

</body>
</html>

