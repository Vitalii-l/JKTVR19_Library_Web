<%-- 
    Document   : profile
    Created on : 22.01.2021, 9:17:42
    Author     : jvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Профиль пользователя</title>
    </head>
    <body>
        <h1>${user.reader.firstname} ${user.reader.lastname}. Логин: ${user.login}</h1>
        <form action="setNewProfile" method="POST">
            <input type="hidden" name="userId" value="${user.id}">
            Имя читателя: <input type="text" name="firstname" value="${user.reader.firstname}"><br>
            Фамилия читателя: <input type="text" name="lastname" value="${user.reader.lastname}"><br>
            Телефон: <input type="text" name="phone" value="${user.reader.phone}"><br>
            Пароль: <input type="password" name="password1" value=""><br>
            Пароль: <input type="password" name="password2" value=""><br>
            <input type="submit" name="submit" value="Обновить данные">
        </form>
    </body>
</html>
