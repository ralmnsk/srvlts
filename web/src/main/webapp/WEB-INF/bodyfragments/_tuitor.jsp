<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container">
        <h2>Страница Преподавателя</h2>
<p>
   <c:if test="${processFlag == 'createcourse'}">
        <div class="form-group">
                <form name="courseForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="docreatecourses" />
                        Название курса:<br/>
<%--                        <input type="text" name="course" value=""/>--%>
                        <textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="course"></textarea>
                        <br/>
                        <input type="submit" value="Создать курс"/>
                </form>
        </div>
   </c:if>

    <c:if test="${processFlag == 'editcourse'}">
        Редактирование курсов:
        <div class="form-group">
            <form name="courseForm" method="POST" action="controller">
                <input type="hidden" name="command" value="updatecourse" />
                Название курса:<br/>
<%--                <input type="text" name="course" value="${editCourse.name}"/>--%>
                <textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="course">${editCourse.name}</textarea>
                <br/>
                <input type="submit" class="btn btn-primary" value="Сохранить"/>
            </form>
            <form name="courseForm" method="POST" action="controller">
                <input type="hidden" name="command" value="deletecourse" />
                <button type="submit" class="btn btn-primary"> Удалить! </button>
            </form>
        </div>
    </c:if>

    <c:if test="${processFlag == 'viewcourse'}">
        <p>Для редактирования курса -кликните на него</p>
            <table border=1 cellpadding=5>
                <tr>
                    <th>Номер</th>
                    <th>Название курсов</th>
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
        <p>Для редактирования отметки - кликните название курсов</p>
        <table border=1 cellpadding=5>
            <tr>
                <th>Номер</th>
                <th>Название курсов</th>
                <th> ФИО Студента </th>
                <th> Оценка</th>
                <th> Ревью </th>
            </tr>
            <c:forEach var="element" items="${list}">
                <tr>
                    <td>${element.course.id}</td>
                    <td><a href="${pageContext.request.contextPath}/controller?command=edit_mark&markid=${element.id}"> ${element.course.name}</a></td>
                    <td>${element.student.surname} ${element.student.name}</td>
                    <td>${element.mark}</td>
                    <td>${element.review}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${processFlag == 'edit_mark'}">
        Выставление оценки:
        <div class="form-group">
            <form name="courseForm" method="POST" action="controller">
                <input type="hidden" name="command" value="do_edit_mark" />
<%--                <input type="hidden" name="mark" value="${mark}" />--%>
                    <%--                <input type="text" name="course" value="${editCourse.name}"/>--%>
                <p>Номер: ${mark.course.id}</p>
                <p>Название курса: ${mark.course.name}</p>
                <p>ФИО Студента: ${mark.student.surname} ${mark.student.name}</p>
                <p>Оценка: <input type="text" name="mark" value="${mark.mark}"/></p>
                <p>Ревью:</p>
                <p><textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="review">${mark.review}</textarea></p>
                <br/>
                <input type="submit" class="btn btn-primary" value="Сохранить"/>
            </form>
        </div>
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