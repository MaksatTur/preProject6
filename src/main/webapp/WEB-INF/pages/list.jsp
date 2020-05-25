<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' /> " rel="stylesheet"/>
    <link href="<c:url value='/resources/css/app.css' /> " rel="stylesheet"/>
</head>
<body>
<div class="generic-container">
    <a href="<c:url value='/user' />">About me</a>
    <%@include file="authheader.jsp" %>
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">List of Users</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <sec:authorize access="hasRole('ADMIN')">
                    <th width="100"></th>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <th width="100"></th>
                </sec:authorize>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.firstname}</td>
                    <td>${user.surname}</td>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td><a href="<c:url value='/admin/edit/${user.id}' />"
                               class="btn btn-success custom-width">edit</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td><a href="<c:url value='/admin/delete/${user.id}' />" class="btn btn-danger custom-width">delete</a>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <sec:authorize access="hasRole('ADMIN')">
        <div class="well">
            <a href="<c:url value='/admin/add' />">Add New User</a>
        </div>
    </sec:authorize>
</div>
</body>
</html>
