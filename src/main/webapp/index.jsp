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
    <title><fmt:message key="tariff.list"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container pt-3">
    <h1 class="text-center"><fmt:message key="tariff.list"/></h1><br>
    <form action="${pageContext.request.contextPath}/index">
        <div class="row">
            <div class="col-sm-4">
                <h4><fmt:message key="service"/></h4>
            </div>
            <div class="col-sm-4">
                <h4>Sort:</h4>
            </div>

        </div>
        <div class="row">
            <div class="col-sm-4">
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
            <div class="col-sm-4">
                <select name="sort" class="custom-select">
                    <c:choose>
                        <c:when test="${sort.equals(ByNameASC)}">
                            <option selected value="${sort}">${sort}</option>
                            <option value="By Name(z-a)">By Name(z-a)</option>
                            <option value="By Price(asc)">By Price(asc)</option>
                            <option value="By Price(desc)">By Price(desc)</option>
                        </c:when>
                        <c:when test="${sort.equals(ByNameDESC)}">
                            <option selected value="${sort}">${sort}</option>
                            <option value="By Name(a-z)">By Name(a-z)</option>
                            <option value="By Price(asc)">By Price(asc)</option>
                            <option value="By Price(desc)">By Price(desc)</option>
                        </c:when>
                        <c:when test="${sort.equals(ByPriceASC)}">
                            <option selected value="${sort}">${sort}</option>
                            <option value="By Name(z-a)">By Name(z-a)</option>
                            <option value="By Name(a-z)">By Name(a-z)</option>
                            <option value="By Price(desc)">By Price(desc)</option>
                        </c:when>
                        <c:when test="${sort.equals(ByPriceDESC)}">
                            <option selected value="${sort}">${sort}</option>
                            <option value="By Name(z-a)">By Name(z-a)</option>
                            <option value="By Name(a-z)">By Name(a-z)</option>
                            <option value="By Price(asc)">By Price(asc)</option>
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>

                </select>
            </div>
            <div class="col-sm-4">
                <button type="submit" class="btn btn-info col-4"><fmt:message key="show"/></button>
            </div>
        </div>
    </form>
    <hr>
    <table class="table table-striped" id="tariffsTable">
        <thead>
        <tr>
            <th><fmt:message key="tariff.name"/></th>
            <th><fmt:message key="tariff.description"/></th>
            <th><fmt:message key="tariff.price"/></th>
            <th><fmt:message key="service.type"/></th>
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