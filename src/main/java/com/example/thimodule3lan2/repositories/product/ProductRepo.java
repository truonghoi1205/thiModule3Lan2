package com.example.thimodule3lan2.repositories.product;

import com.example.thimodule3lan2.database.ConnectDB;
import com.example.thimodule3lan2.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepo implements IProductRepo{
    @Override
    public List<Product> selectAllProduct() throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection connection = new ConnectDB().getConnection();
        String sql ="select * from products";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setDiscount(rs.getInt("discount"));
            product.setStock(rs.getInt("stock"));
            products.add(product);
        }
        return products;
    }

    @Override
    public void createProduct(Product product) throws SQLException {
        Connection connection = new ConnectDB().getConnection();
        String sql = "insert into products(name, price,discount,stock ) values(?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, product.getName());
        ps.setDouble(2, product.getPrice());
        ps.setInt(3, product.getDiscount());
        ps.setInt(4, product.getDiscount());
        ps.executeUpdate();
    }

    @Override
    public List<Product> selectTopProduct(int number) throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection connection = new ConnectDB().getConnection();
        String sql = "select p.*, sum(od.quantity) AS TotalSold\n" +
                "from order_details od\n" +
                "join products p on od.id_product = p.id\n" +
                "group by p.id, p.name,p.price,p.discount,p.stock\n" +
                "order by TotalSold desc\n" +
                "limit ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,number);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setDiscount(rs.getInt("discount"));
            product.setStock(rs.getInt("stock"));
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> selectProductOrderDate(Date startDay, Date endDay) throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection connection = new ConnectDB().getConnection();
        String sql = "select p.*, sum(od.Quantity) as TotalOrdered\n" +
                "from orders o\n" +
                "join order_details od on o.id = od.id_order\n" +
                "join products p on od.id_product = p.id\n" +
                "where o.booking_date between ? and ?\n" +
                "group by  p.id, p.name,p.price,p.discount,p.stock\n" +
                "order by TotalOrdered desc;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setDate(1,startDay);
        ps.setDate(2,endDay);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setDiscount(rs.getInt("discount"));
            product.setStock(rs.getInt("stock"));
            products.add(product);
        }
        return products;
    }
}
