<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename = "messages" var = "messages"/>

        <div class="container">
            <h2><fmt:message key="message.error" bundle="${messages}"/></h2>
            Request from ${pageContext.errorData.requestURI} is failed
            <br/>
            Servlet name: ${pageContext.errorData.servletName}
            <br/>
            Status code: ${pageContext.errorData.statusCode}
            <br/>
            Exception: ${pageContext.exception}
            <br/>
            Message from exception: ${pageContext.exception.message}

            <c:if test="$nullPage != null">
                ${nullPage}
            </c:if>

        </div>
