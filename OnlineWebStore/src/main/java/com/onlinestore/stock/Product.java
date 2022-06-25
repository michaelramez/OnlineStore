/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.stock;

/**
 *
 * @author Michael Ramez
 */
public class Product {

    private Integer id, stockQuantity, price;
    private String model, imagePath;
    private Category category;

    public Product(Integer id, Integer stockQuantity, Integer price, String model, String imagePath, Category category) {
        this.id = id;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.model = model;
        this.imagePath = imagePath;
        this.category = category;
    }

    public Product(Integer stockQuantity, Integer price, String model, String imagePath, Category category) {
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.model = model;
        this.imagePath = imagePath;
        this.category = category;
    }

    public Product(Integer id, Integer stockQuantity, Integer price, String model, String imagePath) {
        this.id = id;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.model = model;
        this.imagePath = imagePath;
    }

    
    
    public Product() {

    }

    public Integer getId() {
        return id;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public Integer getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Category getCategory() {
        return category;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
