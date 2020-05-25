<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>About me</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' /> " rel="stylesheet"/>
    <link href="<c:url value='/resources/css/app.css' /> " rel="stylesheet"/>
</head>
<body>
<div class="generic-container" mode>
    <%@include file="authheader.jsp" %>
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">About me</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Role</th>
            </tr>
            </thead>

            <tbody>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.firstname}</td>
                <td>${user.surname}</td>
                <td>
                    <c:forEach items="${user.roles}" var="role">
                        <c:out value="${role.name}"/>
                    </c:forEach>
                </td>

            </tbody>
        </table>
    </div>
</body>
</html>
