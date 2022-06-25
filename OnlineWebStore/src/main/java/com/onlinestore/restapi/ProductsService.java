/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.restapi;

import com.onlinestore.database.WebDatabase;
import com.onlinestore.stock.Product;
import com.onlinestore.stock.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Ramez
 */
public class ProductsService {


    private final WebDatabase dbInstance = WebDatabase.getDatabaseInstance();
    private final Stock stock = Stock.getStockInstance();
    private static final ProductsService productsServiceInstance = new ProductsService();
    
    private ProductsService(){
    }
    
    public static ProductsService getProductsServiceInstance(){
        return productsServiceInstance;
    }

    public List<String> ListCategories() {
        List<String> categories = new ArrayList<>();
        return categories;
    }

    public List<Product> ListModelsByCategory(String category) {
        List<Product> categoryModels = new ArrayList<>();
        return categoryModels;
    }

    public Product ListProductById(int pid) throws SQLException {
        return stock.GetProductById(pid);
    }
}
