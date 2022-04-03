/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.products;

/**
 *
 * @author Michael Ramez
 */
public class Product {

    private final int id;

    private int stockQuantity, price;

    private String type, model, imagePath;

    public Product(int id, int stockQuantity, int price, String type, String model, String imagePath) {
        this.id = id;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.type = type;
        this.model = model;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
