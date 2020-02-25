<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename = "messages" var = "messages"/>

<div class="container">
        <c:if test="${(userRole == 'TUTOR')}">
                <h2><fmt:message key="message.student.courses" bundle="${messages}"/></h2>
        </c:if>
        <c:if test="${(userRole == 'STUDENT')}">
                <h2><fmt:message key="message.student.page" bundle="${messages}"/></h2>
        </c:if>
        <jsp:include page="_info.jsp"/>
<p>
<%--        VIEW COURSES--%>
        <c:if test="${processFlag == 'viewcourse' or processFlag == 'allcourses'}">
                <c:if test="${(userRole == 'STUDENT')}">
                        <fmt:message key="message.enroll.course" bundle="${messages}"/>
                </c:if>
        <p/>
        <table border=1 cellpadding=5>
                <tr>
                        <th><fmt:message key="message.course.number" bundle="${messages}"/></th>
                        <th><fmt:message key="message.course.name" bundle="${messages}"/></th>
                        <th><fmt:message key="message.description" bundle="${messages}"/></th>
                        <th><fmt:message key="message.tutor" bundle="${messages}"/></th>
                        <c:if test="${(userRole == 'STUDENT')}">
                                <th><fmt:message key="message.enroll" bundle="${messages}"/></th>
                        </c:if>
                </tr>
                <c:forEach var="element" items="${list}">
                        <tr>
                                <td>${element.id}</td>
                                <td>
                                                ${element.name}
                                </td>
                                <td>${element.description}</td>
                                <td>${element.tutor.surname} ${element.tutor.name}</td>
                                <c:if test="${(userRole == 'STUDENT')}">
                                <td>
                                        <div class="form-group">
                                                <form name="enrollCourseForm" method="POST" action="controller">
                                                        <input type="hidden" name="command" value="addmark" />
                                                        <input type="hidden" name="courseid" value="${element.id}" />
                                                        <input class="btn btn-primary" type="submit" value=<fmt:message key="message.enroll" bundle="${messages}"/>>
                                                </form>
                                        </div>
                                </td>
                                </c:if>
                        </tr>
                </c:forEach>
        </table>
        </c:if>

        <c:if test="${processFlag == 'addmark'}">
                <div class="form-group">
                        <form name="courseForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="doaddmark" />
                                <p><fmt:message key="message.course.name" bundle="${messages}"/>:<br/></p>
                                <p>${course.name}</p>
                                <br/>
                                <p><fmt:message key="message.description" bundle="${messages}"/>:<br/></p>
                                <p>${course.description}</p>
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
                                <p>${course.name}</p>
                                <br/>
                                <p><fmt:message key="message.description" bundle="${messages}"/>:<br/></p>
                                <p>${course.description}</p>
                                <br/>
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
                                <th><fmt:message key="message.description" bundle="${messages}"/></th>
                                <th><fmt:message key="message.tutor" bundle="${messages}"/></th>
                                <th><fmt:message key="message.rate" bundle="${messages}"/></th>
                                <th><fmt:message key="message.review" bundle="${messages}"/></th>
                                <th><fmt:message key="message.edit" bundle="${messages}"/></th>
                        </tr>
                        <c:forEach var="element" items="${list}">
                                <tr>
                                        <td>${element.course.id}</td>
                                        <td>
                                                        ${element.course.name}
                                        </td>
                                        <td>${element.course.description}</td>
                                        <td>${element.course.tutor.surname} ${element.course.tutor.name}</td>
                                        <td>
                                                <c:if test="${element.mark==0}"> --- </c:if>
                                                <c:if test="${element.mark!=0}">${element.mark}</c:if>
                                        </td>
                                        <td>${element.review}</td>
                                        <td>
                                                <div class="form-group">
                                                        <form name="myCourseStudentForm" method="POST" action="controller">
                                                                <input type="hidden" name="command" value="delmark" />
                                                                <input type="hidden" name="markid" value="${element.id}" />
                                                                <input class="btn btn-primary" type="submit" value=<fmt:message key="message.edit" bundle="${messages}"/>>
                                                        </form>
                                                </div>
                                        </td>
                                </tr>
                        </c:forEach>
                </table
        </c:if>
</p>

        </div>

<%--PAGINATION--%>

<jsp:include page="_pagination.jsp"/>