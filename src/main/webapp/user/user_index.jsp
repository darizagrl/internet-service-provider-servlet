<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="tariff.list"/></title>
</head>
<body>
<jsp:include page="user_header.jsp"/>
<div class="container pt-3">
    <a href="user_index/export/pdf" class="btn btn-info col-4"><fmt:message key="tariff.export"/></a>
</div>
<div class="container pt-3">
    <h1 class="text-center"><fmt:message key="tariff.list"/></h1><br>
    <form action="${pageContext.request.contextPath}/user/user_index">
        <div class="row">
            <div class="col-sm-4">
                <h4><fmt:message key="service"/></h4>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-4">
                <select name="service_id" class="form-control">

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
                <button type="submit" class="btn btn-info col-4"><fmt:message key="show"/></button>
            </div>
        </div>
    </form>
    <hr>
    <c:if test="${not empty message}">
        <div class="alert alert-info">
            <c:out value="${message}"/>
        </div>
    </c:if>
    <table class="table table-striped" id="tariffsTable">
        <thead>
        <tr>
            <th>
                <a href="${pageContext.request.contextPath}/user/user_index?service_id=${serviceAttr.getId()}&page=${currentPage}&sort_field=name&sort_order=${reverseSortOrder}"><fmt:message
                        key="tariff.name"/></a></th>
            <th><fmt:message key="tariff.description"/></th>
            <th>
                <a href="${pageContext.request.contextPath}/user/user_index?service_id=${serviceAttr.getId()}&page=${currentPage}&sort_field=price&sort_order=${reverseSortOrder}"><fmt:message
                        key="tariff.price"/></a></th>
            <th><fmt:message key="service.type"/></th>
        </thead>
        <tbody>
        <c:forEach var="tariff" items="${tariffList}">
            <tr class="clickable-row">
                <td>${tariff.getName()}</td>
                <td>${tariff.getDescription()}</td>
                <td>${tariff.getPrice()}</td>
                <td>${tariff.getService().getName()}</td>
                <td style="text-align: right;">
                    <form method="post" action='<c:url value="/user/subscribe" />' style="display:inline;">
                        <input type="hidden" name="tariffId" value="${tariff.getId()}">
                        <input type="submit" class="btn btn-success" value="Subscribe">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav aria-label="Navigation for tariffs">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="user_index?service_id=${serviceAttr.getId()}&page=${currentPage-1}">Previous</a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="user_index?service_id=${serviceAttr.getId()}&page=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="user_index?service_id=${serviceAttr.getId()}&page=${currentPage+1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
</body>
</html>