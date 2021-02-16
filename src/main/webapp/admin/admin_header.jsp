<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<c:set var="val" value="ua"/>
<html  lang="${sessionScope.lang}">

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        <%@include file="/css/style.css" %>
    </style>
    <link  rel="stylesheet" type="text/css" href="/css/style.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/app/admin/adminMain">Internet Provider</a>
        </div>
        <ul class="navbar-nav">
            <li class="nav-item">
                <form class="form-inline" action="${pageContext.request.contextPath}/admin/services">
                    <button class="btn btn-primary" type="submit">Services</button>
                </form>
            </li>
            <li class="nav-item">
                <form class="form-inline" action="${pageContext.request.contextPath}/admin/adminMain">
                    <button class="btn btn-primary" type="submit">Tariffs</button>
                </form>
            </li>
            <li class="nav-item">
                <form class="form-inline" action="${pageContext.request.contextPath}/admin/users">
                    <button class="btn btn-primary" type="submit">Users</button>
                </form>
            </li>
            <li class="nav-item">
                <form class="form-inline" action="${pageContext.request.contextPath}/admin/adminProfile">
                    <button class="btn btn-primary" type="submit">My profile</button>
                </form>
            </li>
            <li class="nav-item">
                <form class="form-inline" action="${pageContext.request.contextPath}/admin/logout">
                    <button class="btn btn-primary" type="submit">Logout</button>
                </form>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    <c:choose>
                        <c:when test="${sessionScope.lang.equals(val)}">
                            UA
                        </c:when>
                        <c:otherwise>
                            EN
                        </c:otherwise>
                    </c:choose>
                </a>
                <div class="dropdown-menu">
                    <form class="form-inline" action="${pageContext.request.contextPath}/admin/adminMain">
                        <input type="hidden" value="en" name="localization"/>
                        <button type="submit" class="btn btn-outline-primary">EN</button>
                    </form>
                    <form class="form-inline" action="${pageContext.request.contextPath}/admin/adminMain">
                        <input type="hidden" value="ua" name="localization"/>
                        <button type="submit" class="btn btn-outline-primary">UA</button>
                    </form>
                </div>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>
