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
    <h1>Список книг:</h1>
        <ul>
          <c:forEach var="book" items="${listBooks}" varStatus="status">
                <li>
                    ${status.index}. ${book.name}. ${book.author}. ${book.publishedYear}
                </li>
          </c:forEach>
        </ul>
    
  </body>
</html>