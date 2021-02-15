<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ByNameASC" value="ByName(a-z)"/>
<c:set var="ByNameDESC" value="ByName(z-a)"/>
<c:set var="ByPrice" value="ByPrice"/>

<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="tariff.list"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container pt-3">
    <h1 class="text-center"><fmt:message key="tariff.list"/></h1><br>
    <form action="${pageContext.request.contextPath}/main">
        <div class="row">
            <div class="col-sm-6">
                <h4>Service:</h4>
            </div>
            <div class="col-sm-6">
                <h4>Sort:</h4>
            </div>

        </div>
        <div class="row">
            <div class="col-sm-6">
                <select name="serviceId" class="custom-select mb-3">

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
            <div class="col-sm-6">
                <select name="sort" class="custom-select">
                    <c:choose>
                        <c:when test="${sort.equals(ByNameASC)}">
                            <option selected value="${sort}">${sort}</option>
                            <option value="ByName(z-a)">ByName(z-a)</option>
                            <option value="ByPrice">ByPrice</option>
                        </c:when>
                        <c:when test="${sort.equals(ByNameDESC)}">
                            <option selected value="${sort}">${sort}</option>
                            <option value="ByName(a-z)">ByName(a-z)</option>
                            <option value="ByPrice">ByPrice</option>
                        </c:when>
                        <c:when test="${sort.equals(ByPrice)}">
                            <option selected value="${sort}">${sort}</option>
                            <option value="ByName(z-a)">ByName(z-a)</option>
                            <option value="ByName(a-z)">ByName(a-z)</option>
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>

                </select>

            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <button type="submit" class="btn btn-primary" style="width: 100%;">Show Tariffs</button>
            </div>
        </div>
    </form>
    <br>
    <table class="table table-striped" id="tarifsTable">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Service</th>
        </thead>
        <tbody>
        <c:forEach var="tariff" items="${tariffList}">
            <tr class="clickable-row">
                <td>${tariff.getName()}</td>
                <td>${tariff.getDescription()}</td>
                <td>${tariff.getPrice()}</td>
                <td>${tariff.getService().getName()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>