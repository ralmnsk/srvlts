<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Сортировка</h2>
<form name="sortForm" action="sorted" method="post">
    <input type="text" name="sortInput">
    <input type="submit" name="btn" value="Отправить">
</form>
    <c:if test="${ size > 0 }">
        Отсортированный массив: ${res}
    </c:if>
<p>
    <c:out value="Проверка работы taglib c:"/>
<p>
    <a href="${pageContext.request.contextPath}/">Главная страница</a>
</body>
</html>
