<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--<c:set var="language"--%>
<%--       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"--%>
<%--       scope="session" />--%>
<%--<fmt:setLocale value="${language}" />--%>
<%--${language}--%>
<%--<fmt:setBundle basename="messages" />--%>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename = "messages" var = "messages"/>
        <div class="container">
            <h2><fmt:message key="message.please.register" bundle="${messages}"/></h2>
                <div class="form-group">
                    <form name="registrationForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="register" />
                    <br/><fmt:message key="message.login" bundle="${messages}"/><br/>
                    <input type="text" name="login" value=""/>
                    <br/><fmt:message key="message.password" bundle="${messages}"/><br/>
                    <input type="password" name="password" value=""/>
                    <br/><fmt:message key="message.surname" bundle="${messages}"/><br/>
                    <input type="text" name="surname" value=""/>
                    <br/><fmt:message key="message.name" bundle="${messages}"/><br/>
                    <input type="text" name="name" value=""/>
                    <br/>
                        <fmt:message key="message.choose" bundle="${messages}"/>
                    <br/>
                        <select name="selectType">
                            <option value="student"> <fmt:message key="message.student" bundle="${messages}"/>
                            </option>
                            <option value="tuitor"> <fmt:message key="message.tutor" bundle="${messages}"/>
                            </option>
                        </select>
                    <br/>
                        <input class="btn btn-primary" type="submit" value=<fmt:message key="message.registration" bundle="${messages}"/>>
                    </form>
                </div>
            <hr/>
            </br>
<%--            <fmt:message key="message.student" bundle="${messages}"/>--%>
<%--            request.encoding:<%=request.getCharacterEncoding()%><br/>--%>
<%--            response.encoding:<%=response.getCharacterEncoding()%><br/>--%>
            ${errorRegistrationMessage}
            </br>

        </div>

