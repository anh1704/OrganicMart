<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Detail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="/css/demo.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-12 col-md-8 mx-auto">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h1 class="text-primary">Product Details</h1>
                <a href="/admin/product" class="btn btn-outline-primary">Back to Products</a>
            </div>
            <hr/>
            <div class="card shadow-lg">
                <div class="card-header text-white bg-primary text-center" style="font-size: 20px">Product Information</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-12">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item"><strong>ID:</strong> ${product.id}</li>
                                <li class="list-group-item"><strong>Name:</strong> ${product.name}</li>
                                <li class="list-group-item"><strong>Description:</strong> ${product.description}</li>
                                <li class="list-group-item"><strong>Price:</strong> $${product.price}</li>
                                <li class="list-group-item"><strong>Quantity:</strong> ${product.quantity}</li>
                                <li class="list-group-item"><strong>Category:</strong> ${product.category}</li>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
