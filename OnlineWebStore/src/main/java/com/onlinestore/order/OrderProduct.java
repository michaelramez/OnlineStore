/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.order;

/**
 *
 * @author Michael Ramez
 */
public class OrderProduct {
    private final int id, reqQuantity;

    public OrderProduct(int id, int reqQuantity) {
        this.id = id;
        this.reqQuantity = reqQuantity;
    }

    public int getId() {
        return id;
    }

    public int getReqQuantity() {
        return reqQuantity;
    }
    
    
}
