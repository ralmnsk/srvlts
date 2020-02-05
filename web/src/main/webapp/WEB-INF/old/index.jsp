<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Главная страница</h2>
<a href="${pageContext.request.contextPath}/register">Регистрация</a>
<br/>
<a href="${pageContext.request.contextPath}/login">Вход</a>
<br/>
Проверка EL: ${user}
</body>
</html>
