<%-- 
    Document   : loginForm
    Created on : Dec 3, 2020, 8:50:52 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Вход в систему</title>
  </head>
  <body>
    <h1>Введите логин и пароль</h1>
    <p>${info}</p>
    <form action="login" method="POST">
        Логин <input type="text" name="login" value=""><br>
        Пароль <input type="password" name="password" value=""><br>
        <input type="submit" value="Войти"><br>
    </form>
    Нет аккаунта? <a href="registrationForm">Зарегистрироваться</a>
  </body>
</html>
