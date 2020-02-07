<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename = "messages" var = "messages"/>

<div class="container">
        <h2><fmt:message key="message.student.page" bundle="${messages}"/></h2>
<p>
        <c:if test="${processFlag == 'viewcourse'}">
                        <fmt:message key="message.enroll.course" bundle="${messages}"/>

        <p/>
        <table border=1 cellpadding=5>
                <tr>
                        <th><fmt:message key="message.course.number" bundle="${messages}"/></th>
                        <th><fmt:message key="message.course.name" bundle="${messages}"/></th>
                        <th><fmt:message key="message.tutor" bundle="${messages}"/></th>
                </tr>
                <c:forEach var="element" items="${list}">
                        <tr>
                                <td>${element.id}</td>
                                <td><a href="${pageContext.request.contextPath}/controller?command=addmark&courseid=${element.id}"> ${element.name}</a></td>
                                <td>${element.tutor.surname} ${element.tutor.name}</td>
                        </tr>
                </c:forEach>
        </table>
        </c:if>

        <c:if test="${processFlag == 'addmark'}">
                <div class="form-group">
                        <form name="courseForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="doaddmark" />
                                <p><fmt:message key="message.course.name" bundle="${messages}"/>:<br/></p>
                                        <%--                        <input type="text" name="course" value=""/>--%>
                                <p>${course.name}</p>
                                <br/>
                                <input class="btn btn-primary" type="submit" value=<fmt:message key="message.enroll" bundle="${messages}"/>>
                        </form>
                </div>
        </c:if>

        <c:if test="${processFlag == 'delmark'}">
                <div class="form-group">
                        <form name="courseForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="dodelmark" />
                                <p><fmt:message key="message.course.name" bundle="${messages}"/>:<br/></p>

                                        <%--                        <input type="text" name="course" value=""/>--%>
                                <p>${course.name}</p>
                                <input class="btn btn-primary" type="submit" value=<fmt:message key="message.delete.course" bundle="${messages}"/>/>
                        </form>
                </div>
        </c:if>

        <c:if test="${processFlag == 'viewmark'}">
                <fmt:message key="message.enroll.course.you" bundle="${messages}"/>
                <br/>
                <fmt:message key="message.course.to.delete" bundle="${messages}"/>
                <p/>
                <table border=1 cellpadding=5>
                        <tr>
                                <th><fmt:message key="message.course.number" bundle="${messages}"/></th>
                                <th><fmt:message key="message.course.name" bundle="${messages}"/></th>
                                <th><fmt:message key="message.tutor" bundle="${messages}"/></th>
                                <th><fmt:message key="message.rate" bundle="${messages}"/></th>
                                <th><fmt:message key="message.review" bundle="${messages}"/></th>
                        </tr>
                        <c:forEach var="element" items="${list}">
                                <tr>
                                        <td>${element.course.id}</td>
                                        <td><a href="${pageContext.request.contextPath}/controller?command=delmark&markid=${element.id}"> ${element.course.name}</a></td>
                                        <td>${element.course.tutor.surname} ${element.course.tutor.name}</td>
                                        <td>
                                                <c:if test="${element.mark==0}">нет</c:if>
                                                <c:if test="${element.mark!=0}">${element.mark}</c:if>
                                        </td>
                                        <td>${element.review}</td>
                                </tr>
                        </c:forEach>
                </table>
        </c:if>
<%--        <a href="${pageContext.request.contextPath}/controller?command=tologin">Вход</a></br>--%>
<%--        <a href="${pageContext.request.contextPath}/controller?command=toregister">Регистрация</a></br>--%>
</p>
<%--            <table>--%>
<%--                <c:forEach var="entry" items="${map}">--%>
<%--                    <p><h4>${entry.key.nameNews}</h4>--%>
<%--                        </br>--%>
<%--                            ${entry.key.dataNews}<p>--%>
<%--                        </br>--%>
<%--                            Автор: ${entry.value.name}--%>
<%--                        </br>--%>
<%--                    Дата: <fmt:formatDate type="both" value="${entry.key.dateNews}"/></br>--%>

<%--                    <c:if test = "${(role == 'ROLE_USER')or(role == 'ROLE_ADMIN')}">--%>
<%--                        <form name="sendToDiscuss" method="POST" action="site/discuss">--%>
<%--                            <div class="form=group">--%>
<%--                                <input type="hidden" name="discussNewsId" value="${entry.key.idNews}">--%>
<%--                                <input class="btn btn-primary" type="submit" value="Перейти к обсуждению"/>--%>
<%--                            </div>--%>
<%--                        </form>--%>
<%--                    </c:if>--%>


<%--                    </p>--%>
<%--                    <hr/>--%>
<%--                </c:forEach>--%>
<%--            </table>--%>
        </div>



<%--        <nav aria-label="Page navigation example">--%>
<%--          <ul autofocus class="pagination justify-content-center">--%>

<%--            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/news?move=previous">Previous</a></li>--%>
<%--            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/news?move=next">Next</a></li>--%>
<%--            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/news?maxResults=5">5</a></li>--%>
<%--            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/news?maxResults=15">15</a></li>--%>
<%--            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/news?maxResults=50">50</a></li>--%>
<%--            <li class="page-item"><a class="page-link">Page: ${currentPage}</a></li>--%>
<%--            <li class="page-item"><a class="page-link">Total: ${pagesCount}</a></li>--%>

<%--          </ul>--%>
<%--        </nav>--%>