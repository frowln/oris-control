<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <title>Профиль</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h1>Добро пожаловать!</h1>

<form id="weatherForm">
  <label for="city">Введите город:</label>
  <input type="text" id="city" name="city" required>
  <button type="submit">Узнать погоду</button>
</form>

<div id="weatherResult" style="margin-top: 20px;"></div>

<script>
  $(document).ready(function () {
    $('#weatherForm').on('submit', function (event) {
      event.preventDefault();

      const formData = {
        city: $('#city').val()
      };

      $.ajax({
        url: '/profile',
        type: 'POST',
        data: formData,
        success: function (response) {
          if (response.error) {
            $('#weatherResult').html('<p style="color: red;">' + response.error + '</p>');
          } else {
            $('#weatherResult').html(
                    '<h2>Погода в городе ' + response.city + '</h2>' +
                    '<p>Температура: ' + response.temperature + '°C</p>' +
                    '<p>Условия: ' + response.condition + '</p>'
            );
          }
        },
        error: function () {
          $('#weatherResult').html('<p style="color: red;">Произошла ошибка. Попробуйте ещё раз.</p>');
        }
      });
    });
  });
</script>
</body>
</html>

