<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="tariff.list"/></title>
    <meta charset="utf-8">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container pt-3">
    <form action="${pageContext.request.contextPath}/app/index">
        <table class="table table-striped" id="tariffsTable">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Type</th>
            </thead>
            <tbody>
            <c:forEach var="tariff" items="${tariffList}">
                <tr class="clickable-row">
                    <td>${tariff.getName()}</td>
                    <td>${tariff.getDescription()}</td>
                    <td>${tariff.getPrice()}</td>
                    <td>${tariff.getType()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>

<br/>
<a class="no-background" href="${pageContext.request.contextPath}/app/login">Login</a>
<br/>
<a class="no-background" href="${pageContext.request.contextPath}/app/registration">Registration form</a>
<br>
<a class="no-background" href="${pageContext.request.contextPath}/app/exception">Exception</a>
</body>
</html>