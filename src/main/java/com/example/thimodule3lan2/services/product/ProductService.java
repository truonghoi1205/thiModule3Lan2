package com.example.thimodule3lan2.services.product;

import com.example.thimodule3lan2.models.Product;
import com.example.thimodule3lan2.repositories.product.IProductRepo;
import com.example.thimodule3lan2.repositories.product.ProductRepo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ProductService implements IProductService{
    private static IProductRepo productRepo = new ProductRepo();
    @Override
    public List<Product> selectAllProduct() {
        try {
            return productRepo.selectAllProduct();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createProduct(Product product) {
        try {
            productRepo.createProduct(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> selectTopProduct(int number) {
        try {
            return productRepo.selectTopProduct(number);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> selectProductOrderDate(Date startDay, Date endDay) {
        try {
            return productRepo.selectProductOrderDate(startDay,endDay);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
