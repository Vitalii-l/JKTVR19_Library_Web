<%-- 
    Document   : page1
    Created on : Nov 24, 2020, 10:43:02 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Добавление книги</title>
  </head>
  <body>
    <div>Добавить книгу</div>
    <p>${info}</p>
    <br>
    <a href="index.jsp">Home</a><br>
    <form action="createBook" method="POST">
        Название книги: <input type="text" name="name" value="${name}"><br>
        Автор книги: <input type="text" name="author" value="${author}"><br>
        Год издания книги: <input type="text" name="publishedYear" value="${publishedYear}"><br>
        <input type="submit" name="submit" value="Добавить книгу">
    </form>
  </body>
</html>
