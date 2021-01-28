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
    <title>Список книг</title>
  </head>
  <body>
    <h1>Возврат книги:</h1>
    <form action="returnBook" method="POST">
        <select name="historyId">
          <option value="">Выберите возвращаемую книгу</option>
              <c:forEach var="history" items="${listReadBooks}" varStatus="status">
                  <option value="${history.id}">Книгу "${history.book.name}" читает ${history.reader.firstname} ${history.reader.lastname}</option>
              </c:forEach>
        </select>
      <br><br>
      <input type="submit" value="Вернуть книгу">
    </form>
  </body>
</html>
