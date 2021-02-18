<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<table class="table table-striped table-responsive-md" id="tariffsTable">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">description</th>
        <th scope="col">Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="tariff" items="${tariffList}">
    <tr class="clickable-row">
        <td>${tariff.getName()}</td>
        <td>${tariff.getDescription()}</td>
        <td>${tariff.getPrice()}</td>
        </c:forEach>
    </tbody>
</table>