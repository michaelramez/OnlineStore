/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asmaaMohamed
 */
public class Product {
    
    private int productId;
    private String type;
    private String model;
    private String stock_quantity;
    private float price;
    private  String productImage;
    
    //instuctor for all 
    public Product(int id, String type, String model, String stock_quantity, float price, String productImage) {
        this.productId = id;
        this.type = type;
        this.model = model;
        this.stock_quantity = stock_quantity;
        this.price = price;
        this.productImage = productImage;
    }

    public int getId() {
        return productId;
    }

    public void setId(int id) {
        this.productId = id;
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

    public String getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(String stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
    
    
    
    
    
    
            
    
}
