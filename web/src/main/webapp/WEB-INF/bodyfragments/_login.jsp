<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename = "messages" var = "messages"/>

<div class="container">
        <h2><fmt:message key="message.please.login" bundle="${messages}"/></h2>
            <div class="form-group">
                <form name="formLogin" method="POST" action="controller">
                    <input type="hidden" name="command" value="login" />
                    <fmt:message key="message.login" bundle="${messages}"/><br/>
                    <input type="text" required minlength="3" maxlength="30" name="login" pattern="[a-zA-Zа-яА-Я0-9]{3,30}" value=""/>

                    <br/><fmt:message key="message.password" bundle="${messages}"/><br/>
                    <input type="password" required minlength="3" maxlength="20" name="password" pattern="[a-zA-Zа-яА-Я0-9]{3,20}" value=""/>
                    <br/>

                    <input class="btn btn-primary" type="submit" value=<fmt:message key="message.enter" bundle="${messages}"/>>
                </form><hr/>
            </div>
        <c:if test="${errorLoginPassMessage != null}" >
            <fmt:message key="message.loginerror" bundle="${messages}"/>
        </c:if>
        ${wrongAction}
        ${nullPage}

    </div>