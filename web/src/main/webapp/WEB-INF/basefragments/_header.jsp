<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags"%>


<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename = "messages" var = "messages"/>
         <div style="background: #E0E0E0; height: 65px; padding: 5px;">
           <div style="float: left">
              <h1><fmt:message key="message.optional.classes" bundle="${messages}"/></h1>
           </div>

             <div style="float: right; padding: 10px; text-align: right;">
               <ul class="my">
                   <li class="my"><a href="${pageContext.request.contextPath}/controller?command=lang&lang=en" >English</a></li>
                   <li class="my"><a href="${pageContext.request.contextPath}/controller?command=lang&lang=ru" >Русский</a></li>
               </ul>
           </div>

         </div>
                   <h6 align="right">
                       <c:if test="${person.role == 'STUDENT'}">
                           <fmt:message key="message.student" bundle="${messages}"/> :
                       </c:if>
                       <c:if test="${person.role == 'TUTOR'}">
                           <fmt:message key="message.tutor" bundle="${messages}"/> :
                       </c:if>
                        <ctg:person/>
                   </h6>

