<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>This is error page</h2>
    Request from ${pageContext.errorData.requestURI}
<br/>
    Servlet name ${pageContext.errorData.servletName}
<br/>
    Status code ${pageContext.errorData.statusCode}
<br/>
    Exception ${pageContext.exception}
<br/>
    Message from exception ${pageContext.exception.message}
</body>
</html>
