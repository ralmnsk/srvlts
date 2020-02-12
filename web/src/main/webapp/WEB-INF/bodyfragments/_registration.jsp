<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename = "messages" var = "messages"/>
        <div class="container">
            <h2><fmt:message key="message.please.register" bundle="${messages}"/></h2>
                <div class="form-group">
                    <form name="registrationForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="register" />
                    <br/><fmt:message key="message.login" bundle="${messages}"/><br/>
                    <input type="text" required minlength="3" maxlength="30" name="login" value=""/>
                    <br/><fmt:message key="message.password" bundle="${messages}"/><br/>
                    <input type="password" required minlength="3" maxlength="30" name="password" value=""/>
                    <br/><fmt:message key="message.surname" bundle="${messages}"/><br/>
                    <input type="text" required minlength="2" maxlength="30" name="surname" value=""/>
                    <br/><fmt:message key="message.name" bundle="${messages}"/><br/>
                    <input type="text" required minlength="2" maxlength="30" name="name" value=""/>
                    <br/>
                        <fmt:message key="message.choose" bundle="${messages}"/>
                    <br/>
                        <select name="selectType">
                            <option value="student"> <fmt:message key="message.student" bundle="${messages}"/>
                            </option>
                            <option value="tutor"> <fmt:message key="message.tutor" bundle="${messages}"/>
                            </option>
                        </select>
                    <br/>
                        <input class="btn btn-primary" type="submit" value=<fmt:message key="message.registration" bundle="${messages}"/>>
                    </form>
                </div>
            <hr/>
            <br/>
                ${errorRegistrationMessage}
                ${loginError}
                ${passwordError}
                ${surnameError}
                ${nameError}

            <br/>

        </div>

