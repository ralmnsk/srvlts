<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename = "messages" var = "messages"/>

<div class="container">
        <h2><fmt:message key="message.tutor.page" bundle="${messages}"/></h2>
            ${courseExists}
            <jsp:include page="_info.jsp"/>
<p>
   <c:if test="${processFlag == 'createcourse'}">
        <div class="form-group">
                <form name="courseForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="docreatecourses" />
                    <fmt:message key="message.course.name" bundle="${messages}"/>:<br/>
                        <textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="course"></textarea>
                        <br/>
                        <input type="submit" class="btn btn-primary" value=<fmt:message key="message.create.course" bundle="${messages}"/>/>
                </form>
        </div>
   </c:if>

    <c:if test="${processFlag == 'editCourse'}">
        <fmt:message key="message.edit.courses" bundle="${messages}"/>:
        <div class="form-group">
            <form name="courseForm" method="POST" action="controller">
                <input type="hidden" name="command" value="updatecourse" />
                <fmt:message key="message.course.name" bundle="${messages}"/>:<br/>

                <textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="course">${editCourse.name}</textarea>
                <br/>
                <input type="submit" class="btn btn-primary" value=<fmt:message key="message.save" bundle="${messages}"/>>
            </form>
            <form name="courseForm" method="POST" action="controller">
                <input type="hidden" name="command" value="deletecourse" />
                <button type="submit" class="btn btn-primary"> <input type="submit" class="btn btn-primary" value=<fmt:message key="message.delete.course" bundle="${messages}"/>></button>
            </form>
        </div>
    </c:if>

    <c:if test="${processFlag == 'viewcourse'}">
        <p><fmt:message key="message.edit.courses" bundle="${messages}"/></p>
            <table border=1 cellpadding=5>
                <tr>
                    <th><fmt:message key="message.course.number" bundle="${messages}"/></th>
                    <th><fmt:message key="message.course.name" bundle="${messages}"/></th>
                </tr>
                <c:forEach var="element" items="${list}">
                    <tr>
                        <td>${element.id}</td>
                        <td><a href="${pageContext.request.contextPath}/controller?command=editcourse&editid=${element.id}"> ${element.name}</a></td>
                    </tr>
                </c:forEach>
            </table>
    </c:if>

    <c:if test="${processFlag == 'marks_view'}">
        <p><fmt:message key="message.edit.rate" bundle="${messages}"/></p>
        <table border=1 cellpadding=5>
            <tr>
                <th><fmt:message key="message.course.number" bundle="${messages}"/></th>
                <th><fmt:message key="message.course.name" bundle="${messages}"/></th>
                <th><fmt:message key="message.student" bundle="${messages}"/></th>
                <th><fmt:message key="message.rate" bundle="${messages}"/></th>
                <th><fmt:message key="message.review" bundle="${messages}"/></th>
            </tr>
            <c:forEach var="element" items="${list}">
                <tr>
                    <td>${element.course.id}</td>
                    <td><a href="${pageContext.request.contextPath}/controller?command=edit_mark&markid=${element.id}"> ${element.course.name}</a></td>
                    <td>${element.student.surname} ${element.student.name}</td>
                    <td><c:if test="${element.mark == 0}">---</c:if>
                        <c:if test="${element.mark != 0}">${element.mark}</c:if>
                    </td>
                    <td>${element.review}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

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
                <p><textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="review">${mark.review}</textarea></p>
                <br/>
                <input type="submit" class="btn btn-primary" value=<fmt:message key="message.save" bundle="${messages}"/>>
            </form>
        </div>
    </c:if>

</p>
        </div>

<%--PAGINATION--%>

<c:if test="${processFlag == 'viewcourse' or processFlag == 'marks_view'}">
    <c:if test="${processFlag == 'viewcourse'}">
        <c:set var="process" value="viewcourse"/>
        <c:set var="pageNumber" value="${pageCourseNumber}"/>
    </c:if>
    <c:if test="${processFlag == 'marks_view'}">
        <c:set var="process" value="marks"/>
        <c:set var="pageNumber" value="${pageMarkNumber}"/>
    </c:if>
        <nav aria-label="Page navigation example">
          <ul autofocus class="pagination justify-content-center" vertical-align="bottom">

            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${process}&move=previous">Previous</a></li>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${process}&move=next">Next</a></li>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${process}&scale=10">10</a></li>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${process}&scale=20">20</a></li>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${process}&scale=50">50</a></li>
            <li class="page-item"><a class="page-link">Page: ${pageNumber}</a></li>
            <li class="page-item"><a class="page-link">Total: ${pagesCount}</a></li>

          </ul>
        </nav>

</c:if>