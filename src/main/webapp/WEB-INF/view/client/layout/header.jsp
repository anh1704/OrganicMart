 <%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Navbar start -->
<div class="container-fluid fixed-top">
    <div class="container topbar bg-primary d-none d-lg-block">
        <div class="d-flex justify-content-between">
            <div class="top-info ps-2">
                <small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#" class="text-white">123 Street, New York</a></small>
                <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="#" class="text-white"> <c:out value="${pageContext.request.userPrincipal.name}"></c:out></a></small>
            </div>
        </div>
    </div>
    <div class="container px-0">
        <nav class="navbar navbar-light bg-white navbar-expand-xl">
            <a href="index.html" class="navbar-brand"><h1 class="text-primary display-6">Fruitables</h1></a>
            <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="fa fa-bars text-primary"></span>
            </button>
            <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                <div class="navbar-nav mx-auto">
                    <a href="/" class="nav-item nav-link active">Home</a>
                    <a href="shop.html" class="nav-item nav-link">Shop</a>
                    <a href="shop-detail.html" class="nav-item nav-link">Shop Detail</a>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                        <div class="dropdown-menu m-0 bg-secondary rounded-0">
                            <a href="/cart" class="dropdown-item">Cart</a>
                            <a href="chackout.html" class="dropdown-item">Chackout</a>
                            <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                            <a href="404.html" class="dropdown-item">404 Page</a>
                        </div>
                    </div>
                    <a href="contact.html" class="nav-item nav-link">Contact</a>
                </div>
                <div class="d-flex m-3 me-0">
                    <c:if test="${not empty pageContext.request.userPrincipal}">
                        <a href="/cart" class="position-relative me-4 my-auto">
                            <i class="fa fa-shopping-bag fa-2x"></i>
                            <span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;">
                                <c:out value="${sessionScope.sum}"></c:out>
                            </span>
                        </a>
                        <div class="dropdown my-auto">
                         <a href="#" class="dropdown" role="button" id="dropdownMenuLink"
                            data-bs-toggle="dropdown" aria-expanded="false">
                             <i class="fas fa-user fa-2x"></i>
                         </a>
                         <ul class="dropdown-menu dropdown-menu-end p-4" aria-labelledby="dropdownMenuLink">
                             <li class="d-flex align-items-center flex-column" style="min-width: 300px">
                                 <img style="width: 150px; height: 150px; border-radius: 50%; overflow: hidden"
                                 src="/images/avatar/${sessionScope.avatar}"/>
                                 <div class="text-center my-3">
                                     <c:out value="${sessionScope.fullName}"></c:out>
                                 </div>
                             </li>
                             <li><a class="dropdown-item" href="#">Quan ly tai khoan</a></li>
                             <li><a class="dropdown-item" href="#">Lich su mua hang</a></li>
                             <li>
                                 <hr class="dropdown-divider">
                             </li>
                             <li>
                                 <form method="post" action="/logout">
                                     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     <button class="dropdown-item" >Dang xuat</button>
                                 </form>
                             </li>
                         </ul>
                     </div>
                    </c:if>
                    <c:if test="${empty pageContext.request.userPrincipal}">
                        <a href="/login" class="btn btn-primary" style="color: white">Login</a>
                    </c:if>
                </div>

            </div>
        </nav>
    </div>
</div>
<!-- Navbar End -->