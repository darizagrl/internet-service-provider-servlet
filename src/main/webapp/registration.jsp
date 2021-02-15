<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="registration.page"/></title>
    <meta charset="utf-8">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form method="post" action="${pageContext.request.contextPath}/app/registration" accept-charset="utf-8">
                <h2 class="text-center"><fmt:message key="registration.page"/></h2>
                <div class="form-group">
                    <label for="firstname" class="control-label"><fmt:message key="user.firstname"/></label>
                    <input id="firstname" class="form-control" placeholder="<fmt:message key="enter.user.firstname"/>"
                           name="firstname">
                </div>
                <div class="form-group">
                    <label for="lastname" class="control-label"><fmt:message key="user.lastname"/></label>
                    <input id="lastname" class="form-control" placeholder="<fmt:message key="enter.user.lastname"/>"
                           name="lastname">
                </div>
                <div class="form-group">
                    <label for="email" class="control-label"><fmt:message key="user.email"/></label>
                    <input id="email" type="email" class="form-control"
                           placeholder="<fmt:message key="enter.user.email"/>" name="email">
                </div>
                <div class="form-group">
                    <label for="confirmEmail" class="control-label"><fmt:message key="user.confirm.email"/></label>
                    <input id="confirmEmail" type="email" class="form-control"
                           placeholder="<fmt:message key="user.confirm.email"/>" name="confirmEmail">
                </div>
                <div class="form-group">
                    <label for="password" class="control-label"><fmt:message key="user.password"/></label>
                    <input id="password" type="password" class="form-control"
                           placeholder="<fmt:message key="enter.user.password"/>" name="password">
                </div>
                <div class="form-group">
                    <label for="confirmPassword" class="control-label"><fmt:message key="user.confirm.password"/></label>
                    <input id="confirmPassword" type="password" class="form-control"
                           placeholder="<fmt:message key="user.confirm.password"/>" name="confirmPassword">
                </div>
                <small style="color:red">${message}</small>
                <div class="form-group">
                    <button type="submit" class="btn btn-success"><fmt:message key="register"/></button>
                </div>
                <div>
                    <span><fmt:message key="already.registered"/> <a
                            href="${pageContext.request.contextPath}/app/login"><fmt:message
                            key="login.here"/></a> </span>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
