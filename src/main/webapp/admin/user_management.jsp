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
<div class="container my-2">
    <h1><fmt:message key="users.list"/></h1>
    <a href="${pageContext.request.contextPath}/registration" class="btn btn-primary btn-sm mb-3">
        <fmt:message key="user.add"/></a>

    <table boarder="1" class="table table-striped table-responsive-md">
        <thead>
        <tr>
            <th>
                <a th:href="@{'/page/' + ${currentPage} + '?sortField=firstname&sortOrder=' + ${reverseSortOrder}}"
                   th:text="#{user.firstname}"></a>
            </th>
            <th>
                <a th:href="@{'/page/' + ${currentPage} + '?sortField=lastname&sortOrder=' + ${reverseSortOrder}}"
                   th:text="#{user.lastname}"></a>
            </th>
            <th>
                <a th:href="@{'/page/' + ${currentPage} + '?sortField=email&sortOrder=' + ${reverseSortOrder}}"
                   th:text="#{user.email}"></a>
            </th>
            <th th:text="#{user.actions}"></th>
        </tr>
        </thead>
        <tbody>
        <tr th:each="user : ${listUsers}">
            <td th:text="${user.firstname}"></td>
            <td th:text="${user.lastname}"></td>
            <td th:text="${user.email}"></td>
            <td>
                <a th:href="@{/deleteUser/{id}(id=${user.id})}" class="btn btn-danger" th:text="#{user.delete}"></a>
            </td>
        </tr>
        </tbody>
    </table>
    <div th:if="${totalPages > 1}">
        <div class="row col-sm-10">
            <div class="col-sm-2" th:text="#{page.rows}">
                : [[${totalItems}]]
            </div>
            <div class="col-sm-1">
                    <span th:each="i: ${#numbers.sequence(1, totalPages)}">
      <a th:if="${currentPage != i}"
         th:href="@{'/page/' + ${i}+ '?sortField=' + ${sortField} + '&sortOrder=' + ${sortOrder}}">[[${i}]]</a>
      <span th:unless="${currentPage != i}">[[${i}]]</span> &nbsp; &nbsp;
                    </span>
            </div>
            <div class="col-sm-1">
                <a th:if="${currentPage < totalPages}"
                   th:href="@{'/page/' + ${currentPage + 1}+ '?sortField=' + ${sortField} + '&sortOrder=' + ${sortOrder}}"
                   th:text="#{page.next}"></a>
                <span th:unless="${currentPage < totalPages}" th:text="#{page.next}"></span>
            </div>

            <div class="col-sm-1">
                <a th:if="${currentPage < totalPages}"
                   th:href="@{'/page/' + ${totalPages}+ '?sortField=' + ${sortField} + '&sortOrder=' + ${sortOrder}}"
                   th:text="#{page.last}"></a>
                <span th:unless="${currentPage < totalPages}" th:text="#{page.last}"></span>
            </div>
        </div>
    </div>
</div>
</body>
</html>
