<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 7/10/2024
  Time: 8:47 AM
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
<div class="container py-5">
    <div class=" p-3 shadow-sm bg-white mb-3 align-items-center rounded">
        <a href="/products/list" class="text-decoration-none text-secondary mb-0 h2">Danh sách sản phẩm</a>
        <div>
            <a href="/products/create" class="btn btn-outline-primary btn-sm my-2">Thêm mới</a>
        </div>
        <form action="/products/topProduct" class="d-flex m-0">
            <span class="m-0">Danh sách top: </span>
            <select name="number" id="" style="width: 70px; height: 25px" class="form-select form-select-sm mx-2">
                <option value="3">3</option>
                <option value="5">5</option>
                <option value="10">10</option>
            </select>
            <span class="m-0 me-2"> Sản phẩm được đặt hàng nhiều nhất</span>
            <button class="btn btn-sm btn-outline-secondary m-0" style="width: 50px; height: 30px" >Xem</button>
        </form>
        <form action="/products/orderDate" class="d-flex m-0 mt-2">
            <span class="m-0 me-2">Danh sách sản phẩm được đặt từ: </span>
            <input type="date" name="startDay"> <span class="mx-2">đến</span> <input type="date" name="endDay">
            <button class="btn btn-sm btn-outline-secondary m-0 ms-2" style="width: 50px; height: 30px" >Xem</button>
        </form>
    </div>
    <table class="table table-hover table-bordered mt-5">
        <thead>
        <tr>
            <th>#</th>
            <th>Tên</th>
            <th>Giá</th>
            <th>Discount</th>
            <th>Tồn kho</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="product" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${product.name}</td>
                <td><fmt:formatNumber value="${product.price}"/></td>
                <td>${product.discount}%</td>
                <td>${product.stock}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
