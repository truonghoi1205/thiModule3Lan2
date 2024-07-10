<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 7/10/2024
  Time: 9:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body class="bg-body-tertiary">
<div class="mx-auto py-5 w-25 mt-3">
    <div>
        <h2 class="text-center text-decoration-none text-secondary mb-0">TRANG THÊM SẢN PHẨM</h2>
    </div>
    <div class="shadow-sm bg-white p-3 mt-3 rounded">
        <form action="/products/create" method="post">
            <div>
                <input type="hidden" id="id" name="id" value="${product.id}">
            </div>
            <div>
                <label class="form-label" for="name">Thêm tên sản phẩm</label>
                <input type="text" class="form-control" id="name" name="name" value="${product.name}" required>
            </div>

            <div>
                <label class="form-label" for="price">Thêm giá</label>
                <input type="text" class="form-control" id="price" name="price" value="${product.price}">
            </div>
            <label class="form-label">Discount</label>
            <select class="form-select form-select mb-3" name="discount">
                <option value="5" ${product.discount == '5' ? 'selected' : ''}>5</option>
                <option value="10" ${product.discount == '10' ? 'selected' : ''}>10</option>
                <option value="15" ${product.discount == '15' ? 'selected' : ''}>15</option>
                <option value="20" ${product.discount == '20' ? 'selected' : ''}>20</option>
            </select>

            <div>
                <label class="form-label" for="stock">Tồn kho</label>
                <input type="text" class="form-control" id="stock" name="stock" value="${product.stock}" required>
            </div>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger mt-2">${errorMessage}</div>
            </c:if>
            <div class="d-flex justify-content-end mt-2">
                <button class="btn btn-primary btn-sm me-2">Lưu</button>
                <a href="/products/list" class="btn btn-sm btn-secondary">Hủy</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
