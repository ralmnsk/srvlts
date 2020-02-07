<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename = "messages" var = "messages"/>
         <div style="background: #E0E0E0; height: 55px; padding: 5px;">
           <div style="float: left">
              <h1><fmt:message key="message.optional.classes" bundle="${messages}"/></h1>
           </div>

           <div style="float: right; padding: 10px; text-align: right;">

           </div>

         </div>

