<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename = "messages" var = "messages"/>

<c:if test="${processFlag == null }">
    <br/>
    <fmt:message key="message.surname" bundle="${messages}"/>: ${person.surname}
    <br/><br/>
    <fmt:message key="message.name" bundle="${messages}"/>: ${person.name}
    <br/><br/>
    <fmt:message key="message.login" bundle="${messages}"/>: ${person.login}
    <br/><br/>
</c:if>
