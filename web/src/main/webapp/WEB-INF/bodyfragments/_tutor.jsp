<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename = "messages" var = "messages"/>

<div class="container">
        <h2><fmt:message key="message.tutor.page" bundle="${messages}"/></h2>
            <jsp:include page="_info.jsp"/>

<%--    CREATE COURSE --%>
<p>
   <c:if test="${processFlag == 'createcourse'}">
        <div class="form-group">
                <form name="courseForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="docreatecourses" />
                    <fmt:message key="message.course.name" bundle="${messages}"/>:<br/>
                        <textarea type="text" minlength="1" maxlength="99" aria-label="With textarea" class="form-control" rows="5" name="course"></textarea>
                        <br/>
                    <fmt:message key="message.description" bundle="${messages}"/>:<br/>
                        <textarea type="text" minlength="1" maxlength="999" aria-label="With textarea" class="form-control" rows="5" name="description"></textarea>
                        <br/>
                        <input type="submit" class="btn btn-primary" value=<fmt:message key="message.create.course" bundle="${messages}"/>/>
                </form>
        </div>
   </c:if>

<%--    EDIT COURSE--%>
    <c:if test="${processFlag == 'editCourse'}">
        <fmt:message key="message.edit.courses" bundle="${messages}"/>:
        <div class="form-group">
            <form name="courseForm" method="POST" action="controller">
                <input type="hidden" name="command" value="updatecourse" />
                <fmt:message key="message.course.name" bundle="${messages}"/>:<br/>
                <textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="course" maxlength="99">${editCourse.name}</textarea>
                <br/>
                <fmt:message key="message.description" bundle="${messages}"/>:<br/>
                <textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="description" maxlength="999">${editCourse.description}</textarea>
                <br/>

                <input type="submit" class="btn btn-primary" value=<fmt:message key="message.save" bundle="${messages}"/>>
            </form>
            <form name="courseForm" method="POST" action="controller">
                <input type="hidden" name="command" value="deletecourse" />
                <button type="submit" class="btn btn-primary"> <input type="submit" class="btn btn-primary" value=<fmt:message key="message.delete.course" bundle="${messages}"/>></button>
            </form>
        </div>
    </c:if>

<%--    VIEW COURSE--%>
    <c:if test="${processFlag == 'viewcourse'}">
        <p><fmt:message key="message.edit.courses" bundle="${messages}"/></p>
            <table border=1 cellpadding=5 >
                <tr>
                    <th><fmt:message key="message.course.number" bundle="${messages}"/></th>
                    <th><fmt:message key="message.course.name" bundle="${messages}"/></th>
                    <th><fmt:message key="message.description" bundle="${messages}"/></th>
                    <th><fmt:message key="message.edit.courses" bundle="${messages}"/></th>
                </tr>
                <c:forEach var="element" items="${list}">
                    <tr >
                        <td>${element.id}</td>
                        <td>
                                ${element.name}
                        </td>
                        <td>${element.description}</td>
                        <td align="center">
                            <div class="form-group">
                                <form name="editСourseForm" method="POST" action="controller">
                                    <input type="hidden" name="command" value="editcourse" />
                                    <input type="hidden" name="editid" value="${element.id}" />

                                    <input type="submit" class="btn btn-primary" value=<fmt:message key="message.edit" bundle="${messages}"/>>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
    </c:if>

<%--VIEW MARKS BY TUTOR--%>
    <c:if test="${processFlag == 'marks_view'}">
        <p><fmt:message key="message.edit.rate" bundle="${messages}"/></p>
        <table border=1 cellpadding=5>
            <tr>
                <th><fmt:message key="message.course.number" bundle="${messages}"/></th>
                <th><fmt:message key="message.course.name" bundle="${messages}"/></th>
                <th><fmt:message key="message.student" bundle="${messages}"/></th>
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
                    <td>${element.student.surname} ${element.student.name}</td>
                    <td><c:if test="${element.mark == 0}">---</c:if>
                        <c:if test="${element.mark != 0}">${element.mark}</c:if>
                    </td>
                    <td>${element.review}</td>
                    <td align="center">
                        <div class="form-group">
                            <form name="editMarkForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="edit_mark" />
                                <input type="hidden" name="markid" value="${element.id}" />

                                <input type="submit" class="btn btn-primary" value=<fmt:message key="message.edit" bundle="${messages}"/>>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

<%--    EDIT MARK BY TUTOR--%>
    <c:if test="${processFlag == 'edit_mark'}">
        <fmt:message key="message.grading" bundle="${messages}"/>
        <div class="form-group">
            <form name="courseForm" method="POST" action="controller">
                <input type="hidden" name="command" value="do_edit_mark" />

                <p><fmt:message key="message.course.number" bundle="${messages}"/>: ${mark.course.id}</p>
                <p><fmt:message key="message.course.name" bundle="${messages}"/>: ${mark.course.name}</p>
                <p><fmt:message key="message.student" bundle="${messages}"/>: ${mark.student.surname} ${mark.student.name}</p>
                <p><fmt:message key="message.rate" bundle="${messages}"/>:
                    <input type="number" min="1" max="10" name="mark" value="${mark.mark}"/></p>
                <p><fmt:message key="message.review" bundle="${messages}"/>:</p>
                <p><textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="review" maxlength="999">${mark.review}</textarea></p>
                <br/>
                <input type="submit" class="btn btn-primary" value=<fmt:message key="message.save" bundle="${messages}"/>>
            </form>
        </div>
    </c:if>

        </div>

<%--PAGINATION--%>
<jsp:include page="_pagination.jsp"/>