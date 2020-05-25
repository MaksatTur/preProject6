<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' /> " rel="stylesheet"/>
    <link href="<c:url value='/resources/css/app.css' /> " rel="stylesheet"/>
</head>
<body>
<div class="generic-container">
    <%@include file="authheader.jsp" %>
    <div class="well lead">User Registration Form</div>
    <form:form action="/admin/add" method="post" cssClass="form-horizontal" modelAttribute="userDTO">
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-label">First Name</label>
                <div class="col-md-7">
                    <form:input path="firstname" cssClass="form-control input-sm"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-label">Surname</label>
                <div class="col-md-7">
                    <form:input path="surname" cssClass="form-control input-sm"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-label">Login</label>
                <div class="col-md-7">
                    <form:input path="username" cssClass="form-control input-sm"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-label">Password</label>
                <div class="col-md-7">
                    <form:password path="password" cssClass="form-control input-sm"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-label">Roles</label>
                <div class="col-md-7">
                    <form:select path="role" items="${roles}" multiple="false" cssClass="form-control input-sm" />
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">
                <input type="submit" value="Register" class="btn btn-primary btn-sm"/>
                <a class="btn btn-primary btn-sm" href="<c:url value='/admin/'/> ">Cancel</a>
            </div>
        </div>

    </form:form>
</div>
</body>
</html>
