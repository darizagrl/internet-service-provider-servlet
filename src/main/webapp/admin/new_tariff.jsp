<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="tariff.management"/></title>
</head>
<body>
<jsp:include page="admin_header.jsp"/>
<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/admin/new_tariff">
        <h2 class="text-center"><fmt:message key="tariff.management"/></h2>
        <div class="form-group">
            <label for="name" class="control-label"><fmt:message key="tariff.name"/></label>
            <input type="text" id="name" class="form-control" placeholder="<fmt:message key="tariff.name"/>"
                   name="name"/>
        </div>
        <div class="form-group">
            <label for="description" class="control-label"><fmt:message key="tariff.description"/></label>
            <input type="text" id="description" class="form-control"
                   placeholder="<fmt:message key="tariff.description"/>" name="description"/>
        </div>
        <div class="form-group">
            <label for="price" class="control-label"><fmt:message key="tariff.price"/></label>
            <input id="price" class="form-control" placeholder="0.0" name="price"/>
        </div>
        <div class="form-group">
            <select name="serviceId" class="form-select" aria-label="Default select example" >
                <c:forEach var="service" items="${serviceList}">
                    <c:choose>
                        <c:when test="${serviceAttr.getId() == service.getId()}">
                            <option value="${service.getId()}" selected>${serviceAttr.getName()}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${service.getId()}">${service.getName()}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-info col-4"><fmt:message key="tariff.save"/></button>
        </div>
    </form>
</div>
</body>
</html>
