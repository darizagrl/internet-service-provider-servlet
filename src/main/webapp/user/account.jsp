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
    <title><fmt:message key="user.account"/></title>
</head>
<body>
<jsp:include page="user_header.jsp"/>
<div class="container pt-3">
    <h2><fmt:message key="user.account"/></h2>
    <div class="row justify-content-start">
        <div class="col-3">
            <label><fmt:message key="user.email"/></label>
            <h4>${user.getEmail()}</h4>
        </div>
        <div class="col-3">
            <label><fmt:message key="user.status"/></label>
            <c:choose>
                <c:when test="${user.isBlocked().equals(true)}">
                    <h4 style="color:red"><fmt:message key="user.blocked"/></h4>
                </c:when>
                <c:otherwise>
                    <h4 style="color:green"><fmt:message key="user.active"/></h4>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-3">
            <label><fmt:message key="user.balance"/></label>
            <h4>${user.getBalance()}</h4>
        </div>


        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-toggle="modal"
                data-target="#exampleModalCenter"><fmt:message key="account.replenishment"/>
        </button>

        <!-- Modal -->
        <form method="post" action='<c:url value="/user/account_replenishment" />' style="display:inline;">
            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">
                                <fmt:message key="account.replenishment"/>
                            </h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form method="post" action='<c:url value="/user/account_replenishment" />'
                                  style="display:inline;">
                                <p class="error-message"
                                   style="color:red">${message}
                                </p>
                                <div class="form-group">
                                    <label for="balance" class="control-label">
                                        <fmt:message key="user.balance"/>
                                    </label>
                                    <input id="balance" class="form-control" placeholder="0.0" value="${balance}"
                                           name="balance"/>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                <fmt:message key="close"/>
                            </button>
                            <button type="submit" class="btn btn-success">
                                <fmt:message key="save"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <br>

    <c:if test="${not empty message}">
        <div class="alert alert-danger">
            <c:out value="${message}"/>
        </div>
    </c:if>

    <br>
    <h2><fmt:message key="tariff.list"/></h2>
    <table class="table table-striped" id="tariffsTable">
        <thead>
        <tr>
            <th><fmt:message key="tariff.name"/></th>
            <th><fmt:message key="tariff.description"/></th>
            <th><fmt:message key="tariff.price"/></th>
            <th><fmt:message key="service.type"/></th>
        </thead>
        <tbody>
        <c:forEach var="tariff" items="${user.getTariffs()}">
            <tr class="clickable-row">
                <td>${tariff.getName()}</td>
                <td>${tariff.getDescription()}</td>
                <td>${tariff.getPrice()}</td>
                <td>${tariff.getService().getName()}</td>
                <td style="text-align: right;">
                    <form method="post" action='<c:url value="/user/unsubscribe" />' style="display:inline;">
                        <input type="hidden" name="tariffId" value="${tariff.getId()}">
                        <input type="submit" class="btn btn-danger" value=" <fmt:message key="tariff.unsubscribe"/>">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
