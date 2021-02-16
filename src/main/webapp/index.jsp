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
        <div class="container my-2">
            <h1><fmt:message key="tariff.list"/></h1>
            <div>
                <a href="${pageContext.request.contextPath}/app/showNewTariff" class="btn btn-primary btn-sm mb-3">Add Tariff</a>
            </div>

            <div class="container">

                <div class="row">
                    <div class="col">
                        <ul class="nav nav-tabs mt-5" id="myTabs">
                            <li class="nav-item"><a href="#tab1" data-url="/tab1" class="nav-link active"
                                                    >Phone</a></li>
                            <li class="nav-item"><a href="#tab2" data-url="/tab2" class="nav-link"
                                                    >TV</a>
                            </li>
                            <li class="nav-item"><a href="#tab3" data-url="/tab3" class="nav-link"
                                                    >Internet</a></li>
                        </ul>

                        <div class="tab-content pt-3">
                            <div class="tab-pane active" id="tab1"></div>
                            <div class="tab-pane" id="tab2"></div>
                            <div class="tab-pane" id="tab3"></div>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <br>
        </div>
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