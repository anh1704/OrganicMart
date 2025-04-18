<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>${product.name} - Organic Mart</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
    <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


    <!-- Customized Bootstrap Stylesheet -->
    <link href="/client/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="/client/css/style.css" rel="stylesheet">

</head>
<body>

<!-- Spinner Start -->
<div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
    <div class="spinner-grow text-primary" role="status"></div>
</div>
<!-- Spinner End -->

<jsp:include page="../layout/header.jsp" />

<!-- Single Page Header start -->
<div class="container-fluid page-header py-5">
    <h1 class="text-center text-white display-6">Sản phẩm</h1>
    <ol class="breadcrumb justify-content-center mb-0">
        <li class="breadcrumb-item"><a href="/">Trang chủ</a></li>
        <li class="breadcrumb-item active text-white">Sản phẩm</li>
    </ol>
</div>
<!-- Single Page Header End -->
<!-- Fruits Shop Start-->

<!-- Single Product Start -->
<div class="container-fluid py-5 mt-5">
    <div class="container py-5">
        <div class="row g-4 mb-5">
            <div class="row g-4 fruite">
                <div class="col-12 col-md-4">
                    <div class="row g-4">
                        <div class="col-12" id="categoryFilter">
                            <div class="mb-2"><b>Danh mục</b></div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" id="category-1"
                                       value="Rau, củ, quả">
                                <label class="form-check-label" for="category-1">Rau, củ, quả</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" id="category-2"
                                       value="Trái cây">
                                <label class="form-check-label" for="category-2">Trái cây</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" id="category-3"
                                       value="Hải sản">
                                <label class="form-check-label" for="category-3">Hải sản</label>
                            </div>
                        </div>

                        <div class="col-12" id="priceFilter">
                            <div class="mb-2"><b>Mức giá</b></div>

                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" id="price-2"
                                       value="duoi-100">
                                <label class="form-check-label" for="price-2">Dưới 100.000đ </label>
                            </div>

                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" id="price-3"
                                       value="100-200">
                                <label class="form-check-label" for="price-3">Từ 100.000đ - 150.000đ</label>
                            </div>

                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" id="price-4"
                                       value="200-300">
                                <label class="form-check-label" for="price-4">Từ 200.000đ - 300.000đ</label>
                            </div>

                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" id="price-5"
                                       value="300-500">
                                <label class="form-check-label" for="price-5">Trên 300.000đ</label>
                            </div>
                        </div>


                        <div class="col-12">
                            <div class="mb-2"><b>Sắp xếp</b></div>

                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" id="sort-1"
                                       value="gia-tang-dan" name="radio-sort">
                                <label class="form-check-label" for="sort-1">Giá tăng dần</label>
                            </div>

                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" id="sort-2"
                                       value="gia-giam-dan" name="radio-sort">
                                <label class="form-check-label" for="sort-2">Giá giảm dần</label>
                            </div>

                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" id="sort-3" checked
                                       value="gia-nothing" name="radio-sort">
                                <label class="form-check-label" for="sort-3">Không sắp xếp</label>
                            </div>

                        </div>
                        <div class="col-12">
                            <button
                                    class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4"
                                    id="btnFilter">
                                Lọc Sản Phẩm
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-8 text-center">
                    <div class="row g-4">
                        <c:if test="${totalPages ==  0}">
                            <div>Không tìm thấy sản phẩm</div>
                        </c:if>
                        <c:forEach var="product" items="${products}">
                            <div class="col-md-6 col-lg-4">
                                <div class="rounded position-relative fruite-item img-fluid w-100 rounded-top" style="border: 1px solid #ffb524">
                                    <div class="fruite-img">
                                        <img src="/images/product/${product.image}"
                                             class="img-fluid w-100 rounded-top" alt="">
                                    </div>
                                    <div class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                         style="top: 10px; left: 10px;">${product.category}
                                    </div>
                                    <div
                                            class="p-4 border-top-0">
                                        <h4 style="font-size: 15px;">
                                            <a href="/product/${product.id}">
                                                    ${product.name}
                                            </a>

                                        </h4>
                                        <p style="font-size: 13px;">
                                                ${product.shortDescription}</p>
                                        <div
                                                class="d-flex  flex-lg-wrap justify-content-center flex-column">
                                            <p style="font-size: 15px; text-align: center; width: 100%;"
                                               class="text-dark  fw-bold mb-3">
                                                <fmt:formatNumber type="number"
                                                                  value="${product.price}" />
                                                đ
                                            </p>
                                            <form action="/add-product-to-cart/${product.id}"
                                                  method="post">
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                       value="${_csrf.token}" />

                                                <button
                                                        class="mx-auto btn border border-secondary rounded-pill px-3 text-primary"><i
                                                        class="fa fa-shopping-bag me-2 text-primary"></i>
                                                    Add to cart
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>


                        <c:if test="${totalPages > 0}">
                            <div class="pagination d-flex justify-content-center mt-5">
                                <li class="page-item">
                                    <a class="${1 eq currentPage ? 'disabled page-link' : 'page-link'}"
                                       href="/products?page=${currentPage - 1}${queryString}"
                                       aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <c:forEach begin="0" end="${totalPages - 1}" varStatus="loop">
                                    <li class="page-item">
                                        <a class="${(loop.index + 1) eq currentPage ? 'active page-link' : 'page-link'}"
                                           href="/products?page=${loop.index + 1}${queryString}">
                                                ${loop.index + 1}
                                        </a>
                                    </li>
                                </c:forEach>
                                <li class="page-item">
                                    <a class="${totalPages eq currentPage ? 'disabled page-link' : 'page-link'}"
                                       href="/products?page=${currentPage + 1}${queryString}"
                                       aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>

                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Single Product End -->
<!-- Fruits Shop End-->

<jsp:include page="../layout/footer.jsp" />

<!-- Back to Top -->
<a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>


<!-- JavaScript Libraries -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="/client/lib/easing/easing.min.js"></script>
<script src="/client/lib/waypoints/waypoints.min.js"></script>
<script src="/client/lib/lightbox/js/lightbox.min.js"></script>
<script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Template Javascript -->
<script src="/client/js/main.js"></script>
</body>
</html>