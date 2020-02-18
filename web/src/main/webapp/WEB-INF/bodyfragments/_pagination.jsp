<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename = "messages" var = "messages"/>

<%--PAGINATION--%>

<c:if test="${processFlag == 'viewcourse' or processFlag == 'marks_view' or processFlag == 'allcourses' or processFlag == 'viewmark'}">
<%--    TUTOR--%>
    <c:if test="${(userRole == 'TUTOR')}">
        <c:if test="${processFlag == 'viewcourse'}">
            <c:set var="process" value="viewcourse"/>
            <c:set var="pageNumber" value="${pageCourseNumber}"/>
        </c:if>
        <c:if test="${processFlag == 'marks_view'}">
            <c:set var="process" value="marks"/>
            <c:set var="pageNumber" value="${pageMarkNumber}"/>
        </c:if>
        <c:if test="${processFlag == 'allcourses'}">
            <c:set var="process" value="allcourses"/>
            <c:set var="pageNumber" value="${pageAllCoursesNumber}"/>
        </c:if>
    </c:if>
<%--STUDENT--%>
    <c:if test="${(userRole == 'STUDENT')}">
        <c:if test="${processFlag == 'viewcourse'}">
             <c:set var="process" value="allcourses"/>
             <c:set var="pageNumber" value="${pageAllCoursesNumber}"/>
        </c:if>
        <c:if test="${processFlag == 'viewmark'}">
             <c:set var="process" value="viewmark"/>
             <c:set var="pageNumber" value="${pageMarkStudentNumber}"/>
        </c:if>
        <c:if test="${processFlag == 'allcourses'}">
            <c:set var="process" value="allcourses"/>
            <c:set var="pageNumber" value="${pageAllCoursesNumber}"/>
        </c:if>

    </c:if>

        <nav aria-label="Page navigation example">
          <ul autofocus class="pagination justify-content-center" vertical-align="bottom">

            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${process}&pageNumber=1"> <fmt:message key="message.first" bundle="${messages}"/> </a></li>
<%--            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${process}&move=previous"> << </a></li>--%>
              <c:if test="${pageNumber >= 3}">
                  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${process}&pageNumber=${pageNumber - 2}"> ${pageNumber - 2} </a></li>
              </c:if>
              <c:if test="${pageNumber >= 2}">
                  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${process}&pageNumber=${pageNumber - 1}"> ${pageNumber - 1} </a></li>
              </c:if>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${process}&pageNumber=${pageNumber}">[ ${pageNumber} <c:if test="${pageNumber == null}">1</c:if>]</a></li>
              <c:if test="${pageNumber <= (pagesCount - 1)}">
                  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${process}&pageNumber=${pageNumber + 1}"> ${pageNumber + 1} </a></li>
              </c:if>
              <c:if test="${pageNumber <= (pagesCount - 2)}">
                  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${process}&pageNumber=${pageNumber + 2}"> ${pageNumber + 2} </a></li>
              </c:if>
<%--            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${process}&move=next"> >> </a></li>--%>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${process}&pageNumber=${pagesCount}"> <fmt:message key="message.last" bundle="${messages}"/> </a></li>

<%--SELECT SCALE--%>
              <select name="selectScale" onchange="if (this.value) window.location.href=this.value" >
                  <option value="${pageContext.request.contextPath}/controller?command=${process}&scale=10" <c:if test="${scale == 10 or scale == null}">selected</c:if> >
                      10
                  </option>
                  <option value="${pageContext.request.contextPath}/controller?command=${process}&scale=20" <c:if test="${scale == 20}">selected</c:if> >
                      20
                  </option>
                  <option value="${pageContext.request.contextPath}/controller?command=${process}&scale=50" <c:if test="${scale == 50}">selected</c:if> >
                      50
                  </option>
              </select>
            <li class="page-item"><a class="page-link"><fmt:message key="message.total.pages" bundle="${messages}"/> ${pagesCount}</a></li>

          </ul>
        </nav>

</c:if>