<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<table class="table table-striped table-responsive-md">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">description</th>
        <th scope="col">Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="tariff" items="${tariffList}">
        <option value="${tariff.name}">${tariff.getName()}</option>
        <option value="${tariff.description}">${tariff.getDescription()}</option>
        <option value="${tariff.price}">${tariff.getPrice()}</option>

    </c:forEach>
    </tbody>
</table>
