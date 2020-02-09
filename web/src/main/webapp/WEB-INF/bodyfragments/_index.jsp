<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename = "messages" var = "messages"/>
<div class="container">
        <h2><fmt:message key="message.main.page" bundle="${messages}"/></h2>
<p>
        <a href="${pageContext.request.contextPath}/controller?command=tologin"><fmt:message key="message.login" bundle="${messages}"/></a><br/>
        <a href="${pageContext.request.contextPath}/controller?command=toregister"><fmt:message key="message.registration" bundle="${messages}"/></a><br/>
</p>
        </div>
