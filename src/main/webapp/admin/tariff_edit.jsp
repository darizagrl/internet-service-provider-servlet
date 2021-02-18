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
    <form method="post" action="${pageContext.request.contextPath}/admin/tariff_edit">
        <h2 class="text-center"><fmt:message key="tariff.management"/></h2>
        <div class="form-group">
            <label for="newName" class="control-label"><fmt:message key="tariff.name"/></label>
            <input type="text" id="newName" class="form-control" value="${tariff.getName()}"
                   name="newName"/>
        </div>
        <div class="form-group">
            <label for="newDescription" class="control-label"><fmt:message key="tariff.description"/></label>
            <input type="text" id="newDescription" class="form-control"
                   value="${tariff.getDescription()}" name="newDescription"/>
        </div>
        <div class="form-group">
            <label for="newPrice" class="control-label"><fmt:message key="tariff.price"/></label>
            <input id="newPrice" class="form-control" value="${tariff.getPrice()}" name="newPrice"/>
        </div>
        <div class="form-group">
            <label for="serviceId"><fmt:message key="service.type"/></label>
            <select class="form-control" name="serviceId" id="serviceId">
                <c:forEach var="service" items="${serviceList}">
                    <c:choose>
                        <c:when test="${currentService.getId() == service.getId()}">
                            <option value="${service.getId()}" selected>${currentService.getName()}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${service.getId()}">${service.getName()}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <small style="color:red">${message}</small>
        <div class="form-group">
            <input type="hidden" name="tariffId" value="${tariff.getId()}">
            <button type="submit" class="btn btn-info col-4"><fmt:message key="save"/></button>
        </div>
    </form>

</div>
</body>
</html>
