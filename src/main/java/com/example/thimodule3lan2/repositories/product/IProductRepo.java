package com.example.thimodule3lan2.repositories.product;

import com.example.thimodule3lan2.models.Product;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface IProductRepo {
    List<Product> selectAllProduct() throws SQLException;

    void createProduct(Product product) throws SQLException;

    List<Product> selectTopProduct(int number) throws SQLException;

    List<Product> selectProductOrderDate(Date startDay, Date endDay) throws SQLException;
}
