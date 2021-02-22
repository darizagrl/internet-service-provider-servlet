<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ByNameASC" value="By Name(a-z)"/>
<c:set var="ByNameDESC" value="By Name(z-a)"/>
<c:set var="ByPriceASC" value="By Price(asc)"/>
<c:set var="ByPriceDESC" value="By Price(desc)"/>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="service.type"/></title>
</head>
<body>
<jsp:include page="admin_header.jsp"/>
<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/admin/service_add">
        <h2 class="text-center">Create service</h2>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="<fmt:message key="service.type"/>" name="name">
        </div>

        <c:if test="${not empty message}">
            <div class="alert alert-danger">
                <c:out value="${message}"/>
            </div>
        </c:if>

        <div class="form-group">
            <button type="submit" class="btn btn-info col-4"><fmt:message key="service.add"/></button>
        </div>
    </form>

</div>
</body>
</html>
