/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.products;

import com.onlinestore.database.WebDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Ramez
 */
public class Stock {

    private PreparedStatement preStm;
    private String sqlCommand;
    private ResultSet res;
    private Hashtable<Integer, Product> products;
    private static WebDatabase db = WebDatabase.getDatabaseInstance();
    private static final Stock stock = new Stock();

    private Stock() {

    }

    public void LoadProducts() {
        sqlCommand = "select pid from product";
        try {
            preStm = db.getConnection().prepareStatement(sqlCommand);
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

    public void AddProduct(Product product) {
        sqlCommand = "insert into product(ptype, pmodel, pquantity,pprice, pimage_path) values(?, ?, ?, ?, ?) returning pid ";
        try {
            preStm = db.getConnection().prepareStatement(sqlCommand);
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

    void UpdateProductPrice(int id, int newPrice) {
        sqlCommand = "update product set pprice = ? where pid = ? ";
        try {
            preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setInt(1, newPrice);
            preStm.setInt(2, id);

            int numRowsAffected = preStm.executeUpdate();

            products.get(id).setPrice(newPrice);

        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void UpdateProductQuantity(int id, int newQuantity) {
        sqlCommand = "update product set pquantity = ? where pid = ? ";
        try {
            preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setInt(1, newQuantity);
            preStm.setInt(2, id);

            int numRowsAffected = preStm.executeUpdate();

            products.get(id).setStockQuantity(newQuantity);

        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void RemoveProduct(int id) {
        sqlCommand = "delete from product where pid = ?";
        try {
            preStm = db.getConnection().prepareStatement(sqlCommand);
            
            preStm.setInt(1, id);
            int numRowsAffected = preStm.executeUpdate();

            products.remove(id);

        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

    }
}
