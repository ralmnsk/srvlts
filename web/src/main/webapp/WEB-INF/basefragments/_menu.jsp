<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename = "messages" var = "messages"/>

<div>

   <ul class="my">
           <li class="my"><a href="?lang=en">English</a></li>
           <li class="my"><a href="?lang=ru">Русский</a></li>
           <li><a href="${pageContext.request.contextPath}/"><fmt:message key="message.main.page" bundle="${messages}"/></a></li>

            <c:if test="${(userRole == 'STUDENT')}">
                <br/>
                <li><a href="${pageContext.request.contextPath}/controller?command=student"><fmt:message key="message.student.page" bundle="${messages}"/></a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=allcourses"><fmt:message key="message.student.courses" bundle="${messages}"/></a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=viewmark"><fmt:message key="message.yours.courses" bundle="${messages}"/></a></li>
            </c:if>

            <c:if test="${(userRole == 'TUTOR')}">
                <br/>
                <li><a href="${pageContext.request.contextPath}/controller?command=tutor"><fmt:message key="message.tutor.page" bundle="${messages}"/></a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=createcourse"><fmt:message key="message.create.course" bundle="${messages}"/></a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=viewcourse"><fmt:message key="message.see.yours.courses" bundle="${messages}"/></a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=marks"><fmt:message key="message.rate" bundle="${messages}"/></a></li>
            </c:if>

           <c:if test="${(userRole == 'STUDENT') or (userRole == 'TUTOR')}">
               <br/>
            <li><a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="message.logout" bundle="${messages}"/></a></li>
           </c:if>

   </ul>

</div>