<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename = "messages" var = "messages"/>

        <div class="container">
            <fmt:message key="message.success.registration" bundle="${messages}"/>
            <br/>
            <a href="${pageContext.request.contextPath}/controller?command=toregister"><fmt:message key="message.return.registration" bundle="${messages}"/></a>
            <br/>
            <a href="${pageContext.request.contextPath}/controller?command=tologin"><fmt:message key="message.enter" bundle="${messages}"/></a>
        </div>

