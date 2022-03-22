/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.products;

import com.onlinestore.database.WebDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Michael Ramez
 */
public class Product {

    private int id, stockQuantity, price;
    private String type, model, imagePath;

    public Product(int id, int stockQuantity, int price, String type, String model, String imagePath) {
        this.id = id;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.type = type;
        this.model = model;
        this.imagePath = imagePath;
    }


    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }



    public String getModel() {
        return model;
    }



    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }



//    public void ViewRating() {
//        //mongodb
//    }
}
