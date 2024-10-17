<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
    <meta name="author" content="Hỏi Dân IT" />
    <title>Dashboard - Hỏi Dân IT</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>

<body class="sb-nav-fixed">
<jsp:include page="../layout/header.jsp" />
<div id="layoutSidenav">
    <jsp:include page="../layout/sidebar.jsp" />
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Manage Products</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                    <li class="breadcrumb-item active">Products</li>
                </ol>
                <div class="mt-5">
                    <div class="row">
                        <div class="col-12 mx-auto">
                            <div class="d-flex justify-content-between">
                                <h2>Table product</h2>
                                <a href="/admin/product/create" class="btn btn-primary">Create a product</a>
                            </div>
                            <hr/>
                            <div>
                                <table class="table table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Description</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Category</th>
                                        <th scope="col">Image</th>
                                        <th scope="col">Action</th>
                                    </tr>
                                    </thead>
                                                            <tbody>
                                                            <c:forEach var="product" items="${products}" >   <%-- var="user": tung user trong list users, items="${users}": list users (bien duoc truyen tu controller)--%>
                                                                <tr>
                                                                    <th>${product.id}</th>
                                                                    <td>${product.name}</td>
                                                                    <td style="width: 500px">${product.longDescription}</td>
                                                                    <td>${product.price}</td>
                                                                    <td>${product.quantity}</td>
                                                                    <td>${product.category}</td>
                                                                    <td>
                                                                        <img src="/images/product/${product.image}" alt="${product.name}" style="max-width: 100px; max-height: 100px;" />
                                                                    </td>
                                                                    <td>
                                                                        <a href="/admin/product/${product.id}" class="btn btn-success">View</a>
                                                                        <a href="/admin/product/update/${product.id}" class="btn btn-warning">Update</a>
                                                                        <a href="javascript:void(0)" class="btn btn-danger" onclick="confirmDelete(${product.id})">Delete</a>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="../layout/footer.jsp" />
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    function confirmDelete(productId) {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = '/admin/product/delete/' + productId;
            }
        })
    }
</script>

</body>

</html>