<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ex" uri="/WEB-INF/custom.tld" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>404</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<ex:Hello>
    <div class="container">
        <h1>Something went wrong!</h1>
        <h4>Sorry, we couldn't find the page you were looking for</h4>
        <div>
            <a href="${pageContext.request.contextPath}/index" class="btn btn-primary btn-sm mb-3"><fmt:message
                    key="home.page"/></a>
        </div>
    </div>
</ex:Hello>
</body>
</html>