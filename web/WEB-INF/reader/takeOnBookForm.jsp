<%-- 
    Document   : istBooks
    Created on : Dec 1, 2020, 9:59:17 AM
    Author     : Melnikov
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Выдать книгу</title>
  </head>
  <body>
    <h1>Выдать книгу</h1>
    <form action="takeOnBook" method="POST">
        <p>Выберите книгу:</p>
        <select name="bookId">
          <option value="">Выберите книгу</option>
              <c:forEach var="book" items="${listBooks}" varStatus="status">
                  <option value="${book.id}">${book.name}. ${book.author}. ${book.publishedYear}</option>
              </c:forEach>
        </select>
        
        <br><br>
        <input type="submit" value="Взять книгу">
    </form>
  </body>
</html>
