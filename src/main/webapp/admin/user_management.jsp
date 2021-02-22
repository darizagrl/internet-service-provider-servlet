<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="users.list"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="admin_header.jsp"/>
<div class="container pt-3">
    <h2><fmt:message key="users.list"/></h2>
    <div class="btn-group">
        <a href="${pageContext.request.contextPath}/admin/registration" class="btn btn-success" role="button">Create
            User</a>
        <a href="${pageContext.request.contextPath}/admin/users" class="btn btn-primary" role="button">Users</a>
        <a href="${pageContext.request.contextPath}/admin/admins" class="btn btn-primary"
           role="button">Administrators</a>
    </div>
    <br><br>
    <table class="table table-striped" id="usersTable">
        <thead>
        <tr>
            <th><fmt:message key="user.firstname"/></th>
            <th><fmt:message key="user.lastname"/></th>
            <th><fmt:message key="user.email"/></th>
            <th><fmt:message key="user.actions"/></th>
            <th></th>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}">
            <tr class="clickable-row">
                <td>${user.getFirstname()}</td>
                <td>${user.getLastname()}</td>
                <td>${user.getEmail()}</td>
                <td>
                    <form method="post" action='<c:url value="/admin/user_delete" />' style="display:inline;">
                        <input type="hidden" name="userId" value="${user.getId()}">
                        <input type="submit" class="btn btn-danger" value="<fmt:message key="user.delete"/>">
                    </form>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>