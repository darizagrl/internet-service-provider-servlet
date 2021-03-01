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
<jsp:include page="admin_header.jsp"/>
<div class="container pt-3">
    <a href="admin_index/export/pdf" class="btn btn-info col-4"><fmt:message key="tariff.export"/></a>
</div>
<div class="container pt-3">
    <h1 class="text-center"><fmt:message key="tariff.list"/></h1>
    <div class="btn-group">
        <a type="submit" class="btn btn-info col-4" href="${pageContext.request.contextPath}/admin/tariff_add">
            <fmt:message key="tariff.add"/></a>
        <a href="${pageContext.request.contextPath}/admin/service_add" class="btn btn-success"
           role="button"><fmt:message key="service.add"/></a>
    </div>
    <form action="${pageContext.request.contextPath}/admin/">
        <div class="row">
            <div class="col-md-4">
                <h4><fmt:message key="service"/></h4>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
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
            <div class="col-md-4">
                <button type="submit" class="btn btn-success col-3"><fmt:message key="show"/></button>
            </div>
        </div>
    </form>
    <hr>
    <div>
        <table class="table table-striped" id="tariffsTable">
            <thead>
            <tr>
                <th>
                    <a href="${pageContext.request.contextPath}/admin/admin_index?service_id=${serviceAttr.getId()}&page=${currentPage}&sort_field=name&sort_order=${reverseSortOrder}"><fmt:message
                            key="tariff.name"/></a></th>
                <th><fmt:message key="tariff.description"/></th>
                <th>
                    <a href="${pageContext.request.contextPath}/admin/admin_index?service_id=${serviceAttr.getId()}&page=${currentPage}&sort_field=price&sort_order=${reverseSortOrder}"><fmt:message
                            key="tariff.price"/></a></th>
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
                                                <h3>
                                                    Are you sure?
                                                </h3>
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
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <nav aria-label="Navigation for tariffs">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li class="page-item"><a class="page-link"
                                             href="admin_index?service_id=${serviceAttr.getId()}&page=${currentPage-1}">Previous</a>
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
                                                     href="admin_index?service_id=${serviceAttr.getId()}&page=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item"><a class="page-link"
                                             href="admin_index?service_id=${serviceAttr.getId()}&page=${currentPage+1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>

    </div>
</div>
</body>
</html>