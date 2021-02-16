<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href=" https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.5.0/css/flag-icon.min.css"
          integrity="sha512-Cv93isQdFwaKBV+Z4X8kaVBYWHST58Xb/jVOcV9aRsGSArZsgAnFIhMpDoMDcFNoUtday1hdjn0nGp3+KZyyFw=="
          crossorigin="anonymous"/>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i></a>
        </div>
        <ul class="nav navbar-nav">
            <li>
                <a href="${pageContext.request.contextPath}/app/login"><i class="fa fa-sign-in"
                                                                          aria-hidden="true"></i></a>
            </li>
            <li><a href="${pageContext.request.contextPath}/app/logout}"><i class="fa fa-sign-out"
                                                                            aria-hidden="true"></i></a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a class="flag-icon flag-icon-ua" href="?sessionLocale=ua"></a></li>
            <li>&nbsp;&nbsp;</li>
            <li><a class="flag-icon flag-icon-us" href="?sessionLocale=en"></a></li>
            <li>&nbsp;&nbsp;</li>
        </ul>
    </div>
</nav>
</body>
</html>