<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký</title>
    <link href="/client/css/register.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link href="/client/css/styles.css" rel="stylesheet" />
</head>
<body>
<div class="register-container">
    <div class="wrapper">
        <%--@elvariable id="registerUser" type=""--%>
        <form:form action="/register" method="post" modelAttribute="registerUser">
            <c:set var="errorFirstname">
                <form:errors path="firstName" cssClass="invalid-feedback" />
            </c:set>
            <c:set var="errorPassword">
                <form:errors path="confirmPassword" cssClass="invalid-feedback" />
            </c:set>
            <c:set var="errorEmail">
                <form:errors path="email" cssClass="invalid-feedback" />
            </c:set>
            <h1>Register</h1>
            <div class="input-box">
                <form:input class="form-control ${not empty errorFirstname ? 'is-invalid' : ''}" type="text" placeholder="First name" path="firstName" />
<%--                <i class="fa fa-user-alt icon"></i>--%>
                ${errorFirstname}
            </div>
            <div class="input-box">
                <form:input class="form-control" type="text" placeholder="Last name" path="lastName" />
<%--                <i class="fa fa-user-alt icon"></i>--%>
            </div>
            <div class="input-box">
                <form:input class="form-control ${not empty errorEmail ? 'is-invalid' : ''}" type="email" placeholder="Email" path="email"  />
<%--                <i class="fa fa-envelope icon"></i>--%>
                ${errorEmail}
            </div>
            <div class="input-box">
                <form:input class="form-control ${not empty errorPassword ? 'is-invalid' : ''}" type="password" path="password" placeholder="Password" />
<%--                <i class="fa fa-eye icon" onclick="togglePasswordVisibility()"></i>--%>
                ${errorPassword}
            </div>
            <div class="input-box">
                <form:input class="form-control" type="password" path="confirmPassword" placeholder="Confirm password" />
<%--                <i class="fa fa-eye icon" onclick="togglePasswordVisibility()"></i>--%>
            </div>
            <button type="submit" class="submit-button">Register</button>
            <div class="register-link">
                <p>Have an account? <a href="/login">Login</a></p>
            </div>
        </form:form>
    </div>
</div>

<script>
    function togglePasswordVisibility() {
        var passwordField = document.querySelector('input[name="password"]');
        var icon = document.querySelector('.fa-eye');
        if (passwordField.type === 'password') {
            passwordField.type = 'text';
            icon.classList.remove('fa-eye');
            icon.classList.add('fa-eye-slash');
        } else {
            passwordField.type = 'password';
            icon.classList.remove('fa-eye-slash');
            icon.classList.add('fa-eye');
        }
    }
</script>
</body>
</html>
