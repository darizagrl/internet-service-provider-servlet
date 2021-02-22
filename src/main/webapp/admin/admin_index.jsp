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
<jsp:include page="admin_header.jsp"/>
<div class="container pt-3">
    <h1 class="text-center"><fmt:message key="tariff.list"/></h1>
    <div>
        <span>
            <a type="submit" class="btn btn-info col-4" href="${pageContext.request.contextPath}/admin/tariff_add">
                <fmt:message key="tariff.add"/></a>
        </span>
    </div>
    <div class="btn-group">
        <a href="${pageContext.request.contextPath}/admin/service_add" class="btn btn-success" role="button">Create
            Service</a>
    </div>
    <form action="${pageContext.request.contextPath}/admin/">
        <div class="row">
            <div class="col-md-3">
                <h4>Service:</h4>
            </div>
            <div class="col-md-3">
                <h4>Sort:</h4>
            </div>

        </div>
        <div class="row">
            <div class="col-md-3">
                <select name="serviceId" class="custom-select md-5">

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
            <div class="col-md-3">
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
            <div class="col-md-3">
                <button type="submit" class="btn btn-success col-3"><fmt:message key="show"/></button>
            </div>
        </div>
    </form>
    <hr>
    <div>
        <table class="table table-striped" id="tariffsTable">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
            </thead>
            <tbody>
            <c:forEach var="tariff" items="${tariffList}">
                <tr class="clickable-row">
                    <td>${tariff.getName()}</td>
                    <td>${tariff.getDescription()}</td>
                    <td>${tariff.getPrice()}</td>
                    <td style="text-align: right;">
                        <form method="post" action='<c:url value="/admin/tariff_edit" />' style="display:inline;">
                            <input type="hidden" name="tariffId" value="${tariff.getId()}">
                            <input type="submit" class="btn btn-primary" value="Edit">
                        </form>
                    </td>
                    <td>
                            <button type="button" class="btn btn-danger" data-toggle="modal"
                                    data-target="#exampleModalCenter">
                                <fmt:message key="user.delete"/>
                            </button>

                            <!-- Modal -->
                            <form method="post" action='<c:url value="/admin/tariff_delete" />' style="display:inline;">
                                <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
                                     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLongTitle">
                                                    <fmt:message key="user.delete"/></h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <h5>
                                                        Are you sure?
                                                    </h5>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                    <fmt:message key="close"/>
                                                </button>
                                                <form method="post" action='<c:url value="/admin/tariff_delete" />'
                                                      style="display:inline;">
                                                    <input type="hidden" name="tariffId" value="${tariff.getId()}">
                                                    <input type="submit" class="btn btn-danger"
                                                           value="<fmt:message key="user.delete"/>">
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>