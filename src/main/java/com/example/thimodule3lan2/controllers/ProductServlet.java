package com.example.thimodule3lan2.controllers;

import com.example.thimodule3lan2.models.Product;
import com.example.thimodule3lan2.services.product.IProductService;
import com.example.thimodule3lan2.services.product.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name="ProductServlet", urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {
    private static IProductService productService = new ProductService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        switch (url) {
            case "/list":
                showAllProduct(req,resp);
                break;
            case "/create":
                showFormCreate(req,resp);
                break;
            case "/topProduct":
                selectTopProduct(req,resp);
                break;
            case "/orderDate":
                selectProductOrderDate(req,resp);
                break;
        }
    }

    private void selectProductOrderDate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date startDay = Date.valueOf(req.getParameter("startDay"));
        Date endDay = Date.valueOf(req.getParameter("endDay"));
        List<Product> products = productService.selectProductOrderDate(startDay,endDay);
        req.setAttribute("products",products);
        req.getRequestDispatcher("/views/list.jsp").forward(req,resp);
    }

    private void selectTopProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int number = Integer.parseInt(req.getParameter("number"));
        List<Product> products = productService.selectTopProduct(number);
        req.setAttribute("products",products);
        req.getRequestDispatcher("/views/list.jsp").forward(req,resp);
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/create.jsp").forward(req,resp);
    }

    private void showAllProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.selectAllProduct();
        req.setAttribute("products",products);
        req.getRequestDispatcher("/views/list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        switch (url) {
            case "/create":
                createProduct(req,resp);
                break;
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String stockStr = req.getParameter("stock");
        String errorMessage = validateProduct(name,priceStr,stockStr);

        double price = 0;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            errorMessage = "Vui lòng nhập giá.";
        }

        int stock = 0;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            errorMessage = "Vui lòng nhập tồn kho.";
        }

        int discount = Integer.parseInt(req.getParameter("discount"));
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setStock(stock);

        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("product", product);
            req.getRequestDispatcher("/views/create.jsp").forward(req, resp);
            return;
        }
        productService.createProduct(product);
        if (product.getErrors().isEmpty()) {
            req.setAttribute("noti", "Thêm thành công!");
            showAllProduct(req, resp);
        } else {
            req.setAttribute("product", product);
            resp.sendRedirect("/products/list");
        }
        req.setAttribute("product", product);
        resp.sendRedirect("/products/list");
    }


    private String validateProduct(String name, String priceStr,String stockStr) {
        if (name == null || name.isEmpty()) {
            return "Không được để trống tên sản phẩm";
        }
        try {
            if (priceStr == null || priceStr.isEmpty()) {
                return "Không được để trống giá";
            }
            double price = Double.parseDouble(priceStr);
            if (price < 100) {
                return "Giá phải lớn hơn 1000";
            }
        } catch (NumberFormatException e) {
            return "Vui lòng nhập đúng giá";
        }
        try {
            if (stockStr == null || stockStr.isEmpty()) {
                return "Tồn kho không được để trống";
            }
            int stock = Integer.parseInt(stockStr);
            if (stock < 10) {
                return "Tồn kho phải lớn hơn 10";
            }
        } catch (NumberFormatException e) {
            return "Vui lòng nhập số";
        }
        return null;
    }
}
