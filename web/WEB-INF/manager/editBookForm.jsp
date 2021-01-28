<%-- 
    Document   : editBookForm
    Created on : 10.12.2020, 9:05:47
    Author     : jvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Редактирование книги</title>
    </head>
    <body>
        <h1>Редактирование книги</h1>
        <p>${info}</p>
        <p><a href="index.jsp">Главная</a></p>
        <form action="editBook" method="POST">
            <input type="hidden" name="bookId" value="${book.id}">
            Название книги: <input type="text" name="name" value="${book.name}"><br>
            Автор книги: <input type="text" name="author" value="${book.author}"><br>
            Год издания книги: <input type="text" name="publishedYear" value="${book.publishedYear}"><br>
            <input type="submit" name="submit" value="Изменить">
        </form>
    </body>
</html>
