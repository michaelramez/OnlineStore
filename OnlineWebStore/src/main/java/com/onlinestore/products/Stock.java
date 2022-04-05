/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.products;

import com.onlinestore.database.WebDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Ramez
 */
public class Stock {

    private ResultSet res;
    private final Map<Integer, Product> products;
    private static final WebDatabase db = WebDatabase.getDatabaseInstance();
    private static final Stock stockInstance = new Stock();

    private Stock() {
        this.products = new HashMap<>();
    }
    
    public static Stock getStockInstance(){
        return stockInstance;
    }
    
    public int GetProductPrice(int id){
        return this.products.get(id).getPrice();
    }
    
    
    public void LoadProducts() {
        String sqlCommand = "select * from product";
        try {
            PreparedStatement preStm = db.getConnection().prepareStatement(sqlCommand);
            res = preStm.executeQuery();
            while (res.next()) {
                int id = res.getInt("pid");
                int stockQuantity = res.getInt("pquantity");
                int price = res.getInt("pprice");
                String type = res.getString("ptype");
                String model = res.getString("pmodel");
                String imagePath = res.getString("pimage_path");
                Product product = new Product(id, stockQuantity, price, type, model, imagePath);
                products.put(id, product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Map<Integer, Product> GetCurrentProducts(){
        return this.products;
    }
    
    public void AddProduct(Product product) {
        String sqlCommand = "insert into product(ptype, pmodel, pquantity,pprice, pimage_path) values(?, ?, ?, ?, ?) returning pid ";
        try {
            PreparedStatement preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setString(1, product.getType());
            preStm.setString(2, product.getModel());
            preStm.setInt(3, product.getStockQuantity());
            preStm.setInt(4, product.getPrice());
            preStm.setString(5, product.getImagePath());
            res = preStm.executeQuery();
            int id = res.getInt("pid");
            products.put(id, product);

        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdateProductPrice(int id, int newPrice) {
        String sqlCommand = "update product set pprice = ? where pid = ? ";
        try {
            PreparedStatement preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setInt(1, newPrice);
            preStm.setInt(2, id);

            preStm.executeUpdate();

            products.get(id).setPrice(newPrice);

        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean UpdateProductQuantity(int id, int newQuantity) {
        boolean updateSuccessful = false;
        String sqlCommand = "update product set pquantity = ? where pid = ? ";
        try {
            PreparedStatement preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setInt(1, newQuantity);
            preStm.setInt(2, id);
            preStm.executeUpdate();
            products.get(id).setStockQuantity(newQuantity);
            updateSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateSuccessful;
    }   

    public void RemoveProduct(int id) {
        String sqlCommand = "delete from product where pid = ?";
        try {
            PreparedStatement preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setInt(1, id);
            preStm.executeUpdate();
            products.remove(id);

        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

    }
}
