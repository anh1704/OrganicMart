<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link href="/client/scss/login.scss" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet" />
</head>
<body>
<div class="login-container">
    <div class="wrapper">
        <h1>Login</h1>
        <form:form action="/login" method="post">
            
            <c:if test="${param.error != null}">
                <div class="my-2" style="color: red">Invalid email or password</div>
            </c:if>
            <c:if test="${param.logout != null}">
                <div class="my-2" style="color: green">Logout success</div>
            </c:if>


            <div class="input-box">
                <input class="form-control" type="text" name="username" placeholder="Email" required />
            </div>

            <div class="input-box">
                <input class="form-control" type="password" name="password" placeholder="Password" required />
            </div>

            <div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            </div>

            <button type="submit">Login</button>

            <div class="register-link">
                <p>
                    Don't have an account? <a href="/register">Register</a>
                </p>
            </div>
        </form:form>
    </div>
</div>

<script>
    // Hàm bật/tắt hiển thị mật khẩu
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
