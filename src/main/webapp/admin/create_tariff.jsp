<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Internet Provider</title>
    <meta charset="utf-8">
</head>
<body>
<jsp:include page="admin_header.jsp"/>
<div class="registration-form">
    <form method="post" action="${pageContext.request.contextPath}/admin/createTariff">
        <h2 class="text-center">Create tariff</h2>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Enter name" name="name">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Enter description" name="description">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Enter price" name="price">
        </div>
        <div class="form-group">
            <select name="serviceId" class="custom-select mb-3">
                <c:forEach var="service" items="${serviceList}">
                    <c:choose>
                        <c:when test="${serviceAttr.getId() == service.getId()}">
                            <option  value="${service.getId()}" selected>${serviceAttr.getName()}</option>
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
            <button type="submit" class="btn btn-primary btn-block">Submit</button>
        </div>
    </form>

</div>
</body>
</html>

