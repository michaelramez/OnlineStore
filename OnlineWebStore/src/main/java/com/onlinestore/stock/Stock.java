/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.stock;

import com.onlinestore.database.WebDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael Ramez
 */
public class Stock {

    private final WebDatabase dbInstance = WebDatabase.getDatabaseInstance();
    private static final Stock stockInstance = new Stock();

    private Stock() {
    }

    public static Stock getStockInstance() {
        return stockInstance;
    }

    public List<Category> GetAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String sqlCommand = "select * from category";
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int categoryid = resultSet.getInt("categoryid");
                String categoryname = resultSet.getString("categoryname");
                categories.add(new Category(categoryid, categoryname));
            }
        }
        return categories;
    }

    public List<Product> GetCategoryProducts(int categoryId) throws SQLException {
        List<Product> categoryProducts = new ArrayList<>();
        String sqlCommand = "select * from product,category where product.pcategoryid = category.categoryid and category.categoryid = ?";
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String categoryname = resultSet.getString("categoryname");
                Category category = new Category(categoryId, categoryname);
                int pid = resultSet.getInt("pid");
                int stockQuantity = resultSet.getInt("pquantity");
                int price = resultSet.getInt("pprice");
                String model = resultSet.getString("pmodel");
                String imagePath = resultSet.getString("pimage_path");
                 Product product = new Product(pid, stockQuantity, price, model, imagePath, category);
                categoryProducts.add(product);
            }
        }
        return categoryProducts;
    }

    public void AddCategory(Category category) throws SQLException {
        String sqlCommand = "insert into category(categoryname) values (?)";
        PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand);
        preparedStatement.setString(1, category.getName());
        preparedStatement.executeUpdate();
    }

    public void AddProduct(Product product) throws SQLException {
        String sqlCommand = "insert into product(pcategoryid,pmodel, pquantity,pprice, pimage_path) values(?, ?, ?, ?, ?)";
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setInt(1, product.getCategory().getId());
            preparedStatement.setString(2, product.getModel());
            preparedStatement.setInt(3, product.getStockQuantity());
            preparedStatement.setInt(4, product.getPrice());
            preparedStatement.setString(5, product.getImagePath());
            preparedStatement.executeUpdate();
        }
    }

    public void RemoveProduct(int pid) throws SQLException {
        String sqlCommand = "delete from product where pid = ?";
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setInt(1, pid);
            preparedStatement.executeUpdate();
        }
    }

    public void UpdateProductPrice(int pid, int newPrice) throws SQLException {
        String sqlCommand = "update product set pprice = ? where pid = ? ";
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setInt(1, newPrice);
            preparedStatement.setInt(2, pid);
            preparedStatement.executeUpdate();
        }
    }

    public void UpdateProductQuantity(int pid, int newQuantity) throws SQLException {

        String sqlCommand = "update product set pquantity = ? where pid = ? ";
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2, pid);
            preparedStatement.executeUpdate();
        }
    }

    public void UpdateProduct(Product product) throws SQLException {
        String sqlCommand = "update product set pmodel=?,pquantity=?,pprice=?,pimage_path=?,pcategoryid=? where pid=?";
        PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand);
        preparedStatement.setString(1, product.getModel());
        preparedStatement.setInt(2, product.getStockQuantity());
        preparedStatement.setInt(3, product.getPrice());
        preparedStatement.setString(4, product.getImagePath());
        preparedStatement.setInt(5, product.getCategory().getId());
        preparedStatement.setInt(6, product.getId());
        preparedStatement.executeUpdate();
    }

    public Product GetProductById(int pid) throws SQLException {
        String sqlCommand = "select * from product, category where product.pcategoryid = category.categoryid and product.pid = ?";
        Product product;
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setInt(1, pid);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int categoryId = resultSet.getInt("pcategoryid");
            String categoryname = resultSet.getString("categoryname");
            Category category = new Category(categoryId, categoryname);
            int stockQuantity = resultSet.getInt("pquantity");
            int price = resultSet.getInt("pprice");
            String model = resultSet.getString("pmodel");
            String imagePath = resultSet.getString("pimage_path");
            product = new Product(pid, stockQuantity, price, model, imagePath, category);
        }
        return product;
    }

    public int GetProductPrice(int pid) throws SQLException {
        String sqlCommand = "select pprice from product where product.pid = ?";
        int productprice;
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setInt(1, pid);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            productprice = resultSet.getInt("pprice");
        }
        return productprice;
    }

    public int GetProductQuantity(int pid) throws SQLException {
        String sqlCommand = "select pquantity from product where product.pid = ?";
        int productQuantity;
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setInt(1, pid);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            productQuantity = resultSet.getInt("pquantity");
        }
        return productQuantity;
    }
    
    public String GetProductName(int Pid) throws SQLException {
        String sqlCommand = "select pmodel from product where product.pid = ?";
        String productName;
        try (PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setInt(1, Pid);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            productName = resultSet.getString("pmodel");
        }
        return productName;
    }

    public static void main(String[] args) {

    }
}
