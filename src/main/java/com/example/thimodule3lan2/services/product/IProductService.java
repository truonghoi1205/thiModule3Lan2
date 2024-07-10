package com.example.thimodule3lan2.services.product;

import com.example.thimodule3lan2.models.Product;

import java.sql.Date;
import java.util.List;

public interface IProductService {
    List<Product> selectAllProduct();

    void createProduct(Product product);

    List<Product> selectTopProduct(int number);

    List<Product> selectProductOrderDate(Date startDay, Date endDay);
}
