<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="login.page"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1><fmt:message key="sign.in"/></h1>
            <form method="post" action="${pageContext.request.contextPath}/app/login">
                <div class="form-group">
                    <label for="email"><fmt:message key="user.email"/></label>
                    <input type="text"
                           id="email" name="email" class="form-control"
                           autofocus="autofocus" placeholder="<fmt:message key="enter.user.email"/>"/>
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message key="user.password"/></label>
                    <input type="password"
                           id="password" name="password"
                           class="form-control"
                           placeholder="<fmt:message key="enter.user.password"/>"/>
                </div>
                <small style="color:red">${message}</small>
                <div class="form-group">
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <input type="submit" name="login-submit" id="login-submit"
                                   class="form-control btn btn-primary" value="<fmt:message key="sign.in"/>"/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
